package com.mongle.service;

import java.util.Scanner;

import com.mongle.asset.DepoSave;
import com.mongle.service.invest.Exchange;
import com.mongle.service.invest.Fund;
import com.mongle.service.invest.Gold;
import com.mongle.service.invest.Loan;
import com.mongle.service.invest.Stock;
import com.mongle.view.MongleVisual;

public class InvestService {

	public static void main(String[] args) {
		investMenu();

	}

	public static void investMenu() {
		Scanner scan = new Scanner(System.in);
		MongleVisual.menuHeader("투자 메뉴");
		boolean loop = true;

		System.out.printf("%22s1. 주식\n", " ");
		System.out.printf("%22s2. 펀드\n", " ");
		System.out.printf("%22s3. 대출\n", " ");
		System.out.printf("%22s4. 금 상품\n", " ");
		System.out.printf("%22s5. 환전\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		System.out.println();

		while (loop) {
			System.out.printf("%22s선택(번호) :", " ");
			String sel = scan.nextLine();

			switch (sel) {
			case "1":
				Stock.stockService();
				loop = false; break;
			case "2":
				Fund.fundService();
				loop = false; break;
			case "3":
				Loan.loanService();
				loop = false; break;
			case "4":
				Gold.goldService();
				loop = false; break;
			case "5":
				Exchange.exchangeService();
				loop = false; break;
			case "0":
				System.out.printf("%22s이전 화면으로 돌아갑니다.\n", " ");
				loop = false; break;
			default:
				System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
			}

		} // while
	}

}
