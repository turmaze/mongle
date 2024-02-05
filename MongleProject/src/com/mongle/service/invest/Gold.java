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

import com.mongle.resource.Investment;
import com.mongle.service.InvestService;
import com.mongle.view.MongleVisual;

/**
 * 금 상품 클래스
 */
public class Gold {
	/**
	 * 금 상품 보유 내역
	 */
	public static ArrayList<Investment> listGold = new ArrayList<>();
	private static int price;
	private static int num;

	/**
	 * 매입한 금 가격 Getter
	 * 
	 * @return 매입한 금 가격
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 매입한 금 가격 Setter
	 * 
	 * @param price 매입한 금 가격
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 매입한 금 수량 Getter
	 * 
	 * @return 매입한 금 수량
	 */
	public int getNum() {
		return num;
	}

	/**
	 * 매입한 금 수량 Setter
	 * 
	 * @param num 매입한 금 수량
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 금 상품 시세 검색 및 주문
	 * 
	 * @return 메뉴 이동을 위한 변수
	 */
	public static int goldService() {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 상품 정보 리스트
		boolean loop = true;
		int r = -1;

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("금");

			table = searchGold(table); // 금상품 검색 메서드

			System.out.println();
			System.out.printf("%22s1. 구매\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			while (loop) {
				MongleVisual.choiceGuidePrint();
				String sel = scan.nextLine();
				if (sel.equals("1")) {
					Gold gold = new Gold();
					gold.setPrice((int) (table.get(0).getRate() + table.get(0).getMaxRate()) / 2);
					System.out.println();
					gold.setNum(orderGold(gold.price));
					loop = false;
				} else if (sel.equals("9")) {
					MongleVisual.menuMove("홈 화면");
					return 9;
				} else if (sel.equals("0")) {
					MongleVisual.menuMove("이전 화면");
					return 0;
				} else {
					MongleVisual.wrongInput();
				}
			} // while
		} // while

		// listGold.add(new Investment("금","금",price,num));
		// Investment.list.add(listGold);

		return 0;
	}

	/**
	 * 금 상품 주문
	 * 
	 * @param price 매입할 금 가격
	 * @return 매입할 금 수량
	 */
	public static int orderGold(int price) {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		MongleVisual.pusher();
		System.out.printf("%22s===================================\n", " ");
		System.out.printf("%22s   \t  호가 %,d원\n", " ", price);
		System.out.printf("%22s===================================\n", " ");
		while (loop) {
			System.out.printf("%22s주문 수량 입력(숫자):", " ");
			try {
				String num = scan.nextLine();
				if (Integer.parseInt(num) > 0) {

					InvestService.transaction("금상품 구매", price, Integer.parseInt(num));
					Investment.list.add(new Investment("금", "금", "금", price, Integer.parseInt(num)));

					return Integer.parseInt(num);
				}
			} catch (NumberFormatException e) {
				MongleVisual.wrongInput();
			}
		} // while
		
		return 0;
	}

	/**
	 * 금 상품 시세 검색
	 * 
	 * @param table 금 상품 시세 정보를 담을 리스트
	 * @return 금 상품 시세 정보를 담은 리스트
	 */
	private static List<InfoProduct> searchGold(List<InfoProduct> table) {
		String header = "+------------+---------------+----------+-----------+";
		System.out.printf("%11s%s\n", " ", header);
		System.out.printf("%11s|   고시날짜   |     상품명\t| 최고가(1g) | 최저가(1g) |\n", " ");
		System.out.printf("%11s%s\n", " ", header);
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
			print(table);
		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}
		System.out.printf("%11s%s\n", " ", header);

		return table;
	}

	/**
	 * 표 형식으로 출력
	 * 
	 * @param data 출력할 리스트
	 */
	public static void print(List<InfoProduct> data) {
		for (int i = 0; i < 10; i += 2) {
			System.out.printf("%11s|%-12s|%-12s\t|%9s원|%9s원|\n", " ", data.get(i).getPeriod(), data.get(i).getTitle(),
					data.get(i).getMaxRate(), data.get(i).getRate());
		}
	}

	/**
	 * XML API 분석 및 내용 추출
	 * 
	 * @param tag      필요한 내용 명칭
	 * @param eElement 내용을 찾아낼 Element
	 * @return 찾아낸 정보
	 */
	public static String getTagValue(String tag, Element eElement) {
		String result = "";
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		result = nlList.item(0).getTextContent();
		return result;
	}

}
