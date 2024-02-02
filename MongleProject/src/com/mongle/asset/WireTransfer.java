package com.mongle.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.view.MongleVisual;

public class WireTransfer {

	public static void extracted() {
		Scanner sc = new Scanner(System.in);

		System.out.println(BankAccount.list.get(0).getAccountNumber());

		while (true) {
	//		menuheader("송	금");
			System.out.println("1. 송금하기");
			System.out.println("2. 송금예약");
			System.out.println("3. 더치페이");
			System.out.println("4. 종료하기");
			System.out.print("메뉴 번호를 선택하세요: ");
			int choice = sc.nextInt();

			if (choice >= 1 && choice <= 4) {
				switch (choice) {
				case 1:
//					menuheader("1. 송금");
				
//					transferMoney(BankAccount.list.get(0).getDepositAmount());
//					System.out.println(BankAccount.list.toString());
					break;
				case 2:
	//				menuheader("2. 송금 예약");

					break;
				case 3:
//					menuheader("3. 더치페이");

					break;
				case 4:
					System.out.println("4. 프로그램을 종료합니다.");
					return;
				}

			} else {
				System.out.println("1부터 4까지의 숫자를 입력하세요.");
			}
		}
	}

        private static void transferMoney(int depositAmount ) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		
		System.out.println("보내실 금액을 입력하여 주세요:");
		int b = sc.nextInt();
		if(depositAmount >= b){
			result=depositAmount-b;
			BankAccount.list.set();
		} 
		
	
	}

	private static void transferMoney(String titleName) {
		// 메뉴 헤더 화면
		System.out.printf("%22s===================================\n", " ");
		System.out.printf("%40s\n", titleName);
		System.out.printf("%22s===================================\n", " ");

	}
	public static int transaction(int price, int num) {
		Scanner scan = new Scanner(System.in);
		
		MongleVisual.pusher();

		String header = "+---+---------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s송금할 계좌를 선택해 주세요.\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		boolean loop = true;

		while (loop) {
			System.out.printf("%22s선택(번호) : ", " ");
			String sel = scan.nextLine();
			int totalPrice = price * num;
			
			if (sel.equals("0")) {
				return 0;
			}

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {

					for (BankAccount acc : BankAccount.list) {
						if (acc.getAccountNumber()
								.equals(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber())) {
							if (acc.getDepositAmount() > totalPrice) {

								int rest = acc.getDepositAmount() - totalPrice;
								BankAccount.list.set(BankAccount.list.indexOf(acc), new BankAccount(acc.getBankDepo(),
										acc.getTitleDepo(), acc.getAccountNumber(), rest));
								
								System.out.println();
								System.out.printf("%22s주문가격 %,d원(시장가) / 주문 수량 : %s\n", " ", price, num);
								System.out.printf("%22s거래가 완료되었습니다.\n", " ");
								System.out.printf("%22s거래 후 잔액은 %,d원입니다.\n", " ", rest);
								System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
								scan.nextLine();
								loop = false;
							} else if (acc.getDepositAmount() < totalPrice) {
								System.out.printf("%22s계좌의 잔액이 부족합니다.\n", " ");
								System.out.printf("%22s다시 선택해주세요.\n", " ");
							}
						}
					}
				} else {
					System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
				}
			} catch (NumberFormatException e) {
				System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
			}
		}
		return 0;

	}
	public static void print(List<BankAccount> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}
}
