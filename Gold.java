package com.mongle.service.invest;

import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Gold {
	public static void main(String[] args) {
		String url = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		try {

			Document doc = saxBuilder.build("https://apis.data.go.kr/1160100/service/GetGeneralProductInfoService/getGoldPriceInfo?serviceKey=lv9bpyNMeZHwgq4vZdHVxoieUgW3b1RwSLU5oQH1MJA6BCz4Y86MVwXLcW3ij7OL%2Be9wXLIx1CEuOaAKLyzxwA%3D%3D");
			Element root = doc.getRootElement();

			Element response = root.getChild("response");
			Element body = response.getChild("body");
			List<Element> list = body.getChildren("items");
			
			Iterator<Element> iter = list.iterator();
			for (int i = 0; i < 1; i++) {
				if (!iter.hasNext())
					break;

				Element e = iter.next();
				String link = e.getChildTextTrim("link");

				System.out.println(link);
				
				url = link;
			}
			
			
//			// JSON 결과
//			BufferedReader bf;
//			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//			String result = bf.readLine();
//			bf.close();
//
//			jdom
//			JSONParser parser = new JSONParser();
//			JSONObject root = (JSONObject) parser.parse(result);
//			
//			JSONObject response = (JSONObject) root.get("response");
//			System.out.println(response);
//			JSONObject body = (JSONObject) response.get("body");
//			JSONArray list = (JSONArray) body.get("items");
//
//			for (Object item : list) {
//				String date = (String) ((JSONObject) item).get("basDt");
//				String title = (String) ((JSONObject) item).get("itmsNm");
//				long max = (Long) ((JSONObject) item).get("hipr");
//				long min = (Long) ((JSONObject) item).get("lopr");
//
//
//				System.out.printf("[%s]%s-최대 %,d원-최저 %,d원\n", date, title, max, min);
//				System.out.println();
			

		} catch (Exception e) {
			System.out.println("emain");
			e.getStackTrace();
		}
	}
}
