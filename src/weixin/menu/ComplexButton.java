package weixin.menu;

/**
 * 复杂按钮（父按钮）
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}