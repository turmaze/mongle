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

import com.mongle.resource.Investment;
import com.mongle.view.MongleVisual;

public class Loan {
	private static String bankLoan;
	private static String titleLoan;
	
	public static ArrayList<Investment> listLoan = new ArrayList<>();

	public Loan(String bankLoan, String titleLoan) {
		super();
		this.bankLoan = bankLoan;
		this.titleLoan = titleLoan;
	}

	public String getBankLoan() {
		return bankLoan;
	}

	public void setBankLoan(String bankLoan) {
		this.bankLoan = bankLoan;
	}

	public String getTitleLoan() {
		return titleLoan;
	}

	public void setTitleLoan(String titleLoan) {
		this.titleLoan = titleLoan;
	}

	public static int loanService() {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 대출 정보 담을 리스트
		int index = 0;
		boolean loop = true;
		int r = -1;

		while (loop) {
			MongleVisual.pusher();
			
			MongleVisual.menuHeader("대출");
			
			table = searchLoan(table, index); // 대출 검색 메서드
			
			System.out.println();
			System.out.printf("%22s대출 신청(번호로 선택)\n", " ");
			System.out.printf("%22s8. 다음 페이지\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();

			while (loop) {
				System.out.printf("%22s선택(번호) : ", " ");
				String sel = scan.nextLine();
				try {
					if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= (table.size() > 7 ? 7 : table.size())) {
						Loan loan = new Loan(table.get(Integer.parseInt(sel) - 1 + index).getBank(),
											table.get(Integer.parseInt(sel) - 1 + index).getTitle());

						System.out.println();
						System.out.printf("%22s%s / %s / %s%%\n", " ", 
														loan.bankLoan, 
														loan.titleLoan,
														table.get(Integer.parseInt(sel) - 1 + index).getRate());
						applyLoan(scan, loop);
						//listLoan.add(new Investment(loan.getBankLoan(),loan.getTitleLoan(),0,0));
						Investment.list.add(new Investment("대출",loan.getBankLoan(),loan.getTitleLoan(),0,0));
						loop = false;
					} else if (sel.equals("8")) {
						index += 7;
						break;
					} else if (sel.equals("9")) {
						System.out.printf("%22s홈 화면으로 이동합니다.\n", " ");
						return 9;
					} else if (sel.equals("0")) {
						System.out.printf("%22s이전 화면으로 이동합니다.\n", " ");
						return 0;
					} else {
						System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
					}
				} catch (NumberFormatException e) {
					System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
				}
			} // while
		} // while
		return 0;
	}

	public static void applyLoan(Scanner scan, boolean loop) {
		System.out.println();
		System.out.printf("%22s대출 신청을 진행합니다.\n", " ");
		System.out.printf("%22s고객님의 전화번호로 필요 서류를 안내해드리는 문자를 발송했습니다.\n", " ");
		System.out.printf("%22s서류 구비 후 자격 심사를 위하여 가까운 대리점에 방문해주세요. 감사합니다.\n", " ");
		System.out.printf("%22s(자격 심사에 따라 대출 기한, 금리 등 세부 사항을 안내해드릴 예정입니다.)\n", " ");
		System.out.println();

		System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
		scan.nextLine();
	}

	private static List<InfoProduct> searchLoan(List<InfoProduct> table, int index) { // 대출 검색기
		String header = "+---+---------------------+-----------------------+--------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|        금융사         |          상품명         | 평균금리 |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
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
		System.out.printf("%22s%s\n", " ", header);

		return table;
	}

	public static void printAsciiTable(List<InfoProduct> data, int index) { // 표에 반복해서 출력하는 메서드
		int printNum = 1;
		for (int i = index; i < index + 7; i++) {
			System.out.printf("%22s|%-3d|%-15s\t|%-15s\t|%7s%%|\n", " ", printNum, 
												data.get(i).getBank(),
												data.get(i).getTitle(),
												data.get(i).getRate());
			printNum++;
		}
	}
}
