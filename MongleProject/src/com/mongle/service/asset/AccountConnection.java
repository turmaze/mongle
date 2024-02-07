package com.mongle.service.asset;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.mongle.resource.BankAccount;
import com.mongle.view.MongleVisual;

public class AccountConnection {

	/**
	 * 계좌 연동하기 메서드
	 * 
	 * @return 사용자가 입력한 계좌
	 */
	public static int connection() {

		Scanner sc = new Scanner(System.in);
		String bd = "";
		boolean tff=true;
		while(tff) {
		MongleVisual.menuHeader("계좌 연동");
		System.out.printf("%22s기업,국민,농협,신한,우리,하나 은행만 연동이 가능합니다.\n", " ");
		System.out.printf("%22s은행명: ", " ");
		bd = sc.nextLine();
		tff=bankName(bd);
		
		}
		boolean tf = true;
		while (tf) {
			System.out.printf("%22s예금 or 적금: ", " ");
			String td = sc.nextLine();

			if (td.equals("예금") || td.equals("적금")) {
				{
					System.out.printf("%22s불러올 계좌번호를 입력하세요( - 포함):", " ");
					String an = sc.nextLine().trim();

					if (an.length() >= 8 && an.length() <= 20 && an.matches("[0-9\\-]+")) {
						int randomAmount = randomAmount();
						BankAccount.list.add(new BankAccount(bd, td, an, randomAmount));
						tf = false;
					} else {
						System.out.printf("%22s8~20자, 숫자와 - 만 입력이 가능 합니다.\n", " ");
						System.out.println();
					}
				}
			} else {
				System.out.printf("%22s예금 or 적금만 연동이 가능 합니다.\n", " ");
				System.out.println();
			}
		}
		System.out.println();
		System.out.printf("%22s연동이 완료되었습니다.\n", " ");

		boolean a = true;
		while (a) {
			System.out.printf("%22s0.홈으로: ", " ");
			String sel = sc.nextLine();
			if (sel.equals("0")) {
				MongleVisual.menuMove("홈 화면");
				return 9;
			} else {
				MongleVisual.wrongInput();
			}
		}

		return 0;
	}

	public static boolean bankName(String bd) {
		String [] banklist = {"기업","국민","농협","신한","우리","하나"};
		for(String str : banklist) {
			if(str.equals(bd)) {
				return false;			
			}
		}
		return true;
		
	
	}

	/**
	 * 잔액 랜덤 생성
	 * 
	 * @return 계좌 연동 후 잔액 랜덤으로 넣기
	 */
	private static int randomAmount() {

		int randomNumber = ThreadLocalRandom.current().nextInt(1000000, 3000001);
		return randomNumber;

	}
}
