package com.mongle.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.asset.DepoSave;
import com.mongle.asset.GiveAccount;
import com.mongle.resource.BankAccount;
import com.mongle.resource.DataAccount;
import com.mongle.view.MongleVisual;

public class AssetService {

	public static void main(String[] args) {
		GiveAccount.load();
		//DataAccount.load();
		assmenu();

	}

	public static void assmenu() {
		// 메뉴 헤더 화면
		boolean loop = true;
		while (loop) {
			MongleVisual.menuHeader("계좌 관리");
			System.out.println();
			System.out.printf("%22s 1.계좌개설\n", " ");
			System.out.printf("%22s 2.계좌조회\n", " ");
			System.out.printf("%22s 3.계좌해지\n", " ");
			System.out.printf("%22s 4.이자계산기\n", " ");
			System.out.printf("%22s 9.홈으로\n", " ");
			System.out.printf("%22s 0.이전으로\n", " ");
			System.out.printf("%22s번호를 입력하세요:", " ");

			Scanner sc = new Scanner(System.in);
			String sel = sc.nextLine();
			AssetService ass = new AssetService();

			if (sel.equals("1")) {
				DepoSave.depoSaveService();
			} else if (sel.equals("2")) {
				ass.checkDepo();
			} else if (sel.equals("3")) {

			} else if (sel.equals("4")) {

			} else if (sel.equals("9")) {

			} else if (sel.equals("0")) {

			} else {
				loop = false;
			}
		}

	}// choice

	public static void openDepo(String bankDepo, String titleDepo) {

		String AccountNumber = "";

		for (BankAccount acc : GiveAccount.glist) {
			if (bankDepo.contains(acc.getBankDepo())) {
				AccountNumber = acc.getAccountNumber();
				GiveAccount.glist.remove(acc);
				break;
			}
		}
		DataAccount.list.add(new BankAccount(bankDepo, titleDepo, AccountNumber, 0));

	}// OpenDeposit

	public static void checkDepo() {
		// 헤더 출력
				String header = "+---+--------------+-----------------------+-----------------+-------------+";
				System.out.printf("%22s%s\n", " ", header);
				System.out.printf("%22s|번호|     금융사     |         상품명    \t|      계좌번호      |     잔액     |\n", " ");
				System.out.printf("%22s%s\n", " ", header);
				printAsciiTable(DataAccount.list);
				System.out.printf("%22s%s\n", " ", header);
				// 데이터 출력
//			    DataAccount.list.forEach(s -> {
//			        // 각 열의 데이터 출력 포맷 지정
//			        String formattedAmount = String.format("%,d원", s.getDepositAmount());
//			        // 정렬된 형태로 출력
//			        System.out.printf("%-15s %-20s %-15s %s\n", s.getBankDepo(), s.getTitleDepo(), s.getAccountNumber(), s.getDepositAmount());
//			    });
			}

	public static void pause() {

		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("계속하려면 엔터를 입력하세요.");
		sc.nextLine();
		System.out.println(); // 위 아래 구분 위해 엔터 하나씩 넣어놓음
	}
	
	public static void printAsciiTable(ArrayList<BankAccount> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("%22s|%-3d|%-13s|%-16s\t|%12s    |%,6d원\t|\n", " ", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}
}// class
