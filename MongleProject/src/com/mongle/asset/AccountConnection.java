package com.mongle.asset;

import java.util.Scanner;

import com.mongle.resource.BankAccount;
import com.mongle.service.AssetService;

import java.util.concurrent.ThreadLocalRandom;

public class AccountConnection {
	public static void connection() {
		// 계좌 연동(불러)오기
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
			System.out.printf("%22s0.홈 화면으로: ", " ");
			String sel = sc.nextLine();
			if (sel.equals("0")) {
				AssetService.assmenu();
				a = false;
				break;
			} else {
				System.out.printf("%22s0 번을 누르면 홈화면으로 갑니다.\n", " ");
				continue;
			}

		}

	}

	private static int randomAmount() {
		// 잔액 랜덤으로 생성 > 범위: 1000000~3000000
		int randomNumber = ThreadLocalRandom.current().nextInt(1000000, 3000001);
		return randomNumber;

	}
}
