package com.mongle.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.service.asset.AccountConnection;
import com.mongle.service.asset.DepoCalculator;
import com.mongle.service.asset.DepoSave;
import com.mongle.service.asset.GiveAccount;
import com.mongle.view.MongleVisual;
/**
 * 계좌 관리 클래스
 */
public class AssetService {
	/**
	 * 계좌 관리 메뉴
	 * @return 선택 번호
	 */
	public static int assmenu() {
		
		boolean loop = true;
		int r = -1;
		while (loop) {
			MongleVisual.menuHeader("계좌 관리");
			System.out.println();
			System.out.printf("%22s 1.계좌 개설\n", " ");
			System.out.printf("%22s 2.계좌 조회\n", " ");
			System.out.printf("%22s 3.계좌 연동\n", " ");
			System.out.printf("%22s 4.이자계산기\n", " ");
			System.out.printf("%22s 9.홈으로\n", " ");
			System.out.printf("%22s 0.이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			
			Scanner sc = new Scanner(System.in);
			String sel = sc.nextLine();
			//
			if (sel.equals("1")) {
				MongleVisual.menuMove("계좌 개설 화면");
				r = DepoSave.depoSaveService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("2")) {
				MongleVisual.menuMove("계좌 조회 화면");
				r = checkDepo();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("3")) {
				MongleVisual.menuMove("계좌 연동 화면");
				r = AccountConnection.connection();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			} else if (sel.equals("4")) {
				MongleVisual.menuMove("이자계산기 화면");
				r = DepoCalculator.depoSaveService();
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
	/**
	 * 보유 계좌 리스트 출력
	 * @return 선택 번호
	 */
	public static int checkDepo() {
		
		Scanner scan = new Scanner(System.in);
		int r = -1;

		while (true) {	
			MongleVisual.menuHeader("계좌 조회");
			String header =  "+----+-----------------+----------------------------------+---------+-----------+----------+";
			System.out.printf("%s\n", header);
			System.out.printf("|번호|       금융사   \t|         상품명      \t|         계좌번호\t|       잔액      |\n", " ");
			System.out.printf("%s\n", header);
			printAsciiTable(BankAccount.list);
			System.out.printf("%s\n", header);

			System.out.printf("%22s거래 내역 확인(계좌 선택)\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			String sel = scan.nextLine();
			if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= BankAccount.list.size()) {
				r = History.check(BankAccount.list.get(Integer.parseInt(sel) - 1).getAccountNumber());
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
			}

		}

	}


	/**
	 * 보유 계좌 리스트 규격
	 * @param data 보유 계좌 리스트
	 */
	public static void printAsciiTable(ArrayList<BankAccount> data) { 
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}

}
