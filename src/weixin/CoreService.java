package weixin;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import weixin.access.WeixinUtil;
import weixin.dao.UserInfoDao;
import weixin.message.MessageUtil;
import weixin.message.response.TextRespMessage;
import weixin.order.Order;
import weixin.product.Product;

/**
 * 核心服务类
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class CoreService {
	// 缓冲用户的上次操作，主要是用于预订流程的控制
	private static Map<String, Order> user2LastBookOpt = new ConcurrentHashMap<String, Order>();

	private static Map<String, Product> products = new ConcurrentHashMap<String, Product>();
	static {
		Product product1 = new Product();
		product1.setName("苹果");
		Product product2 = new Product();
		product2.setName("樱桃");
		products.put("1", product1);
		products.put("2", product2);
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;

		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextRespMessage textMessage = new TextRespMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";
				String msg = requestMap.get("Content");
				respContent += msg;
				Order value = user2LastBookOpt.get(fromUserName);
				respContent += value;
				if (value != null) {
					// 已经选择预订，此处需要判断所输入的产品是否存在
					if (value.getStatus() == 1) {
						if (products.keySet().contains(msg)) {
							respContent = "请输入预订的" + msg + "数量，单位：箱";
							// 将状态设置为确认数量
							value.setStatus(2);
							value.setProductId(products.get(msg).getId());
							user2LastBookOpt.put(fromUserName, value);
						} else {
							respContent = "你输入的产品标识不存在，请重新输入！";
						}
					} else if (value.getStatus() == 2) {
						// 先判断是否为数字
						if (isNumeric(msg)) {
							respContent = "感谢你预订了" + msg + "箱"
									+ products.get(value.getProductId());
							value.setStatus(3);
							user2LastBookOpt.remove(fromUserName);
						} else {
							respContent = "你输入的格式不对，应该为数字！";
						}
					} else {
						respContent = "你输入的格式不对！";
						user2LastBookOpt.remove(fromUserName);
					}
				} else {
					respContent = "你输入的格式不对！";
					user2LastBookOpt.remove(fromUserName);
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
					// 将关注的用户ID保存在数据库中
					UserInfoDao.insertUserOpenId(fromUserName);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					UserInfoDao.deleteUser(fromUserName);
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					String fromUser = WeixinUtil
							.getUserNameByOpenId(fromUserName);
					if (eventKey.equals("booked")) {
						respContent = "请输入需要预订的产品标识：\n";
						Iterator<String> it = products.keySet().iterator();
						while (it.hasNext()) {
							String tmp = it.next();
							respContent += tmp + "."
									+ products.get(tmp).getName() + "\n";
						}
						Order order = new Order();
						order.setUserId(fromUserName);
						order.setStatus(1);
						// 本次操作是请求预订，那么下一步应该是输入1或者2，否则就清除
						user2LastBookOpt.put(fromUserName, order);
					} else {
						user2LastBookOpt.remove(fromUserName);
						if (eventKey.equals("11")) {
							respContent = "企业介绍";
						} else if (eventKey.equals("12")) {
							respContent = "社区服务站菜单项被点击！";
						} else if (eventKey.equals("query_product")) {
							respContent = "产品信息菜单项被点击！";
						} else if (eventKey.equals("31")) {
							respContent = "优惠信息菜单项被点击！";
						} else if (eventKey.equals("32")) {
							respContent = "健康知识菜单项被点击！";
						} else if (eventKey.equals("33")) {
							respContent = "联系我们菜单项被点击！";
						}
					}

					respContent += fromUser;
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}
