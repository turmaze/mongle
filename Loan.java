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

import com.mongle.service.invest.DepositSaving.InfoProduct;

public class Loan {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 대출 정보 담을 리스트
		int index = 0;
		boolean loop = true;

		while (loop) {
			table = researchLoan(table, index); //대출 검색 메서드

			System.out.println("대출 신청(번호로 선택)");
			System.out.println("9. 다음 페이지");
			System.out.println("0. 이전으로");

			while (loop) {
				System.out.println("선택(번호) : ");
				String sel = scan.nextLine();

				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= (table.size() > 7 ? 7 : table.size())) {
					System.out.println("가입 화면으로 이동합니다.");
					loop = false;
				} else if (sel.equals("0")) {
					System.out.println("이전 화면으로 이동합니다.");
					loop = false;
				} else if (sel.equals("9")) {
					index += 7;
					break;
				} else {
					System.out.println("올바른 번호를 입력해주세요.");
				}
			} // while
		} // while
	}// main

	private static List<InfoProduct> researchLoan(List<InfoProduct> table, int index) { //대출 검색기
		String header = "+---+-------------------+---------------+-------+";
		System.out.println(header);
		System.out.println("|번호|       금융사        |      상품명     | 평균금리 |");
		System.out.println(header);
		try {
			URL url = new URL(
					"https://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json?auth=e06ef138c067a4ff1a42504d0fefda36&topFinGrpNo=020000&pageNo=1");

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
				double rate = 0;

				for (Object opt : option) {
					if (((JSONObject) opt).get("fin_prdt_cd").equals(code)) {
						rate = Double.parseDouble(((JSONObject) opt).get("crdt_grad_avg").toString());
						break;
					}
				}
				InfoProduct d = new InfoProduct(bank, title, "", rate, 0);
				table.add(d);
			}
			printAsciiTable(table, index);

		} catch (Exception e) {
			System.out.println("emain");
			e.printStackTrace();
		}
		System.out.println(header);

		return table;
	}

	public static void printAsciiTable(List<InfoProduct> data, int index) { // 표에 반복해서 출력하는 메서드
		int printNum = 1;
		for (int i = index; i < index + 7; i++) {
			System.out.printf("|%-3d|%-10s\t|%-10s\t|%6s%%|\n", printNum, data.get(i).getBank(), data.get(i).getTitle(),
					data.get(i).getRate());
			printNum++;
		}
	}
}
