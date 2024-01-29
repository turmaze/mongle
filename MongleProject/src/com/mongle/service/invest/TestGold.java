package com.mongle.service.invest;

import java.io.BufferedInputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestGold {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			URL url = new URL("https://apis.data.go.kr/1160100/service/GetGeneralProductInfoService/getGoldPriceInfo?serviceKey=lv9bpyNMeZHwgq4vZdHVxoieUgW3b1RwSLU5oQH1MJA6BCz4Y86MVwXLcW3ij7OL%2Be9wXLIx1CEuOaAKLyzxwA%3D%3D");
			
			BufferedInputStream xmldata = new BufferedInputStream(url.openStream());
			
			Document document = builder.parse(xmldata);
			
			Element root = document.getDocumentElement();
			System.out.println(root.getTagName());
			NodeList list = root.getElementsByTagName("item");
			
			for (int i=0; i<list.getLength();i++) {
				Node node = list.item(i);
				NodeList item_childList = node.getChildNodes();
				for(int j=0;j<item_childList.getLength();j++) {
					Node item_child = item_childList.item(j);
					Element eElement = (Element) item_child;

					System.out.println("날짜 : " + getTagValue("basDt", eElement));
					System.out.println("제품명 : " + getTagValue("itmsNm", eElement));
					System.out.println("최고가 : " + getTagValue("hipr", eElement));
					System.out.println("최저가 : " + getTagValue("lopr", eElement));
				}
			}
		} catch (Exception e) {
			System.out.println("TestGold");
			e.printStackTrace();
		}

	}
	public static String getTagValue(String tag, Element eElement) {

		// 결과를 저장할 result 변수 선언
		String result = "";

		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		result = nlList.item(0).getTextContent();

		return result;
	}

// tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, String childTag, Element eElement) {

//결과를 저장할 result 변수 선언
		String result = "";

		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		for (int i = 0; i < eElement.getElementsByTagName(childTag).getLength(); i++) {

			// result += nlList.item(i).getFirstChild().getTextContent() + " ";
			result += nlList.item(i).getChildNodes().item(0).getTextContent() + " ";
		}

		return result;
	}

}
