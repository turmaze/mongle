package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.view.MongleVisual;

public class Exchange {
	
	private static double buyPrice;
	private static int buyAmount;
	
	
	public static void exchangeService() {
		
		String exchangeURL =  "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=I2RthKfXSu7z1LFQH3mxZqouqGgL3KKm&searchdate=20240126&data=AP01";
		
		try {

			Scanner scan = new Scanner(System.in);
			Boolean loop = true;
			int index = -1;
			

			while (loop) {
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
					System.out.printf("%22s연결 실패\n", " ");
					System.out.printf("%22s재시도 하시려면 엔터\n", " ");
					scan.nextLine();
					continue;
				}
				
				String header = "+----+--------------------------+---------------+---------------+---------------+";
		        System.out.println(header);
		        System.out.printf(
		        		"|%4s|     국가 및 통화명  \t|%5s구매시\t|%5s판매시\t|%3s매매기준율\t|\n"
		        		, "", " ", " ", " ");
				
		        JSONArray fx = new JSONArray();
		        
		        for (Object obj : jsonResult) {
		        	if (getFx(obj, "cur_nm").equals("한국 원")) {
						continue;
					}
		        	fx.add(obj);
		        }
		        
		        
		        for (int i=1; i<=5; i++) {
		        	index++;
		        	if (index>=fx.size()) {
		        		System.out.println("다음 페이지가 없습니다.");
		        		break;
		        	}
		        	JSONObject obj = new JSONObject();
		        	obj = (JSONObject) fx.get(index);
		        	String curnm = "";
					curnm = ((String)getFx(obj, "cur_nm")).replace(" ", "");
					String space = "";
					for (int j=0; j < 9-curnm.length(); j++) {
						space += " ";
					}
					System.out.printf("|%4s|%s%12s\t|%12s\t|%12s\t|%12s\t|"
										,i
										,space
										,getFx(obj, "cur_nm")
										,getFx(obj, "tts")
										,getFx(obj, "ttb")
										,getFx(obj, "deal_bas_r"));
					System.out.println();
		        }
				
				System.out.println(header);

				System.out.printf("%22s6. 다음페이지\n", " ");
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
				} else if (sel.equals("0")){
					break;
				} else {
					System.out.printf("%22s입력이 올바르지 않습니다.\n", " ");
					System.out.printf("%22s홈 화면으로 돌아가시려면 엔터를 눌러주세요.\n", " ");
					index = -1;
					scan.nextLine();
					continue;
				}
				fxName = " 선택하신 외화: " + getFx(fx.get(index), "cur_nm");
				price = (String) getFx(fx.get(index), "tts");

				price = price.replace(",", "");

				
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
						} else {
							break;
						}
					}
					System.out.printf("%22s총 구매 대금: %,.2f원\n", " ", Double.parseDouble(price) * Integer.parseInt(amount));
					System.out.printf("%22s구매하시겠습니까? (y/n)\n", " ");
					System.out.printf("%22s선택: ", " ");
					sel = scan.nextLine();
					if (sel.equals("y")) {
						System.out.printf("%22s거래가 완료되었습니다.\n", " ");
						System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
						setBuyPrice(Double.parseDouble(price));
						setBuyAmount(Integer.parseInt(amount));
						
						scan.nextLine();
						continue;
					} else if (sel.equals("n")) {
						System.out.printf("%22s거래가 취소되었습니다.\n", " ");
						System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
						index = -1;
						scan.nextLine();
						continue;
					} else {
						System.out.printf("%22s입력이 올바르지 않습니다.\n", " ");
						System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
						index = -1;
						scan.nextLine();
						continue;
					}
				

			}

		} catch (Exception e) {
			System.out.println("Exchange");
			e.printStackTrace();
		}
		
		
		
	}//Exchange

	private static Object getFx(Object obj, String element) {
		return ((JSONObject)obj).get(element);
	}

	public static double getBuyPrice() {
		return buyPrice;
	}

	public static int getBuyAmount() {
		return buyAmount;
	}

	public static void setBuyPrice(double buyPrice) {
		Exchange.buyPrice = buyPrice;
	}

	public static void setBuyAmount(int buyAmount) {
		Exchange.buyAmount = buyAmount;
	}
	
	
}
