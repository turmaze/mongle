package com.mongle.service.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongle.service.invest.InfoProduct;
import com.mongle.view.MongleVisual;
import com.mongle.service.AssetService;
import com.mongle.service.asset.DepoSave;

/**
 * 이자계산 클래스
 */
public class DepoCalculator {
	private static String bankDepo;
	private static String titleDepo;
	private static double rate;

	/**
	 * 상품별 이자 계산해주는 메서드
	 * 
	 * @param bank  은행명
	 * @param title 상품명
	 * @param rate  이자율
	 */
	public DepoCalculator(String bank, String title, double rate) {
		// 이자 계산 생성자
		this.bankDepo = bank;
		this.titleDepo = title;
		this.rate = rate;
	}

	/**
	 * APi연동 예적금 상품 검색 메서드
	 * 
	 * @return 이자계산 기업명 + 상품명 + 기본금리
	 */
	public static int depoSaveService() {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 예적금 정보 담을 리스트
		boolean loop = true;

		while (loop) {
			MongleVisual.menuHeader("이자계산기");
			table = DepoSave.searchDepoSave(table); // 예적금 검색

			System.out.println();
			System.out.printf("%22s이자 계산(상품 번호 선택)\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			while (loop) {
				MongleVisual.choiceGuidePrint();
				String sel = scan.nextLine();
				try {
					if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= (table.size() > 7 ? 7 : table.size())) {
						DepoCalculator acc = new DepoCalculator(table.get(Integer.parseInt(sel) - 1).getBank(),
								table.get(Integer.parseInt(sel) - 1).getTitle(),
								table.get(Integer.parseInt(sel) - 1).getRate());
						;
						MongleVisual.menuMove("이자 계산 화면");
						MongleVisual.menuHeader("이자계산기");
						System.out.printf("%22s%s / %s / 금리: %.1f\n", " ", acc.bankDepo, acc.titleDepo, acc.rate);
						cal(acc.bankDepo, acc.titleDepo, acc.rate);
						MongleVisual.menuMove("이전 화면");
						return 0;
					} else if (sel.equals("")) {
						break;
					} else if (sel.equals("9")) {
						MongleVisual.menuMove("홈 화면");
						return 9;
					} else if (sel.equals("0")) {
						MongleVisual.menuMove("이전 화면");
						return 0;
					} else {
						MongleVisual.wrongInput();
					}
				} catch (NumberFormatException e) {
					MongleVisual.wrongInput();
				}
			} // while
		} // while
		return 0;
	}

	/**
	 * 
	 * @param bankDepo2  은행명
	 * @param titleDepo2 상품명
	 * @param rate2      이자율
	 */
	private static void cal(String bankDepo2, String titleDepo2, double rate2) {
		// 원금 X 해당 이율(3%) X 예입일로부터 지급 전일까지의 일수/365
		Scanner sc = new Scanner(System.in);
//		System.out.printf("%22s선택(번호) : ", " ");
//		String sel = scan.nextLine();
		System.out.println();
		System.out.printf("%22s매달 납입 금액 :", " ");
		int maturit = sc.nextInt();

		System.out.printf("%22s만기 기간(개월) :", " ");
		int period = sc.nextInt();

		int mat = period * maturit;
		int result = 0;

		result = (int) (mat * (rate2 / 100));
		System.out.println();
		System.out.printf("%22s매달 %,d만원 %d개월 입금 시 이자: %,d원\n", " ", maturit, period, result);
		System.out.printf("%22s총 수령받으실 금액: %,d원\n", " ", mat + result);

	}

}// class
