package weixin.menu;

import org.apache.log4j.Logger;

import weixin.access.AccessToken;
import weixin.access.WeixinUtil;

/**
 * 菜单管理
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class MenuManager {
	private static Logger log = Logger.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx66f039c83a5fe048";
		// 第三方用户唯一凭证密钥
		String appSecret = "0accd7442c2e274d45e87cffd23f0f79";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("企业介绍");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("我要加盟");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("客户咨询");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn21 = new CommonButton();
		btn21.setName("商品简介");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("近期促销");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn31 = new CommonButton();
		btn31.setName("联系我们");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("商品反馈");
		btn32.setType("click");
		btn32.setKey("32");

		View baiduview = new View();
		baiduview.setType("view");
		baiduview.setName("百度");
		baiduview.setUrl("http://www.baidu.com");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("关于绿田");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("商品介绍");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多信息");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, baiduview });

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}