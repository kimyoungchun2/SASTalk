package sastalk3.block;

public class SasSubText {

	private String text;
	private String align;   /* left(default), center, right(?) */
	//////////////////////////////////////////////////////
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
}
