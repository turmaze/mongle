package com.mongle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongle.view.MongleVisual;

/**
 * 고객센터 클래스
 */
public class CustomerService {
	/**
	 * 고객 센터 메뉴
	 * 
	 * @return 메뉴 이동을 위한 변수
	 */
	public static int csmenu() {
		Scanner scan = new Scanner(System.in);
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();

		boolean loop = true;
		int r = -1;

		while (loop) {
			MongleVisual.menuHeader("고객센터");
			System.out.printf("%22s1. 공지사항\n", " ");
			System.out.printf("%22s2. 문의하기\n", " ");
			System.out.printf("%22s3. 문의내역\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			String sel = scan.nextLine();
			Inquiry inq = new Inquiry();

			switch (sel) {
			case "1":
				MongleVisual.menuMove("공지사항 화면");
				r = Inquiry.mebmerAnnouncement();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "2":
				MongleVisual.menuMove("문의 화면");
				r = Inquiry.inquiry();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
			case "3":
				MongleVisual.menuMove("문의내역 화면");
				r = Inquiry.checkInq();
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
