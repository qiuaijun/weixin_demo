package weixin.commodity;

import java.io.FileInputStream;

import weixin.access.AccessToken;
import weixin.access.WeixinUtil;

/**
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class GoodsService {

	// 增加商品对应的URL（POST）
	private static String add_goods_url = "https://api.weixin.qq.com/merchant/create?access_token=ACCESS_TOKEN";

	public static void addGoods(Goods goods) {
		try {
			FileInputStream fr = new FileInputStream("./test.txt");
			byte[] buf = new byte[1024];
			StringBuffer sb = new StringBuffer();
			while ((fr.read(buf)) != -1) {
				sb.append(new String(buf));
				buf = new byte[1024];// 重新生成，避免和上次读取的数据重复
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx66f039c83a5fe048";
		// 第三方用户唯一凭证密钥
		String appSecret = "0accd7442c2e274d45e87cffd23f0f79";
		System.out.println(System.getProperty("user.dir"));

		try {
			FileInputStream fr = new FileInputStream("test.txt");
			byte[] buf = new byte[1024];
			StringBuffer sb = new StringBuffer();
			while ((fr.read(buf)) != -1) {
				sb.append(new String(buf));
				buf = new byte[1024];// 重新生成，避免和上次读取的数据重复
			}

			AccessToken accessToken = WeixinUtil.getAccessToken(appId,
					appSecret);
			WeixinUtil.httpRequest(add_goods_url, "POST", sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
