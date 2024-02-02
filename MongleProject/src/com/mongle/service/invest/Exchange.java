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

public class Exchange {

	public static int buyPrice;
	public static int buyAmount;
	public static ArrayList<Investment> listExchange = new ArrayList<>();

	public static int getBuyPrice() {
		return buyPrice;
	}

	public static void setBuyPrice(int buyPrice) {
		Exchange.buyPrice = buyPrice;
	}

	public static int getBuyAmount() {
		return buyAmount;
	}

	public static void setBuyAmount(int buyAmount) {
		Exchange.buyAmount = buyAmount;
	}

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
				System.out.println();
				System.out.printf("%22s선택(번호): ", " ");

				String sel = scan.nextLine();

				System.out.println();

				String fxName = "";
				String price = "";

				if (sel.equals("1")) {
					index -= 4;
				} else if (sel.equals("2")) {
					index -= 3;
				} else if (sel.equals("3")) {
					index -= 2;
				} else if (sel.equals("4")) {
					index -= 1;
				} else if (sel.equals("5")) {
					index -= 0;
				} else if (sel.equals("6")) {
					continue;
				} else if (sel.equals("9")) {
					return 9;
				} else if (sel.equals("0")) {
					return 0;
				} else {
					System.out.printf("%22s입력이 올바르지 않습니다.\n", " ");
					System.out.printf("%22s홈 화면으로 돌아가시려면 엔터를 눌러주세요.\n", " ");
					scan.nextLine();
					continue;
				}
				fxName = "선택하신 외화: " + getFx(fx.get(index), "cur_nm");
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
					} else if (Integer.parseInt(amount)<100) {
						System.out.printf("%22s최소 구매 수량(100) 이상을 입력해주세요\n", " ");
					} else {
						break;
					}
				}
				price = price.replace(price.substring(price.indexOf(".")), "");
				price = price.replace(",", "");
				System.out.printf("%22s총 구매 대금: %,d원\n", " ", Integer.parseInt(price) * Integer.parseInt(amount));
				System.out.printf("%22s구매하시겠습니까? (y/n)\n", " ");
				System.out.printf("%22s선택: ", " ");
				sel = scan.nextLine();
				if (sel.equals("y")) {
					buyPrice = Integer.parseInt(price);
					buyAmount = Integer.parseInt(amount);
					InvestService.transaction(buyPrice, buyAmount);
					index = -1;
					continue;
				} else if (sel.equals("n")) {
					MongleVisual.pusher();
					System.out.printf("%22s거래가 취소되었습니다.\n", " ");
					MongleVisual.stopper();
					index = -1;
					continue;
				} else {
					MongleVisual.pusher();
					System.out.printf("%22s입력이 올바르지 않습니다.\n", " ");
					MongleVisual.stopper();
					index = -1;
					continue;
				}

			}
			listExchange.add(new Investment("환전", "환전", Exchange.getBuyPrice(), Exchange.getBuyAmount()));

		} catch (Exception e) {
			System.out.println("Exchange");
			e.printStackTrace();
		}
		return r;
		

	}// Exchange

	private static Object getFx(Object obj, String element) {
		return ((JSONObject) obj).get(element);
	}

}
