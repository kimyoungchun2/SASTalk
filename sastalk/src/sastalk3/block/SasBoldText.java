package sastalk3.block;

public class SasBoldText {

	private String text;
	private String subText;
	///////////////////////////////////////////////
	public SasBoldText(String text, String subText) {
		this.text = text;
		this.subText = subText;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubText() {
		return subText;
	}
	public void setSubText(String subText) {
		this.subText = subText;
	}
}
