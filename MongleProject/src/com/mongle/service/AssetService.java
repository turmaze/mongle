package com.mongle.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.asset.AccountConnection;
import com.mongle.asset.DepoCalculator;
import com.mongle.asset.DepoSave;
import com.mongle.asset.GiveAccount;
import com.mongle.view.MongleVisual;

public class AssetService {


	public static int assmenu() {
		// 메뉴 헤더 화면
		boolean loop = true;
		int r = -1;
		while (loop) {
			MongleVisual.menuHeader("계좌 관리");
			System.out.println();
			System.out.printf("%22s 1.계좌개설\n", " ");
			System.out.printf("%22s 2.계좌조회\n", " ");
			System.out.printf("%22s 3.연동하기\n", " ");
			System.out.printf("%22s 4.이자계산기\n", " ");
			System.out.printf("%22s 9.홈으로\n", " ");
			System.out.printf("%22s 0.이전으로\n", " ");
			System.out.printf("%22s번호를 입력하세요:", " ");

			Scanner sc = new Scanner(System.in);
			String sel = sc.nextLine();
			//
			if (sel.equals("1")) {
				r = DepoSave.depoSaveService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("2")) {
				r = checkDepo();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("3")) {
				//연동
				AccountConnection.connection();
			} else if (sel.equals("4")) {
				//이자 계산기
				DepoCalculator.depoSaveService();
				
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			} else {
				loop = false;
			}
		}
		return 0;

	}// choice

	public static int checkDepo() {
		// 헤더 출력
		Scanner scan = new Scanner(System.in);
		int r = -1;
		
		while (true) {
		
		String header = "+---+---------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		printAsciiTable(BankAccount.list); //json 에서 가져온 데이터
		System.out.printf("%22s%s\n", " ", header);
		
		
		System.out.printf("%22s거래 내역 확인(계좌 선택)\n", " ");
		System.out.printf("%22s9. 홈으로\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		System.out.println();
		System.out.printf("%22s선택(번호) :", " ");
		
		String sel = scan.nextLine();
		if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= BankAccount.list.size()) {
			History.check(BankAccount.list.get(Integer.parseInt(sel) - 1).getAccountNumber());
			if (r == 9) {
				return 9;
			} else {
				continue;
			}
		} else if (sel.equals("9")) {
			return 9;
		} else if (sel.equals("0")) {
			return 0;
		}
		
		}
		

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
			System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}

}// class
