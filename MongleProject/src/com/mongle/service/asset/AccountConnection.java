package com.mongle.service.asset;

import java.util.Scanner;

import com.mongle.resource.BankAccount;
import com.mongle.service.AssetService;
import com.mongle.view.MongleVisual;

import java.util.concurrent.ThreadLocalRandom;

public class AccountConnection {
	
	
	/**
	 * 계좌 연동하기 메서드
	 * @return 사용자가 입력한 계좌 
	 */
	public static int connection() {
	
		Scanner sc = new Scanner(System.in);

		boolean tf = true;

		System.out.printf("%22s은행명: ", " ");
		String bd = sc.nextLine();
		while (tf) {
			System.out.printf("%22s예금 or 적금 :", " ");
			String td = sc.nextLine();
			if (td.equals("예금") || td.equals("적금")) {
				System.out.printf("%22s불러올 계좌번호를 입력 하세요( - 포함):", " ");
				String an = " " + sc.nextLine();

				int randomAmount = randomAmount();

				// 난수
				BankAccount.list.add(new BankAccount(bd, td, an, randomAmount));
				tf = false;
			} else {
				System.out.printf("%22s잘못된 값을 입력하셨습니다. 다시 입력해 주세요\n", " ");
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
				continue;
			}

		}
		return 0;
	}
	/**
	 * 잔액 랜덤 생성
	 * @return 계좌 연동 후 잔액 랜덤으로 넣기
	 */
	private static int randomAmount() {
		// 잔액 랜덤으로 생성 > 범위: 1000000~3000000
		int randomNumber = ThreadLocalRandom.current().nextInt(1000000, 3000001);
		return randomNumber;

	}
}
