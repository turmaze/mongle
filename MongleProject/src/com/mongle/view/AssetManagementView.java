package com.mongle.view;

import java.util.Scanner;

import com.mongle.service.AssetService;
import com.mongle.service.LoanService;
import com.mongle.service.asset.DepoSave;

public class AssetManagementView {

	/***
	 * 자산 관리 메뉴
	 * @return
	 */
	public static int assertadd() {

		boolean loop = true;
		int r = -1;
		while (loop) {
			MongleVisual.menuHeader("자산 관리");
			System.out.println();
			System.out.printf("%22s 1.계좌 관리\n", " ");
			System.out.printf("%22s 2.투자 관리\n", " ");
			System.out.printf("%22s 3.대출 관리\n", " ");
			System.out.printf("%22s 9.홈으로\n", " ");
			System.out.printf("%22s 0.이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			Scanner sc = new Scanner(System.in);
			String sel = sc.nextLine();
			//
			if (sel.equals("1")) {
				MongleVisual.menuMove("계좌 관리 화면");
				r = AssetService.assmenu();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}

			} else if (sel.equals("2")) {
				MongleVisual.menuMove("투자 관리 화면");
				r = InvestmentManagementView.addmenu();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("3")) {
				MongleVisual.menuMove("대출 관리 화면");
				LoanService.loanMenu();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("9")) {
				MongleVisual.menuMove("홈 화면");
				return 9;
			} else if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			} else {
				MongleVisual.wrongInput();
			}

		}
		return 0;

	}
}
