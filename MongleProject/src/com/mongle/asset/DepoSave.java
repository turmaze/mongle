package com.mongle.asset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.service.AssetService;
import com.mongle.service.InvestService;
import com.mongle.service.invest.InfoProduct;

public class DepoSave {
	private static String bankDepo;
	private static String titleDepo;

	public DepoSave(String bankDepo, String titleDepo) {
		super();
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;
	}

	public String getBankDepo() {
		return bankDepo;
	}

	public void setBankDepo(String bankDepo) {
		this.bankDepo = bankDepo;
	}

	public String getTitleDepo() {
		return titleDepo;
	}

	public void setTitleDepo(String titleDepo) {
		this.titleDepo = titleDepo;
	}

	@Override
	public String toString() {
		return String.format("DepoSave [bankDepo=%s, titleDepo=%s]", bankDepo, titleDepo);
	}

	public static void depoSaveService() {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 예적금 정보 담을 리스트
		boolean loop = true;

		while (loop) {
			table = searchDepoSave(scan, table); // 예적금 검색

			System.out.println();
			System.out.printf("%22s예적금 가입(번호로 선택)\n", " ");
			System.out.printf("%22s8. 다시 검색하기\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();

			while (loop) {
				System.out.printf("%22s선택(번호) : ", " ");
				String sel = scan.nextLine();
				try {
					if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= (table.size() > 7 ? 7 : table.size())) {
						DepoSave acc = new DepoSave(table.get(Integer.parseInt(sel) - 1).getBank(),
													table.get(Integer.parseInt(sel) - 1).getTitle());
						System.out.printf("%22s가입 화면으로 이동합니다.\n", "  ");
						System.out.println();
						System.out.printf("%22s%s / %s\n", " ", acc.bankDepo, acc.titleDepo);
						DepositSignUp.signUp(acc.bankDepo, acc.titleDepo);
						loop = false;
					} else if (sel.equals("8")) {
						table.clear();
						break;
					} else if (sel.equals("9")) {
						System.out.printf("%22s홈 화면으로 이동합니다.\n", " ");
						loop = false;
					} else if (sel.equals("0")) {
						System.out.printf("%22s이전 화면으로 이동합니다.\n", " ");
						AssetService.assmenu();
						loop = false;
					} else {
						System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
					}
				} catch (NumberFormatException e) {
					System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
				}
			} // while
		} // while
	}

	private static List<InfoProduct> searchDepoSave(Scanner scan, List<InfoProduct> table) { // 예적금 검색 한번에 모으기
		String apiDepo = "https://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json?auth=e06ef138c067a4ff1a42504d0fefda36&topFinGrpNo=020000&pageNo=1";
		String apiSave = "https://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=efebe52a92c17a5bcee4c231f829a349&topFinGrpNo=020000&pageNo=1";

		System.out.println();
		System.out.printf("%22s검색(은행 이름) : ", " ");
		String name = scan.nextLine();

		String header = "+---+----------------+----------------------------+-------+-------+-------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|      금융사      |           상품명      \t|  기간  | 기본금리 | 최고금리 |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		table = searchAPI(table, name, apiDepo);
		table = searchAPI(table, name, apiSave);
		printAsciiTable(table);
		System.out.printf("%22s%s\n", " ", header);

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
				System.out.printf("%22s|%-3d|%-14s|%-20s\t|%4s개월|%6s%%|%6s%%|\n", " ", i + 1, 
												data.get(i).getBank(),
												data.get(i).getTitle(), 
												data.get(i).getPeriod(), 
												data.get(i).getRate(),
												data.get(i).getMaxRate());
			}
		} else {
			for (int i = 0; i < data.size(); i++) {
				System.out.printf("%22s|%-3d|%-14s|%-20s\t|%4s개월|%6s%%|%6s%%|\n", " ", i + 1,
												data.get(i).getBank(),
												data.get(i).getTitle(),
												data.get(i).getPeriod(),
												data.get(i).getRate(),
												data.get(i).getMaxRate());
			}
		}
	}
}
