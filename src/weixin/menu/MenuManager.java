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
	// 第三方用户唯一凭证
	public static String appId = "wx66f039c83a5fe048";
	// 第三方用户唯一凭证密钥
	public static String appSecret = "0accd7442c2e274d45e87cffd23f0f79";

	public static void main(String[] args) {

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
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("🍒五莲大樱桃");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("🍷法国红酒");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("🍎烟台苹果");
		btn13.setType("click");
		btn13.setKey("13");

		// 团子商城--产品信息
		CommonButton btn21 = new CommonButton();
		btn21.setName("💻网上预订");
		btn21.setType("click");
		btn21.setKey("booked");
		// 团子商城--我要预订
		CommonButton btn22 = new CommonButton();
		btn22.setName("📋其他方式");
		btn22.setType("click");
		btn22.setKey("query_product");

		CommonButton btn31 = new CommonButton();
		btn31.setName("👉🏼关于我们");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("📌健康贴士");
		btn32.setType("click");
		btn32.setKey("32");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("近期热销");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("如何购买");
		mainBtn2.setSub_button(new Button[] { btn21, btn22 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("走进百米");
		mainBtn3.setSub_button(new Button[] { btn31, btn32 });

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