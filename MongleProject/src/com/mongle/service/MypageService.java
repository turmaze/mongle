package com.mongle.service;

import java.util.Scanner;

import com.mongle.service.mypage.AttendanceCheck;
import com.mongle.service.mypage.CreditScore;
import com.mongle.service.mypage.EditInfo;
import com.mongle.service.mypage.Point;
import com.mongle.service.mypage.QRCode;
import com.mongle.service.mypage.SafeSend;
import com.mongle.sign.SignOut;
import com.mongle.view.MongleVisual;

public class MypageService {

	public static int mypageService() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
			MongleVisual.pusher();
			int r = -1;
			MongleVisual.menuHeader("MyPage");
			System.out.printf("%22s1. 결제용 QRcode\n", " ");
			System.out.printf("%22s2. 개인정보 조회 및 수정\n", " ");
			System.out.printf("%22s3. 신용점수\n", " ");
			System.out.printf("%22s4. 안심송금서비스 설정\n", " ");
			System.out.printf("%22s5. 출석체크\n", " ");
			System.out.printf("%22s6. 포인트\n", " ");
			System.out.printf("%22s7. 회원탈퇴\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();

			switch (sel) {
			case "1":
				MongleVisual.menuMove("QRcode 화면");
				r = QRCode.qr();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "2":
				MongleVisual.menuMove("개인정보 화면");
				r = EditInfo.edit();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "3":
				MongleVisual.menuMove("신용점수 화면");
				r = CreditScore.creditScoreService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "4":
				MongleVisual.menuMove("안심송금서비스 설정 화면");
				r = SafeSend.safeSendService();
				
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "5":
				MongleVisual.menuMove("출석체크 화면");
				r = AttendanceCheck.attendanceCheckService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "6":
				MongleVisual.menuMove("포인트 화면");
				r = Point.pointService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "7":
				MongleVisual.menuMove("회원탈퇴 화면");
				r = SignOut.signOutService();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "0":
				MongleVisual.menuMove("이전 화면");
				return 0;
			default:
				MongleVisual.wrongInput();
			}
		}
		return 0;

	}

}
