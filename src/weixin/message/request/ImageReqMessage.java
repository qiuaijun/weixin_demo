package weixin.message.request;

/**
 * 图片消息
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class ImageReqMessage extends BaseReqMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
