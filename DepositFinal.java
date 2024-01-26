package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DepositFinal {
	public static void main(String[] args) {

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

				System.out.printf("[%s]%s-%s개월-%.1f%%-%.1f%%\n", bank, title, period, rate, maxRate);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}
	}
}
