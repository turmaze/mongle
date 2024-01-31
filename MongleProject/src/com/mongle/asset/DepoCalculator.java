package com.mongle.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongle.service.invest.DepoSave;
import com.mongle.service.invest.InfoProduct;

public class DepoCalculator {
	private static String bankDepo;
	private static String titleDepo;
	private static double rate;

	public DepoCalculator(String bank, String title, double rate) {
		// 이자 계산 생성자
		this.bankDepo = bank;
		this.titleDepo = title;
		this.rate = rate;
	}


	public static void depoSaveService() {
		Scanner scan = new Scanner(System.in);
		List<InfoProduct> table = new ArrayList<>(); // 예적금 정보 담을 리스트
		boolean loop = true;

		while (loop) {
			table = DepoSave.searchDepoSave(scan, table); // 예적금 검색

			System.out.println();
			System.out.printf("%22s이자 계산(상품 번호 선택)\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();

			while (loop) {
				System.out.printf("%22s선택(번호) : ", " ");
				String sel = scan.nextLine();
				try {
					if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= (table.size() > 7 ? 7 : table.size())) {
						DepoCalculator acc = new DepoCalculator(table.get(Integer.parseInt(sel) - 1).getBank(),
								table.get(Integer.parseInt(sel) - 1).getTitle(),
								table.get(Integer.parseInt(sel) - 1).getRate());
						;
						System.out.printf("%22s이자 계산 화면으로 이동합니다.\n", "  ");
						System.out.println();
						System.out.printf("%22s%s / %s / 금리: %.1f\n", " ", acc.bankDepo, acc.titleDepo, acc.rate);
						cal(acc.bankDepo, acc.titleDepo, acc.rate);
						
						
						
					} else if (sel.equals("")) {

						break;
					} else if (sel.equals("9")) {
						System.out.printf("%22s홈 화면으로 이동합니다.\n", " ");
						loop = false;
					} else if (sel.equals("0")) {
						System.out.printf("%22s이전 화면으로 이동합니다.\n", " ");
						AssetService.assmenu();
						System.out.println();
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
		// 만기금액 * 이자/100
		result = (int) (mat * (rate2 / 100));
		System.out.println();
		System.out.printf("%22s매달 %,d만원 %d개월 입금 시 이자: %,d원\n", " ", maturit, period, result);
		System.out.printf("%22s총 수령받으실 금액: %,d원\n", " ", mat + result);

	}


}// class
