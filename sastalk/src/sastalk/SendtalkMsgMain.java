package sastalk;

/**
 * SAS-JAVA Wrapper Class
 * 2019.3.15
 */

/**
 * @author
 *
 */

import static skt.tmall.talk.dto.type.AppKdCdType.ELEVENSTAPP;

import java.util.Date;
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
import com.google.gson.JsonObject;


public class SendtalkMsgMain {
	public static void main(String[] arg) throws Exception {
		// 전송 처리
		SendtalkMsg sendtalkMsg = new SendtalkMsg();
		int ret = sendtalkMsg.exec();
		System.out.println(">>>>> ret = " + ret);
	}
}

class SendtalkMsg {

	//기본정보
	private Long memNo = 18468196L; //회원번호  강석12774111, 김영천18468196
	private String talkMsgTmpltNo = "002"; //알림톡 템플릿에 등록한 메시지 타입코드
	private AppKdCdType appKdCd = ELEVENSTAPP; // 발송대상 앱코드

	public int exec() throws Exception {
		//푸시메시지
		JsonObject obj = new JsonObject();
		obj.addProperty("IOS_MSG", "아이폰 메시지");
		obj.addProperty("AND_MSG", "안드로이드 메시지");

		//알림톡메시지
		String summary = "주문 알림톡입니다."; //알림톡방 리스트에 노출 할 메시지
		List<Block> composites = Lists.newArrayList(
			new BlockTopCap(new BlockTopCap.Value("패션워크", "광고"))
			, new BlockBoldText(new BlockBoldText.Value("반값 타임딜 하루 4번 오픈", "높치지마세요!"))
			, new BlockImg500(Lists.newArrayList(
					new BlockImg500.Value("http://img.11st.co.kr/img/sample.jpg")
			))
			, new BlockBtnView(new BlockBtnView.Value("상세보기",
					new BlockLinkUrl("http://m.11st.co.kr/", "http://11st.co.kr")
			))
		);

		//메시지 셋팅
		PushTalkParameter data = new PushTalkParameter(talkMsgTmpltNo, memNo);
		data.setAppKdCd(appKdCd);
		data.setPushTopMessage(obj.toString());
		data.setPushBottomMessage(obj.toString());
		data.setPushIosMessage(obj.toString());
		data.setTalkSummaryMessage(summary);
		data.setTalkMessage(composites);
		System.out.println(">>>>> composites: " + composites);
		
		//예약발송
		data.setSendAllwBgnDt(new Date()); //예약발송시 설정.  예약발송시간을 java.util.Date 타입으로 작성.

		//SMS 셋팅 http://wiki.11stcorp.com/pages/viewpage.action?pageId=214088691
		//data.setSmsMsg("SMS 스펙에 해당하는 데이터 작성");

		//알림톡 전송
		PushTalkSendService.INSTANCE.remoteSyncPush(Lists.newArrayList(data));
		return 0;
	}
}


