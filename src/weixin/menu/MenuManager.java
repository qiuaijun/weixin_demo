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
		btn12.setName("社区服务站");
		btn12.setType("click");
		btn12.setKey("12");

		// 团子商城--产品信息
		CommonButton productBtn = new CommonButton();
		productBtn.setName("产品信息");
		productBtn.setType("click");
		productBtn.setKey("query_product");
		// 团子商城--我要预订
		CommonButton bookedBtn = new CommonButton();
		bookedBtn.setName("我要预订");
		bookedBtn.setType("click");
		bookedBtn.setKey("booked");

		CommonButton btn31 = new CommonButton();
		btn31.setName("优惠信息");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("健康知识");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("联系我们");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("关于团子");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("团子商城");
		mainBtn2.setSub_button(new Button[] { productBtn, bookedBtn });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多信息");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });

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