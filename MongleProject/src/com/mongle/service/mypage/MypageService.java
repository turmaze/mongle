package com.mongle.service.mypage;

import java.util.Scanner;

import com.mongle.view.MongleVisual;
import com.mongle.yourapp.SignOut;

public class MypageService {

	public static void mypageService(String[] args) {
		Scanner scan = new Scanner(System.in);
		MongleVisual.menuHeader("MyPage");
		boolean loop = true;

		System.out.printf("%22s1. 개인정보 조회 및 수정\n", " ");
		System.out.printf("%22s2. 신용점수\n", " ");
		System.out.printf("%22s3. 안심송금서비스 설정\n", " ");
		System.out.printf("%22s4. 출석체크\n", " ");
		System.out.printf("%22s5. 포인트\n", " ");
		System.out.printf("%22s6. 회원탈퇴\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		System.out.println();

		while (loop) {
			System.out.printf("%22s선택(번호) :", " ");
			String sel = scan.nextLine();

			switch (sel) {
			case "1":
				System.out.printf("%22s개인정보 화면으로 이동합니다.\n", " ");
				loop = false; break;
			case "2":
				System.out.printf("%22s신용점수 화면으로 이동합니다.\n", " ");
				CreditScore.creditScoreService();
				loop = false; break;
			case "3":
				System.out.printf("%22s안심송금서비스 화면으로 이동합니다.\n", " ");
				loop = false; break;
			case "4":
				System.out.printf("%22s출석체크 화면으로 이동합니다.\n", " ");
				AttendanceCheck.attendanceCheckService();
				loop = false; break;
			case "5":
				System.out.printf("%22s포인트 화면으로 이동합니다.\n", " ");
				Point.pointService();
				loop = false; break;
			case "6":
				System.out.printf("%22s회원탈퇴 화면으로 이동합니다.\n", " ");
				SignOut.signOutService();
				loop = false; break;
			case "0":
				System.out.printf("%22s이전 화면으로 이동합니다.\n", " ");
				loop = false; break;
			default:
				System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
			}
		}

	}

}
