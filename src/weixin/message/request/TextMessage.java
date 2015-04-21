package weixin.message.request;

/**
 * 文本消息
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}