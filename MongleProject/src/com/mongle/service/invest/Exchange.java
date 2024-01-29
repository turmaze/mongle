package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.view.MongleVisual;

public class Exchange {
	
	static String exchangeURL =  "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=I2RthKfXSu7z1LFQH3mxZqouqGgL3KKm&searchdate=20240126&data=AP01";
	
	public static void exchangeService(String[] args) {
		
		try {

			Scanner scan = new Scanner(System.in);
			Boolean loop = true;
			int index = -1;
			
			MongleVisual.menuHeader("환전");

			while (loop) {

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

				System.out.println("6. 다음페이지");
				System.out.println("0. 이전으로");
				System.out.println();
				System.out.print("선택: ");

				String sel = scan.nextLine();
				
				System.out.println();

				if (sel.equals("1")) {
					System.out.println("선택하신 외화: " + getFx(fx.get(index-4), "cur_nm"));
					scan.nextLine();
				} else if (sel.equals("2")) {
					System.out.println("선택하신 외화: " + getFx(fx.get(index-3), "cur_nm"));
					scan.nextLine();
				} else if (sel.equals("3")) {
					System.out.println("선택하신 외화: " + getFx(fx.get(index-2), "cur_nm"));
					scan.nextLine();
				} else if (sel.equals("4")) {
					System.out.println("선택하신 외화: " + getFx(fx.get(index-1), "cur_nm"));
					scan.nextLine();
				} else if (sel.equals("5")) {
					System.out.println("선택하신 외화: " + getFx(fx.get(index), "cur_nm"));
					scan.nextLine();
				} else if (sel.equals("6")) {
				} else {
					loop = false;
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
	
	
}
