package com.mongle.view;

import java.util.Scanner;

import com.mongle.service.invest.InvestmentManagementService;

/***
 * 투자관리 클래스
 */
public class InvestmentManagementView {
	/***
	 * 투자 관리 메뉴
	 * 
	 * @return 메뉴 이동을 위한 변수
	 */
	public static int addmenu() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while (loop) {

			MongleVisual.menuHeader("투자 관리");

			System.out.printf("%22s1. 보유 주식 관리\n", " ");
			System.out.printf("%22s2. 보유 금 상품 관리\n", " ");
			System.out.printf("%22s3. 보유 외화 관리\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			MongleVisual.choiceGuidePrint();
			String num = scan.nextLine();

			if (num.equals("1")) { // 주식
				MongleVisual.menuMove("주식 관리 화면");
				InvestmentManagementService.stockSave();
			} else if (num.equals("2")) { // 금
				MongleVisual.menuMove("금 상품 관리 화면");
				InvestmentManagementService.goldSave();
			} else if (num.equals("3")) {// 환전
				MongleVisual.menuMove("외화 관리 화면");
				InvestmentManagementService.ExchangeSave();
			} else if (num.equals("9")) { // 홈으로
				MongleVisual.menuMove("홈 화면");
				return 9;
			} else if (num.equals("0")) { // 이전으로
				MongleVisual.menuMove("이전 화면");
				return 0;
			} else {
				loop = false;
			}
		}
		return 0;
	}

}