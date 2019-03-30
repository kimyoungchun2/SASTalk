package sastalk3.main;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Type1Main {

	public static void main(String[] args) throws Exception {
		System.setProperty("server.type", "real");
		
		JsonObject obj = new JsonObject();
		//푸시메시지
		obj.addProperty("IOS_MSG", "아이폰 메시지~개인화테스트입니다~44번째(포맷유지)");
		obj.addProperty("AND_TOP_MSG", "안드로이드 메시지~~개인화테스트입니다~44번째(포맷변경)");
		obj.addProperty("AND_BTM_MSG", "안드로이드 메시지~~개인화테스트입니다~44번째(포맷변경)");

		//알림톡메시지
		@SuppressWarnings("unused")
		String summary = "주문 알림톡입니다.(44)"; //알림톡방 리스트에 노출 할 메시지

		/*
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
		*/
		String jsonParam = "{\"alimiShow\":\"Y\",\"alimiText\":\"a\",\"alimiType\":\"001\",\"title1\":\"b\",\"advText\":\"광고\",\"title2\":\"c\",\"title3\":\"d\",\"arrImg\":[{\"imgUrl\":\"e\"}],\"ftrText\":\"f\",\"ftrMblUrl\":\"g\",\"ftrWebUrl\":\"h\"}";
		System.out.println(">>>>> " + jsonParam);
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(jsonParam, new TypeToken<Map<String, Object>>() {}.getType());
		System.out.println(">>>>> " + map);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> arrMap = (List<Map<String,Object>>) map.get("arrImg");
		System.out.println(">>>>> " + arrMap);
		//map.forEach((x,y) -> System.out.println("key:" + x + ", value:" + y));
	}
}
