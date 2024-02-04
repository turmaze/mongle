package com.mongle.view;

import java.util.Scanner;

import com.mongle.asset.DepoSave;
import com.mongle.service.AssetService;

public class AssertManagementView {

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
				MongleVisual.menuHeader("계좌 관리 화면");
				r = AssetService.assmenu();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}

			} else if (sel.equals("2")) {
				MongleVisual.menuHeader("투자 관리 화면");
				r = InvestmentView.addmenu();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("3")) {
				MongleVisual.menuHeader("대출 관리 화면");
///////////////////////////대출 관리 기능 추가
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("9")) {
				MongleVisual.menuHeader("홈 화면");
				return 9;
			} else if (sel.equals("0")) {
				MongleVisual.menuHeader("이전 화면");
				return 0;
			} else {
				MongleVisual.wrongInput();
			}

		}
		return 0;

	}
}
