package sastalk3.block;

public class SasCoupon {

	private String couponText;
	private String title;
	private String subText1;
	private String subText2;
	private String couponNo;
	private boolean isDisplayBtn;   /* true(default), false */
	//////////////////////////////////////////////////////////
	public String getCouponText() {
		return couponText;
	}
	public void setCouponText(String couponText) {
		this.couponText = couponText;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubText1() {
		return subText1;
	}
	public void setSubText1(String subText1) {
		this.subText1 = subText1;
	}
	public String getSubText2() {
		return subText2;
	}
	public void setSubText2(String subText2) {
		this.subText2 = subText2;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public boolean isDisplayBtn() {
		return isDisplayBtn;
	}
	public void setDisplayBtn(boolean isDisplayBtn) {
		this.isDisplayBtn = isDisplayBtn;
	}
}
