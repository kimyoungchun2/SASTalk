package sastalk3;
/**
 * SAS-JAVA Wrapper Class
 *  * 2019.3.15
 */

/**
 * @author
 *
 */

import static skt.tmall.talk.dto.type.AppKdCdType.ELEVENSTAPP;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


import skt.tmall.talk.dto.PushTalkParameter;
import skt.tmall.talk.dto.type.AppKdCdType;
import skt.tmall.talk.dto.type.Block;
import skt.tmall.talk.dto.type.BlockBoldText;
import skt.tmall.talk.dto.type.BlockBtnView;
import skt.tmall.talk.dto.type.BlockImg500;
import skt.tmall.talk.dto.type.BlockLinkUrl;
import skt.tmall.talk.dto.type.BlockTopCap;
import skt.tmall.talk.service.PushTalkSendService;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


public class SendtalkMsgMain {

	//기본정보
	//private Long memNo = 18468196L; //회원번호  강석12774111, 김영천18468196, 20750578김창범 (42751905, 10000276)
	//private String talkMsgTmpltNo = "002"; //알림톡 템플릿에 등록한 메시지 타입코드
	private AppKdCdType appKdCd = ELEVENSTAPP; // 발송대상 앱코드
	

    private String android_messageData;
    private String ios_messageData;
    private String detailUrl = "http://m.11st.co.kr/MW/TData/dataFree.tmall";
    private String bannerUrl = "http://m.11st.co.kr";
    
    private String memNo;
    private String talkMsgTmpltNo;
    private String talkTitle = "주문 알림톡입니다.(44)";
    private String BlockTopCapMain_Value = "패션워크";
    private String BlockTopCapSub_Value = "광고" ;
    private String BlockBoldTextMain_Value = "반값 타임딜 하루 44번 오픈" ;
    private String BlockBoldTextSub_Value = "놓치지마세요!" ;
    //private String BlockImg500_Value = "http://img.11st.co.kr/img/sample.jpg" ;
    private String BlockImg500_Value = "http://i.011st.com/ui_img/11talk/img_500_500_sample2.png";
    private String BlockBtnView_Value = "상세보기(44)" ;
    //private String BlockLinkUrlMobile_Value = "http://m.11st.co.kr/" ;
    private String BlockLinkUrlMobile_Value = "http://m.11st.co.kr/MW/MyPage/V1/benefitCouponDownList.tmall";
    private String BlockLinkUrlWeb_Value = "http://11st.co.kr" ;
    private String etcData = "{ \"campaigncode\":\"CAMP00000\", \"treatmentcode\":\"TR00000\" }";
    //String etcData = "{\"campaigncode\":\"CAMP00000\", \"treatmentcode\":\"TR00000\" }";
    
    public String getMemno() {
        return memNo;
    }

    public void setMemno(String memno) {
        this.memNo = memno;
    }

    public String getTalkMsgTmpltNo() {
        return talkMsgTmpltNo;
    }

    public void setTalkMsgTmpltNo(String talkMsgTmpltNo) {
        this.talkMsgTmpltNo = talkMsgTmpltNo;
    }
    
    public String getAndroid_messageData() {
           return android_messageData;
    }

    public void setAndroid_messageData(String android_messageData) {
           this.android_messageData= android_messageData;
    }

    public String getIos_messageData() {
          return ios_messageData;
    }

    public void setIos_messageData(String ios_messageData) {
          this.ios_messageData = ios_messageData;
    }
    
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
    
    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }      
    
    public String getTalkTitle() {
        return talkTitle;
    }

    public void setTalkTitle(String talkTitle) {
        this.talkTitle = talkTitle;
    }
    
    public String getBlockTopCapMain_Value() {
        return BlockTopCapMain_Value;
    }

    public void setBlockTopCapMain_Value(String BlockTopCapMain_Value) {
        this.BlockTopCapMain_Value = BlockTopCapMain_Value;
    }
    
    public String getBlockTopCapSub_Value() {
        return BlockTopCapSub_Value;
    }

    public void setBlockTopCapSub_Value(String BlockTopCapSub_Value) {
        this.BlockTopCapSub_Value = BlockTopCapSub_Value;
    }
    
    public String getBlockBoldTextMain_Value() {
        return BlockBoldTextMain_Value;
    }

    public void setBlockBoldTextMain_Value(String BlockBoldTextMain_Value) {
        this.BlockBoldTextMain_Value = BlockBoldTextMain_Value;
    }    
    
    public String getBlockBoldTextSub_Value() {
        return BlockBoldTextSub_Value;
    }

    public void setBlockBoldTextSub_Value(String BlockBoldTextSub_Value) {
        this.BlockBoldTextSub_Value = BlockBoldTextSub_Value;
    }        
    
    public String getBlockImg500_Value() {
        return BlockImg500_Value;
    }

    public void setBlockImg500_Value(String BlockImg500_Value) {
        this.BlockImg500_Value = BlockImg500_Value;
    }        

    public String getBlockBtnView_Value() {
        return BlockBtnView_Value;
    }

    public void setBlockBtnView_Value(String BlockBtnView_Value) {
        this.BlockBtnView_Value = BlockBtnView_Value;
    }        
    
    public String getBlockLinkUrlMobile_Value() {
        return BlockLinkUrlMobile_Value;
    }

    public void setBlockLinkUrlMobile_Value(String BlockLinkUrlMobile_Value) {
        this.BlockLinkUrlMobile_Value = BlockLinkUrlMobile_Value;
    }        
    
    public String getBlockLinkUrlWeb_Value() {
        return BlockLinkUrlWeb_Value;
    }

    public void setBlockLinkUrlWeb_Value(String BlockLinkUrlWeb_Value) {
        this.BlockLinkUrlWeb_Value = BlockLinkUrlWeb_Value;
    }   
    
    public String getEtcData() {
        return etcData;
    }

    public void setEtcData(String etcData) {
        this.etcData = etcData;
    }   
    
    
    public SendtalkMsgMain() {}
    
	public int exec() throws Exception {
		System.setProperty("server.type", "real");
		//푸시메시지
		JsonObject obj = new JsonObject();
		obj.addProperty("IOS_MSG", ios_messageData);
		obj.addProperty("AND_TOP_MSG", android_messageData);
		obj.addProperty("AND_BTM_MSG", android_messageData);
//  {"AND_TOP_MSG":"혜택톡 안드로이드","AND_BTM_MSG":"혜택톡 안드로이드","IOS_MSG":"혜택톡 아이폰" }
		

		//알림톡메시지
		String summary = talkTitle; //알림톡방 리스트에 노출 할 메시지
		List<Block> composites = Lists.newArrayList(
			new BlockTopCap(new BlockTopCap.Value(BlockTopCapMain_Value , BlockTopCapSub_Value))
			, new BlockBoldText(new BlockBoldText.Value(BlockBoldTextMain_Value , BlockBoldTextSub_Value))
			, new BlockImg500(Lists.newArrayList(
					new BlockImg500.Value(BlockImg500_Value) ,
					new BlockImg500.Value(BlockImg500_Value) ,
					new BlockImg500.Value(BlockImg500_Value) 
			))
			, new BlockBtnView(new BlockBtnView.Value(BlockBtnView_Value,
					new BlockLinkUrl(BlockLinkUrlMobile_Value , BlockLinkUrlWeb_Value)
			//		new BlockLinkUrl("http://m.11st.co.kr/MW/Product/productBasicInfo.tmall?prdNo=2147806088&detailViewType=webviewReady", "http://11st.co.kr")
					
			))
		);

		//알림톡메시지 (Example)
//		String summary = "주문 알림톡입니다.(40)"; //알림톡방 리스트에 노출 할 메시지
//		List<Block> composites = Lists.newArrayList(
//			new BlockTopCap(new BlockTopCap.Value("패션워크", "광고"))
//			, new BlockBoldText(new BlockBoldText.Value("반값 타임딜 하루 40번 오픈", "놓치지마세요!"))
//			, new BlockImg500(Lists.newArrayList(
//					new BlockImg500.Value("http://img.11st.co.kr/img/sample.jpg")
//			))
//			, new BlockBtnView(new BlockBtnView.Value("상세보기(40)",
//				new BlockLinkUrl("http://m.11st.co.kr/", "http://11st.co.kr")
//			))
//		);
		//etcData (캠페인 정보 추가 )
		Gson gson = new Gson();
        //HashMap<String,String> mapEtcData = gson.fromJson(etcData, new TypeToken<Map<String, String>>() {}.getType());
		HashMap<String,String> mapEtcData = gson.fromJson(etcData, HashMap.class);
		
		//메시지 셋팅
		PushTalkParameter data = new PushTalkParameter(talkMsgTmpltNo , Long.valueOf(memNo));
		data.setAppKdCd(appKdCd);
		data.setPushTopMessage(obj.toString());
		data.setPushBottomMessage(obj.toString());
		data.setPushIosMessage(obj.toString());
		data.setTalkDispYn("Y");            // 고정 처리 (Y) 알림-혜택톡방 동시 사용함 
		data.setDetailUrl(detailUrl);       //   일반푸시 사용시- 클릭URL
		data.setBannerUrl(bannerUrl);       //   푸시배너이미지. 없을경우 생략가능
		//data.setMsgGrpNo(1235L);          //   메시지 식별 그룹번호. 없을경우 생략가능
		data.setEtcData(mapEtcData);
		
		data.setTalkSummaryMessage(summary);
		data.setTalkMessage(composites);
		System.out.println(">>>>> composites: " + composites);
		System.out.println(">>>>> obj: " + obj);
		
		
		//예약발송
		data.setSendAllwBgnDt(new Date()); //예약발송시 설정.  예약발송시간을 java.util.Date 타입으로 작성.

		//SMS 셋팅 http://wiki.11stcorp.com/pages/viewpage.action?pageId=214088691
		//data.setSmsMsg("SMS 스펙에 해당하는 데이터 작성");
	    //테스트 데이터 셋팅  , 운영모드 일 경우 Remarking 
	    
		//알림톡 전송
		PushTalkSendService.INSTANCE.remoteSyncPush(Lists.newArrayList(data));
		return 0;
	}
	
	public static void main(String[] arg) throws Exception {
		// 전송SendtalkMsgMainalkMsg sendtalkMsg SendtalkMsgMainalkMsg();
		SendtalkMsgMain instance = new SendtalkMsgMain();
		
	    //테스트 데이터 셋팅  , 운영모드 일 경우 Remarking 
	    instance.setAndroid_messageData("안드로이드 메시지~~개인화테스트입니다~44번째(포맷변경)");
	    instance.setIos_messageData    ("아이폰 메시지~개인화테스트입니다~44번째(포맷유지)");
	    instance.setTalkMsgTmpltNo     ("001");
	    instance.setMemno              ("18468196"); //42751905 18468196 20750578
	    
		int ret = instance.exec();
		System.out.println(">>>>> ret = " + ret);
				
	}
}
