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

public class DepositSaving {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 예적금 정보 담을 리스트
		boolean loop = true;

		while (loop) {
			table = searchDepoSave(scan, table); // 예적금 검색

			System.out.println("예적금 가입(번호로 선택)");
			System.out.println("9. 다시 검색하기");
			System.out.println("0. 이전으로");

			while (loop) {
				System.out.println("선택(번호) : ");
				String sel = scan.nextLine();
				try {
					if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= (table.size() > 7 ? 7 : table.size())) {
						System.out.println("가입 화면으로 이동합니다.");
						loop = false;
					} else if (sel.equals("0")) {
						System.out.println("이전 화면으로 이동합니다.");
						loop = false;
					} else if (sel.equals("9")) {
						table.clear();
						break;
					} else {
						System.out.println("올바른 번호를 입력해주세요.");
					}
				} catch (NumberFormatException e) {
					System.out.println("올바른 번호를 입력해주세요.");
				}
			} // while
		} // while
	}// main

	private static List<InfoProduct> searchDepoSave(Scanner scan, List<InfoProduct> table) { // 예적금 검색 한번에 모으기
		String apiDepo = "https://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json?auth=e06ef138c067a4ff1a42504d0fefda36&topFinGrpNo=020000&pageNo=1";
		String apiSave = "https://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=efebe52a92c17a5bcee4c231f829a349&topFinGrpNo=020000&pageNo=1";

		System.out.print("검색(은행 이름) : ");
		String name = scan.nextLine();

		String header = "+---+--------------+--------------------+-------+-------+-------+";
		System.out.println(header);
		System.out.println("|번호|     금융사     |        상품명        |  기간  | 기본금리 | 최고금리 |");
		System.out.println(header);
		table = searchAPI(table, name, apiDepo);
		table = searchAPI(table, name, apiSave);
		printAsciiTable(table);
		System.out.println(header);

		return table;
	}

	private static List<InfoProduct> searchAPI(List<InfoProduct> table, String name, String path) { // api 검색
		try {
			URL url = new URL(path);

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
		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}
		return table;
	}

	public static void printAsciiTable(List<InfoProduct> data) { // 표에 반복해서 출력하는 메서드
		if (data.size() > 7) {
			for (int i = 0; i < 7; i++) {
				System.out.printf("|%-3d|%-12s|%-15s\t|%5s개월|%6s%%|%6s%%|\n", i + 1, data.get(i).getBank(),
						data.get(i).getTitle(), data.get(i).getPeriod(), data.get(i).getRate(),
						data.get(i).getMaxRate());
			}
		} else {
			for (int i = 0; i < data.size(); i++) {
				System.out.printf("|%-3d|%-12s|%-15s\t|%5s개월|%6s%%|%6s%%|\n", i + 1, data.get(i).getBank(),
						data.get(i).getTitle(), data.get(i).getPeriod(), data.get(i).getRate(),
						data.get(i).getMaxRate());
			}
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
