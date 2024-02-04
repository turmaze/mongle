package com.mongle.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.service.invest.Exchange;
import com.mongle.service.invest.Fund;
import com.mongle.service.invest.Gold;
import com.mongle.service.invest.Loan;
import com.mongle.service.invest.Stock;
import com.mongle.view.MongleVisual;

public class InvestService {

	public static int investMenu() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		int r = -1;

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("투자 메뉴");

			System.out.printf("%22s1. 주식\n", " ");
			System.out.printf("%22s2. 펀드\n", " ");
			System.out.printf("%22s3. 대출\n", " ");
			System.out.printf("%22s4. 금 상품\n", " ");
			System.out.printf("%22s5. 환전\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();;

			String sel = scan.nextLine();

			switch (sel) {
			case "1":
				MongleVisual.menuHeader("주식 화면");
				r = Stock.stockService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "2":
				MongleVisual.menuHeader("펀드 화면");
				r = Fund.fundService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "3":
				MongleVisual.menuHeader("대출 화면");
				r = Loan.loanService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "4":
				MongleVisual.menuHeader("금 상품 화면");
				r = Gold.goldService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "5":
				MongleVisual.menuHeader("환전 화면");
				r = Exchange.exchangeService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "0":
				MongleVisual.menuHeader("이전 화면");
				loop = false;
				break;
			default:
				MongleVisual.wrongInput();
			}

		} // while
		return 0;
	}

	public static int transaction(String memo, int price, int num) {
		Scanner scan = new Scanner(System.in);

		MongleVisual.pusher();

		String header = "+---+-------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%s\n", header);
		System.out.printf("|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n", " ");
		System.out.printf("%s\n", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%s\n", header);
		System.out.printf("%22s주문 내역을 결제할 계좌를 선택해주세요.\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		boolean loop = true;

		while (loop) {
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();
			int totalPrice = price * num;

			if (sel.equals("0")) {
				MongleVisual.menuHeader("이전 화면");
				return 0;
			}

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {

					for (BankAccount acc : BankAccount.list) {
						if (acc.getAccountNumber().equals(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber())) {
							if (acc.getDepositAmount() > totalPrice) {

								int rest = acc.getDepositAmount() - totalPrice;
//								BankAccount.list.set(BankAccount.list.indexOf(acc), new BankAccount(acc.getBankDepo(),
//										acc.getTitleDepo(), acc.getAccountNumber(), rest));
								History.make(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber(), memo,
										-totalPrice);

								System.out.println();
								System.out.printf("%22s주문가격 %,d원(시장가) / 주문 수량 : %s\n", " ", price, num);
								System.out.printf("%22s거래가 완료되었습니다.\n", " ");
								System.out.printf("%22s거래 후 잔액은 %,d원입니다.\n", " ", rest);
								MongleVisual.stopper();
								loop = false;
							} else if (acc.getDepositAmount() < totalPrice) {
								System.out.printf("%22s계좌의 잔액이 부족합니다.\n", " ");
								System.out.printf("%22s다시 선택해주세요.\n", " ");
							}
						}
					}
				} else {
					MongleVisual.wrongInput();
				}
			} catch (NumberFormatException e) {
				MongleVisual.wrongInput();
			}
		}
		return 0;

	}

	public static void print(List<BankAccount> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}

}
