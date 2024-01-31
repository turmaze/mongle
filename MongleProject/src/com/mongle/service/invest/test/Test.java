package com.mongle.service.invest.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {

	public static void main(String[] args) {

		ArrayList<Depo> table = new ArrayList<>();
		System.out.println("+--------------+----------------+-------+-------+-------+");
		System.out.println("|     금융사     |      상품명      |  기간  | 기본금리 | 최고금리 |");
		System.out.println("+--------------+----------------+-------+-------+-------+");

		try {
			URL url = new URL(
					"https://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=efebe52a92c17a5bcee4c231f829a349&topFinGrpNo=020000&pageNo=1");

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

				Depo d = new Depo(bank, title, period, rate, maxRate);
				table.add(d);
				printAsciiTable(table);

				// System.out.printf("[%s]%s-%s개월-%.1f%%-%.1f%%\n", bank, title, period, rate,
				// maxRate);
				// System.out.println();
			}

		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}

	}

	public static void printAsciiTable(ArrayList<Depo> data) {
		// Print header
		String header = "+--------------+----------------+-------+-------+-------+";

		// Print data rows with custom spacing
		for (Depo d : data) {
			System.out.printf("|%-9s|%-10s|%-6s개월|%-7s%%|%-7s%%|\n", d.getBank(), d.getTitle(), d.getPeriod(),
					d.getRate(), d.getMaxRate());
		}

		System.out.println(header);
	}

	static class Depo {
		private String bank;
		private String title;
		private String period;
		private double rate;
		private double maxRate;

		public Depo() {

		}

		public Depo(String bank, String title, String period, double rate, double maxRate) {
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

		@Override
		public String toString() {
			return String.format("[bank=%s, title=%s, period=%s, rate=%s, maxRate=%s]", bank, title, period, rate,
					maxRate);
		}

	}
}
