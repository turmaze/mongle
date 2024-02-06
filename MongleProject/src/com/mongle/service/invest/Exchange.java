package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.resource.Investment;
import com.mongle.service.InvestService;
import com.mongle.view.MongleVisual;

/**
 * 환전(외화 상품) 클래스
 */
public class Exchange {
	/**
	 * 외화 상품 보유 내역
	 */
	
	public static int buyPrice;
	public static int buyAmount;
	public static String realname;
	
	
	/**
	 * 매입한 외화 이름 Getter
	 * 
	 * @return 매입한 외화 이름
	 */
	public static String getRealname() {
		return realname;
	}
	
	/**
	 * 매입한 외화 이름 Setter
	 * 
	 * @return realname 매입한 외화 이름
	 */
	public static void setRealname(String realname) {
		Exchange.realname = realname;
	}
	
	/**
	 * 매입한 외화 가격 Getter
	 * 
	 * @return 매입한 외화 가격
	 */
	public static int getBuyPrice() {
		return buyPrice;
	}
	
	/**
	 * 매입한 외화 가격 Setter
	 * 
	 * @return buyPrice 매입한 외화 가격
	 */
	public static void setBuyPrice(int buyPrice) {
		Exchange.buyPrice = buyPrice;
	}
	
	/**
	 * 매입한 외화 수량 Getter
	 * 
	 * @return 매입한 외화 수량
	 */
	public static int getBuyAmount() {
		return buyAmount;
	}
	
	/**
	 * 매입한 외화 수량 Setter
	 * 
	 * @return buyArice 매입한 외화 수량
	 */
	public static void setBuyAmount(int buyAmount) {
		Exchange.buyAmount = buyAmount;
	}
	
	/**
	 * 외화 시세 검색 및 주문
	 * 
	 * @return 메뉴 이동을 위한 변수
	 */
	public static int exchangeService() {

		int r = -1;

		String exchangeURL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=I2RthKfXSu7z1LFQH3mxZqouqGgL3KKm&searchdate=20240126&data=AP01";

		try {

			Scanner scan = new Scanner(System.in);
			Boolean loop = true;
			int index = -1;

			while (loop) {

				MongleVisual.pusher();

				MongleVisual.menuHeader("환전");

				System.out.println();
				JSONArray jsonResult = new JSONArray();
				try {

					URL url = new URL(exchangeURL);
					// JSON 결과
					BufferedReader bf;
					bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
					String result = bf.readLine();

					JSONParser parser = new JSONParser();
					jsonResult = (JSONArray) parser.parse(result);

				} catch (Exception e) {
					System.out.println("연결 실패");
					System.out.println("재시도 하시려면 엔터");
					scan.nextLine();
					continue;
				}

				String header = "+----+--------------------------+---------------+---------------+---------------+";
				System.out.println(header);
				System.out.printf("|%4s|     국가 및 통화명  \t|%5s구매시\t|%5s판매시\t|%3s매매기준율\t|\n", "", " ", " ", " ");
				System.out.println(header);

				JSONArray fx = new JSONArray();

				for (Object obj : jsonResult) {
					if (getFx(obj, "cur_nm").equals("한국 원")) {
						continue;
					}
					fx.add(obj);
				}

				for (int i = 1; i <= 5; i++) {
					index++;
					if (index >= fx.size()) {
						System.out.println("다음 페이지가 없습니다.");
						index = fx.size()-1;
						break;
					}
					JSONObject obj = new JSONObject();
					obj = (JSONObject) fx.get(index);
					String curnm = "";
					curnm = ((String) getFx(obj, "cur_nm")).replace(" ", "");
					String space = "";
					for (int j = 0; j < 9 - curnm.length(); j++) {
						space += " ";
					}
					System.out.printf("|%4s|%s%12s\t|%12s\t|%12s\t|%12s\t|", i, space, getFx(obj, "cur_nm"),
							getFx(obj, "tts"), getFx(obj, "ttb"), getFx(obj, "deal_bas_r"));
					System.out.println();
				}

				System.out.println(header);
				System.out.println();

				System.out.printf("%22s6. 다음페이지\n", " ");
				System.out.printf("%22s9. 홈으로\n", " ");
				System.out.printf("%22s0. 이전으로\n", " ");
				MongleVisual.choiceGuidePrint();

				String sel = scan.nextLine();

				System.out.println();

				String fxName = "";
				String price = "";
				String real = "";

				if (sel.equals("1")) {
					if (index != fx.size()-1) {
						index -= 4;
					} else {
						index = fx.size()-2;
					}
				} else if (sel.equals("2")) {
					if (index != fx.size()-1) {
						index -= 3;
					}
				} else if (sel.equals("3")) {
					index -= 2;
				} else if (sel.equals("4")) {
					index -= 1;
				} else if (sel.equals("5")) {
					index -= 0;
				} else if (sel.equals("6")) {
					continue;
				} else if (sel.equals("9")) {
					MongleVisual.menuMove("홈 화면");
					return 9;
				} else if (sel.equals("0")) {
					MongleVisual.menuMove("이전 화면");
					return 0;
				} else {
					MongleVisual.wrongInput();
					continue;
				}
				fxName = "선택하신 외화: " + getFx(fx.get(index), "cur_nm");
				real=  getFx(fx.get(index), "cur_nm") + "";
				price = (String) getFx(fx.get(index), "tts");

				MongleVisual.pusher();

				MongleVisual.menuHeader(fxName);

				String amount = "";
				while (true) {
					System.out.printf("%22s수량(숫자): ", " ");
					amount = scan.nextLine();
					String regex = "^[0-9]+$";
					Pattern p1 = Pattern.compile(regex);
					Matcher m1 = p1.matcher(amount);
					if (!m1.find()) {
						System.out.printf("%22s정확한 숫자를 입력해 주시기 바랍니다.\n", " ");
					} else if (Integer.parseInt(amount) < 100) {
						System.out.printf("%22s최소 구매 수량(100) 이상을 입력해주세요\n", " ");
					} else {
						break;
					}
				}
				price = price.replace(price.substring(price.indexOf(".")), "");
				price = price.replace(",", "");
				System.out.printf("%22s총 구매 대금: %,d원\n", " ", Integer.parseInt(price) * Integer.parseInt(amount));
				System.out.printf("%22s구매하시겠습니까? (y/n)\n", " ");
				MongleVisual.choiceGuidePrint();
				sel = scan.nextLine();
				if (sel.equals("y")) {
					buyPrice = Integer.parseInt(price);
					buyAmount = Integer.parseInt(amount);
					InvestService.transaction("환전", buyPrice, buyAmount);
					realname = real;
					index = -1;
					Investment.list.add(new Investment("환전", "", Exchange.getRealname(), Exchange.getBuyPrice(), Exchange.getBuyAmount()));
					continue;
				} else if (sel.equals("n")) {
					MongleVisual.pusher();
					System.out.printf("%22s거래가 취소되었습니다.\n", " ");
					MongleVisual.stopper();
					index = -1;
					continue;
				} else {
					MongleVisual.wrongInput();
					index = -1;
					continue;
				}

			}
			
			Investment.list.add(new Investment("환전", "환전", realname, Exchange.getBuyPrice(), Exchange.getBuyAmount()));
		} catch (Exception e) {
			System.out.println("Exchange");
			e.printStackTrace();
		}
		return r;

	}// Exchange
	
	/**
	 * JSONObject 분석 및 내용 추출
	 * 
	 * @param name		외화 명
	 * @param element	내용을 찾아낼 element
	 * @return 찾아낸 정보
	 */
	private static Object getFx(Object name, String element) {
		return ((JSONObject) name).get(element);
	}

}
