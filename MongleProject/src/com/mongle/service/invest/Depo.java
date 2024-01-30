package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Depo {

	public static void getDeposit() {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); //예금 정보 담을 리스트
		boolean loop = true;

		while (loop) {
			table = searchDeposit(scan, table); //예금 검색 메서드

			System.out.printf("%22s예금 가입(번호로 선택)\n", " ");
			System.out.printf("%22s9. 다시 검색하기\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			while (loop) {
				System.out.printf("%22s선택(번호) : ", " ");
				int sel = scan.nextInt();
				scan.nextLine();

				if (sel >= 1 && sel <= table.size()) {
					System.out.printf("%22s가입 화면으로 이동합니다.\n", " ");
					loop = false;
				} else if (sel == 0) {
					loop = false;
				} else if (sel == 9) {
					break;
				} else {
					System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
				}
			}//while
		}//while
	}

	private static List<InfoProduct> searchDeposit(Scanner scan, List<InfoProduct> table) {
		System.out.printf("%22s예금 검색(은행 이름) : ", " ");
		String name = scan.nextLine();

		
		String header = "+---+--------------+--------------------+-------+-------+-------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|     금융사     |        상품명        |  기간  | 기본금리 | 최고금리 |\n", " ");
		System.out.printf("%22s%s\n", " ", header);

		try {
			URL url = new URL(
					"https://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json?auth=e06ef138c067a4ff1a42504d0fefda36&topFinGrpNo=020000&pageNo=1");

			// JSON 결과
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String result = bf.readLine();
			bf.close();

			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(result);
			JSONObject res = (JSONObject) root.get("result");

			JSONArray list = (JSONArray) res.get("baseList");
			JSONArray option = (JSONArray) res.get("optionList");

			for (Object product : list) {
				if (((String) ((JSONObject) product).get("kor_co_nm")).contains(name)) {
					String bank = (String) ((JSONObject) product).get("kor_co_nm");
					String title = (String) ((JSONObject) product).get("fin_prdt_nm");
					String code = (String) ((JSONObject) product).get("fin_prdt_cd");
					String period = "";
					double rate = 0;
					double maxRate = 0;

					for (Object opt : option) {
						if (((JSONObject) opt).get("fin_prdt_cd").equals(code)) {

							period = (String) ((JSONObject) opt).get("save_trm");
							rate = Double.parseDouble(((JSONObject) opt).get("intr_rate").toString());
							maxRate = Double.parseDouble(((JSONObject) opt).get("intr_rate2").toString());
							break;
						}
					}

					InfoProduct d = new InfoProduct(bank, title, period, rate, maxRate);
					table.add(d);
				}
			}
			printAsciiTable(table);
			System.out.printf("%22s%s\n", " ", header);

		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}
		return table;
	}

	public static void printAsciiTable(List<InfoProduct> data) { //표에 반복해서 출력하는 메서드
		for (int i=0;i<data.size();i++) {
			
			System.out.printf("%22s|%-3d|%-12s|%-15s\t|%5s개월|%6s%%|%6s%%|\n", " ", i+1, 
								data.get(i).getBank(), data.get(i).getTitle(), 
								data.get(i).getPeriod(), data.get(i).getRate(), data.get(i).getMaxRate());
		}
	}
	
	static class InfoProduct { // 상품 정보 클래스
		private String bank;
		private String title;
		private String period;
		private double rate;
		private double maxRate;

		public InfoProduct(String bank, String title, String period, double rate, double maxRate) {
			this.bank = bank;
			this.title = title;
			this.period = period;
			this.rate = rate;
			this.maxRate = maxRate;
		}

		public String getBank() {
			return bank;
		}

		public String getTitle() {
			return title;
		}

		public String getPeriod() {
			return period;
		}

		public double getRate() {
			return rate;
		}

		public double getMaxRate() {
			return maxRate;
		}

	}
	
}
