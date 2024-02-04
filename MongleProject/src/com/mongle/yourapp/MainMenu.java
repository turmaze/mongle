package com.mongle.yourapp;

import java.util.Scanner;

import com.mongle.asset.WireTransfer;
import com.mongle.database.DataBase;
import com.mongle.main.Main;
import com.mongle.resource.Investment;
import com.mongle.service.AssetService;
import com.mongle.service.BlackList;
import com.mongle.service.Inquiry;
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
			System.out.printf("\n%22s9. 프로그램 종료", " ");
			System.out.printf("\n%22s0. 로그아웃", " ");
			System.out.printf("\n\r\n%22s선택번호: ", " ");
			String choice = scan.nextLine();

			switch (choice) {
			case "1":
				System.out.printf("%22s자산관리 화면으로 이동합니다.\n\n", " ");			
				AssertManagementView.assertadd();
				
				continue;
			case "2":
				System.out.printf("%22s송금 화면으로 이동합니다.\n\n", " ");
				WireTransfer.extracted();
				continue;
			case "3":
				System.out.printf("%22s투자 화면으로 이동합니다.\n\n", " ");
				r = InvestService.investMenu();
				continue;
			case "4":
				System.out.printf("%22s고객센터 화면으로 이동합니다.\n\n", " ");
				continue;
			case "5":
				System.out.printf("%22sMyPage 화면으로 이동합니다.\n\n", " ");
				r = MypageService.mypageService();
				continue;
			case "9":
				System.out.printf("%22s정말로 종료하시겠습니까?(y/n)", " ");
				String sel = scan.nextLine();
				sel = sel.toLowerCase();
				if (sel.equals("y")) {
					
					System.out.printf("\n%22s프로그램을 종료합니다.", " ");
					
					DataBase.changeData();
					
					DataBase.dataSave();
					
					System.exit(0);
					
				} else if (sel.equals("n")) {
					
					MongleVisual.pusher();
					System.out.printf("\n%22s종료를 취소합니다.\n", " ");
					MongleVisual.stopper();
					
				} else {
					
					MongleVisual.wrongInput();
					
				}
				continue;
			case "0":
				System.out.printf("%22s로그아웃합니다.\n\n", " ");
				loop = false;
				break;
			default:
				MongleVisual.wrongInput();
			}

		}

	}

	private static void adminMenu() {
		boolean loop = true;
		while(loop) {
			Scanner scan = new Scanner(System.in);
			
			MongleVisual.menuHeader("관리자 페이지");
			
			System.out.printf("\n%22s1. 회원 관리", " ");
			System.out.printf("\n%22s2. 데이터 관리", " ");
			System.out.printf("\n%22s3. 공지사항 관리", " ");
			System.out.printf("\n%22s4. 문의 처리", " ");
			System.out.printf("\n%22s5. 블랙리스트", " ");
			System.out.printf("\n%22s0. 로그아웃", " ");
			
			System.out.printf("\n%22s선택번호: ", " ");
			String choice = scan.nextLine();
			switch (choice) {
			case "1":
				System.out.printf("%22s회원 관리화면으로 이동합니다.\n\n", " ");
				
				continue;
			case "2":
				System.out.printf("%22s데이터 관리화면으로 이동합니다.\n\n", " ");
				WireTransfer.extracted();
				continue;
			case "3":
				System.out.printf("%22s공지사항 관리화면으로 이동합니다.\n\n", " ");
				Inquiry.adminAnnouncement();
				continue;
			case "4":
				System.out.printf("%22s문의 관리화면으로 이동합니다.\n\n", " ");
				WireTransfer.extracted();
				continue;
			case "5":
				System.out.printf("%22s블랙리스트 화면으로 이동합니다.\n\n", " ");
				BlackList.blackList();
				continue;
			case "0":
				System.out.printf("%22s로그아웃합니다.\n\n", " ");
				loop = false;
				return;
			default:
				System.out.printf("%22s올바른 번호(숫자)를 입력해주세요.\n\n", " ");
			}
			
		}
		//System.out.println(choice);

	}
}
