package weixin.message.response;

/**
 * 音乐消息
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
