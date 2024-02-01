package com.mongle.service;

import java.util.Scanner;

import com.mongle.service.mypage.AttendanceCheck;
import com.mongle.service.mypage.CreditScore;
import com.mongle.service.mypage.Point;
import com.mongle.service.mypage.SafeSend;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.SignOut;

public class MypageService {

	public static int mypageService() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
			MongleVisual.pusher();
			int r = -1;
			MongleVisual.menuHeader("MyPage");
		System.out.printf("%22s1. 개인정보 조회 및 수정\n", " ");
		System.out.printf("%22s2. 신용점수\n", " ");
		System.out.printf("%22s3. 안심송금서비스 설정\n", " ");
		System.out.printf("%22s4. 출석체크\n", " ");
		System.out.printf("%22s5. 포인트\n", " ");
		System.out.printf("%22s6. 회원탈퇴\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		System.out.println();

			System.out.printf("%22s선택(번호) :", " ");
			String sel = scan.nextLine();

			switch (sel) {
			case "1":
				System.out.printf("%22s개인정보 화면으로 이동합니다.\n", " ");
			case "2":
				System.out.printf("%22s신용점수 화면으로 이동합니다.\n", " ");
				CreditScore.creditScoreService();
				continue;
			case "3":
				System.out.printf("%22s안심송금서비스 화면으로 이동합니다.\n", " ");
				r = SafeSend.safeSendService();
				if (r == 9) {
					return 9;
				} else {
				continue;
				}
			case "4":
				System.out.printf("%22s출석체크 화면으로 이동합니다.\n", " ");
				r = AttendanceCheck.attendanceCheckService();
				if (r == 9) {
					return 9;
				} else {
				continue;
				}
			case "5":
				System.out.printf("%22s포인트 화면으로 이동합니다.\n", " ");
				Point.pointService();
				continue;
			case "6":
				System.out.printf("%22s회원탈퇴 화면으로 이동합니다.\n", " ");
				SignOut.signOutService();
				continue;
			case "0":
				System.out.printf("%22s이전 화면으로 이동합니다.\n", " ");
				loop = false; break;
			default:
				System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
			}
		}
		return 0;

	}

}
