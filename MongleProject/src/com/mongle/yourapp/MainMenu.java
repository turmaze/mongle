package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.asset.WireTransfer;
import com.mongle.main.Main;
import com.mongle.resource.Investment;
import com.mongle.service.AssetService;
import com.mongle.service.InvestService;
import com.mongle.service.MypageService;
import com.mongle.service.mypage.AttendanceCheck;
import com.mongle.service.mypage.CreditScore;
import com.mongle.service.mypage.Point;
import com.mongle.service.mypage.SafeSend;
import com.mongle.view.AssertManagementView;
import com.mongle.view.InvestmentView;
import com.mongle.view.MongleVisual;

public class MainMenu {
	public static void mainMenu(String level) {

		Scanner scan = new Scanner(System.in);

		if (level.equals("1")) {
			userMenu();
		} else if (level.equals("2")) {
			adminMenu();
		}
	}

	private static void userMenu() {
		Scanner scan = new Scanner(System.in);
		int r = -1;
		boolean loop = true;

		while (loop) {
			MongleVisual.pusher();
			
			MongleVisual.menuHeader("메인메뉴");

			System.out.printf("\n%22s1. 자산관리", " ");
			System.out.printf("\n%22s2. 송금", " ");
			System.out.printf("\n%22s3. 투자", " ");
			System.out.printf("\n%22s4. 고객센터", " ");
			System.out.printf("\n%22s5. MyPage", " ");
			System.out.printf("\n%22s0. 로그아웃", " ");
			System.out.printf("\n\r\n%22s선택번호: ", " ");
			String choice = scan.nextLine();

			switch (choice) {
			case "1":
				System.out.printf("%22s자산관리 화면으로 이동합니다.\n", " ");
				
			
				AssertManagementView.assertadd();
				
				continue;
			case "2":
				System.out.printf("%22s송금 화면으로 이동합니다.\n", " ");
				WireTransfer.extracted();
				continue;
			case "3":
				System.out.printf("%22s투자 화면으로 이동합니다.\n", " ");
				r = InvestService.investMenu();
				continue;
			case "4":
				System.out.printf("%22s고객센터 화면으로 이동합니다.\n", " ");
				continue;
			case "5":
				System.out.printf("%22sMyPage 화면으로 이동합니다.\n", " ");
				r = MypageService.mypageService();
				continue;
			case "0":
				System.out.printf("%22s로그아웃합니다.\n", " ");
				loop = false; return;
			default:
				System.out.printf("%22s올바른 번호(숫자)를 입력해주세요.\n", " ");
			}

		}

	}

	private static void adminMenu() {
		Scanner scan = new Scanner(System.in);

		MongleVisual.menuHeader("관리자 페이지");

		System.out.printf("\n%22s1. 회원 관리", " ");
		System.out.printf("\n%22s2. 데이터 관리", " ");
		System.out.printf("\n%22s3. 문의 처리", " ");
		System.out.printf("\n%22s0. 로그아웃", " ");

		System.out.printf("\n%22s선택번호: ", " ");
		String choice = scan.nextLine();

		System.out.println(choice);

	}
}
