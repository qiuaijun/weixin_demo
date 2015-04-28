package weixin.message.response;

/**
 * 文本消息
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class TextRespMessage extends BaseRespMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}