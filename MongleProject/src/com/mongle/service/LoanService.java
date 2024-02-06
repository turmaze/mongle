package com.mongle.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.service.loan.ManageLoan;
import com.mongle.view.MongleVisual;
/**
 * 대출 관리 메인 메뉴 클래스
 */
public class LoanService {
		/**
		 * 대출 관리 메인 메뉴 출력
		 */
	public static int loanMenu() {
		boolean loop = true;
		int r = -1;
		ManageLoan.openLoan();
		while(loop) {
			MongleVisual.menuHeader("대출 관리");
			System.out.println();
			System.out.printf("%22s 1.대출정보\n", " ");
			System.out.printf("%22s 2.대출연장\n", " ");
			System.out.printf("%22s 3.중도 상환 수수료 계산기\n", " ");
			System.out.printf("%22s 0.이전으로\n", " ");
			System.out.printf("%22s번호를 입력하세요:", " ");
			
			Scanner sc = new Scanner(System.in);
			String sel = sc.nextLine();
			
			if(sel.equals("1")) {
				MongleVisual.menuHeader("대출 정보 화면");
				r = ManageLoan.checkLoan();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			}else if(sel.equals("2")) {
				MongleVisual.menuHeader("대출 연장 화면");
				r = ManageLoan.extension();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			}else if(sel.equals("3")) {
				MongleVisual.menuHeader("중도 상환 수수료 계산기");
				r=ManageLoan.calculrator();
				if(r==9) {
					return 9;
				} else {
					continue;
				}
			}else if(sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			
			}else if(sel.equals("9")){
				MongleVisual.menuMove("홈 화면");
				return 9;
			}else {
				MongleVisual.wrongInput();
			}
		}
		return 0;
	}
	
}
