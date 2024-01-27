package com.mongle.service.invest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mongle.service.invest.DepositSaving.InfoProduct;

public class Gold {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 상품 정보 리스트
		boolean loop = true;

		while (loop) {
			table = researchGold(table); // 금상품 검색 메서드

			System.out.println("1. 구매");
			System.out.println("0. 이전으로");

			while (loop) {
				System.out.println("선택(번호) : ");
				String sel = scan.nextLine();

				if (sel.equals("1")) {
					System.out.println("구매 화면으로 이동합니다.");
					loop = false;
				} else if (sel.equals("0")) {
					System.out.println("이전 화면으로 이동합니다.");
					loop = false;
				} else {
					System.out.println("올바른 번호를 입력해주세요.");
				}
			} // while
		} // while
	} // main

	private static List<InfoProduct> researchGold(List<InfoProduct> table) {
		String header = "+------------+------------------+----------+----------+";
		System.out.println(header);
		System.out.println("|   고시날짜    |       상품명\t|   최고가   |   최저가   |");
		System.out.println(header);
		try {
			String url = "https://apis.data.go.kr/1160100/service/GetGeneralProductInfoService/getGoldPriceInfo?serviceKey=lv9bpyNMeZHwgq4vZdHVxoieUgW3b1RwSLU5oQH1MJA6BCz4Y86MVwXLcW3ij7OL%2Be9wXLIx1CEuOaAKLyzxwA%3D%3D";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				Element eElement = (Element) nNode;
				String period = getTagValue("basDt", eElement);
				String title = getTagValue("itmsNm", eElement);
				double maxrate = Double.parseDouble(getTagValue("hipr", eElement));
				double rate = Double.parseDouble(getTagValue("lopr", eElement));

				InfoProduct d = new InfoProduct("", title, period, rate, maxrate);
				table.add(d);
			}
			printAsciiTable(table);
		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}
		System.out.println(header);

		return table;
	}

	public static void printAsciiTable(List<InfoProduct> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < 8; i++) {
			System.out.printf("|%-12s|%-7s\t|%9s원|%9s원|\n", data.get(i).getPeriod(), data.get(i).getTitle(),
					data.get(i).getMaxRate(), data.get(i).getRate());
		}
	}

	public static String getTagValue(String tag, Element eElement) {

		String result = "";
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		result = nlList.item(0).getTextContent();

		return result;
	}

// tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, String childTag, Element eElement) {

		String result = "";
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		for (int i = 0; i < eElement.getElementsByTagName(childTag).getLength(); i++) {
			// result += nlList.item(i).getFirstChild().getTextContent() + " ";
			result += nlList.item(i).getChildNodes().item(0).getTextContent() + " ";
		}

		return result;
	}
}
