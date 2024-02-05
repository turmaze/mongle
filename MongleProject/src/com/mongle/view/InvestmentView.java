package com.mongle.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.resource.Investment;
import com.mongle.service.InvestService;

public class InvestmentView {

	public static int addmenu() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while (loop) {

			MongleVisual.menuHeader("투자 관리");

			System.out.printf("%22s1. 주식\n", " ");
			System.out.printf("%22s2. 대출\n", " ");
			System.out.printf("%22s3. 금 상품\n", " ");
			System.out.printf("%22s4. 환전\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			System.out.printf("%22s사용자 입력 : ", " ");
			String num = scan.nextLine();

			if (num.equals("1")) { // 주식
				stocksave();
			} else if (num.equals("2")) { // 대출
				loansave();
			} else if (num.equals("3")) {// 금
				goldsave();
			} else if (num.equals("4")) { // 환전
				Exchangesave();

			} else if (num.equals("9")) { // 홈으로
				return 9;

			} else if (num.equals("0")) { // 이전으로
				return 0;

			} else {
				loop = false;

			}
		}
		return 0;

	}

	private static void loansave() { // 대출

		Scanner scan = new Scanner(System.in);
		boolean loop2 = true;
		System.out.println();
		System.out.printf("%22s상세 보기\n", " ");

		printAsciiTableLoan(Investment.list, "대출");
		System.out.println();
		
		while (loop2) {

			System.out.printf("%22s 옵션 : \n", " ");
			System.out.printf("%22s1. 판매 \n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();
			System.out.printf("%22s사용자 입력 : ", " ");
			String num4 = scan.nextLine();

			if (num4.equals("1")) {
				// 삭제 후 상세 불러오기
				System.out.printf("%22s판매하고 싶은 상품 번호를 입력해 주세요 :", " ");
				int removeN = scan.nextInt();
				System.out.printf("%22s정말로 판매 하시겠습니까?(y/n)\n", " ");
				System.out.printf("%22s사용자 입력 : ", " ");
				while (loop2) {
					String num = scan.nextLine();
					if (num.equals("y")) {

						transaction(removeN, "대출"); // 계좌고르기
						removeLoanInvestmentByJ(Investment.list, removeN, "대출");
						System.out.printf("%22s상세보기\n ", " ");
						printAsciiTableLoan(Investment.list, "대출");
						break;
					} else if (num.equals("n")) {
						System.out.println("이전으로 돌아갑니다.");
						break;
					}
				}
				//break;
			} else if (num4.equals("0")) {
				loop2= false;

			}
		}

	}

	private static void Exchangesave() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		System.out.println();
		System.out.printf("%22s상세 보기\n", " ");

		printAsciiTable(Investment.list, "환전");
		System.out.println();
		while (loop) {

			System.out.printf("%22s 옵션 : \n", " ");
			System.out.printf("%22s1. 판매 \n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();
			System.out.printf("%22s사용자 입력 : ", " ");
			String num4 = scan.nextLine();

			if (num4.equals("1")) {
				// 삭제 후 상세 불러오기
				System.out.printf("%22s판매하고 싶은 상품 번호를 입력해 주세요 :", " ");
				int removeN = scan.nextInt();
				System.out.printf("%22s정말로 판매 하시겠습니까?(y/n)\n", " ");
				System.out.printf("%22s사용자 입력 : ", " ");
				while (loop) {
					String num = scan.nextLine();
					if (num.equals("y")) {

						transaction(removeN, "환전");
						break;
					} else if (num.equals("n")) {
						System.out.println("이전으로 돌아갑니다.");
						break;
					}
				}
				removeLoanInvestmentByJ(Investment.list, removeN, "환전");
				System.out.printf("%22s상세보기\n ", " ");
				printAsciiTable(Investment.list, "환전");

				break;

			} else if (num4.equals("0")) {
				loop = false;

			}
		}

	}

	private static void goldsave() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		System.out.println();
		System.out.printf("%22s상세 보기\n", " ");

		printAsciiTable(Investment.list, "금");

		while (loop) {

			System.out.printf("%22s 옵션 : \n", " ");
			System.out.printf("%22s1. 판매 \n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();
			System.out.printf("%22s사용자 입력 : ", " ");
			String num3 = scan.nextLine();

			if (num3.equals("1")) {
				// 삭제 후 상세 불러오기
				System.out.printf("%22s판매하고 싶은 상품 번호를 입력해 주세요 :", " ");
				int removeN = scan.nextInt();
				System.out.printf("%22s정말로 판매 하시겠습니까?(y/n)\n", " ");
				System.out.printf("%22s사용자 입력 : ", " ");
				while (loop) {
					String num = scan.nextLine();
					if (num.equals("y")) {

						transaction(removeN, "금");
						break;
					} else if (num.equals("n")) {
						System.out.println("이전으로 돌아갑니다.");
						break;
					}
				}
				removeLoanInvestmentByJ(Investment.list, removeN, "금");
				System.out.printf("%22s상세보기\n ", " ");
				printAsciiTable(Investment.list, "금");

				break;
			} else if (num3.equals("0")) {
				loop = false;

			}
		}

	}

	private static void stocksave() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		boolean loop2 = true;
		System.out.println();
		System.out.printf("%22s상세 보기\n", " ");

		printAsciiTableStock(Investment.list, "주식");

		while (loop) {
			System.out.println();
			System.out.printf("%22s 옵션 : \n", " ");
			System.out.printf("%22s1. 매수\n", " ");
			System.out.printf("%22s2. 일괄매도\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();
			System.out.printf("%22s사용자 입력 : ", " ");
			String num2 = scan.nextLine();

			if (num2.equals("1")) { //// 매수

				System.out.printf("%22s매수할 상품번호를 선택하시오: ", " ");
				int removeN = scan.nextInt();

				System.out.printf("%22s 수량을 입력해 주세요 : ", " ");
				int num = scan.nextInt();

				int total = stockcare(Investment.list, removeN, "주식", num); // 합쳐진 수..
				transactionStock(removeN, "주식", num); // 계좌 골라서 넣기..

				// 매수하고 상세보기 불러오기
				System.out.printf("%22s 상세보기\n", " ");
				printAsciiTable222(Investment.list, "주식", total, removeN); // 리스트..

			} else if (num2.equals("2")) { // 일괄매도

				System.out.printf("%22s일괄매도할 상품번호를 선택하시오:", " ");
				int removeN = scan.nextInt();

				System.out.printf("%22s정말로 매도하시겠습니까? (y/n) : \n", " ");
				System.out.printf("%22s사용자 입력 : ", " ");
				while (loop2) {
					String answer = scan.nextLine();
					if (answer.equals("y")) {
						transaction(removeN, "주식");

						removeLoanInvestmentByJ(Investment.list, removeN, "주식");
						printAsciiTableStock(Investment.list, "주식");
						break;
					} else if (answer.equals("n")) {
						MongleVisual.menuMove("이전 화면");
						break;
					}
				}
			} else if (num2.equals("0")) {
				loop = false;
			}
		}

	}

	public static void printAsciiTableStock(ArrayList<Investment> data, String invest) { // 표에 반복해서 출력하는 메서드
		int j = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15f원\t|%,15d개|\n", " ",
						j + 1, 
						data.get(i).getRealTitle(),
						data.get(i).getBankDepo(),
						data.get(i).getPrice(), 
						data.get(i).getAmount());
				j++;
			}

		}
	}
	
	public static void printAsciiTableLoan(ArrayList<Investment> data, String invest) { // 표에 반복해서 출력하는 메서드
		int j = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				System.out.printf("%22s|%-3s|%-14s\t|%-18s\t|%15s\t|\n", " ",
						j + 1, 
						data.get(i).getRealTitle(),
						data.get(i).getBankDepo(),
						data.get(i).getTitleDepo());
				
				j++;
			}

		}
	}


	public static void printAsciiTable(ArrayList<Investment> data, String invest) { // 표에 반복해서 출력하는 메서드
		int j = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {

				System.out.printf("%22s|%-3d|%-14s\t|%,15f원|%,15d개\n", " ", 
						j + 1, 
						data.get(i).getRealTitle(),
						data.get(i).getPrice(), 
						data.get(i).getAmount());
				j++;
			}
		}
	}

	public static void printAsciiTable222(ArrayList<Investment> data, String invest, int total, int removeN) { // 표에
																												// 반복해서
																												// 출력하는
																												// 메서드

		int j = 0;
		int printNum = 1;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				if (printNum == removeN) {
					data.get(i).setAmount(total);
				}
				System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", printNum,
						data.get(i).getTitleDepo(), data.get(i).getBankDepo(), data.get(i).getPrice(),
						data.get(i).getAmount());
				printNum++;
			}
			j++;
		}

	}

	private static void removeLoanInvestmentByJ(ArrayList<Investment> data, int removeN, String invest) {
		int j = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					data.remove(i);
					return; // 삭제 후 바로 메서드 종료
				}
			}
		}
	}

	private static int stockcare(ArrayList<Investment> data, int removeN, String invest, int num) {
		int j = 0;
		int care = 0;
		int money = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					care = data.get(i).getAmount() + num;
					// money = (int)data.get(i).getPrice() * care ;

				}
			}
		}
		return care;
	}

	private static int totalmoney(ArrayList<Investment> data, int removeN, String invest) {
		int j = 0;
		int total = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					total = (int) data.get(i).getPrice() * data.get(i).getAmount();
				}
			}
		}
		return total;
	}

	private static int stockcare2(ArrayList<Investment> data, int removeN, String invest, int num) {
		int j = 0;
		int money = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())) {
				j++;
				if (j == removeN) {
					// care = data.get(i).getAmount()+num;
					money = (int) data.get(i).getPrice() * num;
				}
			}
		}
		return money;
	}

	public static int transaction(int removeN, String invest) {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		MongleVisual.pusher();

		String header = "+---+---------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s 계좌를 선택해주세요.\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");

		while (loop) {
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();
			int totalPrice = totalmoney(Investment.list, removeN, invest);
			if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			}

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {
					BankAccount acc = BankAccount
							.findAccount(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber());
					if (totalPrice > 0) {
						History.make(acc.getAccountNumber(), invest + " 수익", totalPrice);
						System.out.println();

						System.out.printf("%22s판매가 완료되었습니다.\n", " ");
						MongleVisual.stopper();
						loop = false;
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

	public static int transactionStock(int removeN, String invest, int num) {
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
		System.out.printf("%22s 계좌를 선택해주세요.\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		boolean loop = true;

		while (loop) {
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();
			int totalPrice = stockcare2(Investment.list, removeN, "주식", num);
			System.out.println(totalPrice);

			if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			}

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {
					BankAccount acc = BankAccount
							.findAccount(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber());
					if (acc.getDepositAmount() > totalPrice) {
						History.make(acc.getAccountNumber(), invest + " 매수", -totalPrice);
						System.out.println();

						System.out.printf("%22s매수가 완료 됐습니다.\n", " ");
						MongleVisual.stopper();
						loop = false;
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
			System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());
		}
	}

}