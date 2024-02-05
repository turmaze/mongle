package com.mongle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class CustomerService {

	public static int csmenu() {
		Scanner scan = new Scanner(System.in);
		MongleVisual.menuHeader("고객센터");
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();

		boolean loop = true;
		int r = -1;

		while (loop) {
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
//	private static void inquiryList(ArrayList<Inquiry> list) {
//		Scanner scan = new Scanner(System.in);
//
//		System.out.printf("%22s총 %d개의 문의내역이 존재합니다.\n", " ", list.size());
//		System.out.println();
//		for (int i = 0; i < list.size(); i++) {
//			MongleVisual.menuHeader(i + 1 + "." + list.get(i).getTitleInq());
//			System.out.printf("%22s%s\n", " ", list.get(i).getTxtInq());
//			System.out.println();
//		}
//		System.out.printf("%22s이전 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
//		scan.nextLine();
//	}
//
//	private static ArrayList<Inquiry> inquiry(ArrayList<Inquiry> list) {
//		Scanner scan = new Scanner(System.in);
//		MongleVisual.menuHeader("문의하기");
//
//		System.out.printf("%22s문의 제목 : ", " ");
//		String title = scan.nextLine();
//		System.out.println();
//
//		System.out.printf("%22s문의 내용 : ", " ");
//		String txt = scan.nextLine();
//		System.out.println();
//		Inquiry inq = new Inquiry(title, txt);
//		list.add(inq);
//
//		masking(inq);
//
//		System.out.printf("%22s문의가 접수되었습니다. 감사합니다.\n", " ");
//
//		System.out.printf("%22s이전 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
//		scan.nextLine();
//
//		return list;
//	}
//
//	private static void masking(Inquiry inq) {
//		String[] forbidden = { "바보", "시발", "개새", "새끼", "멍청이", "병신", "등신", "머저리" };
//
//		for (String word : forbidden) {
//			if (inq.getTitleInq().contains(word)) {
//				String clean = "";
//				clean = inq.getTitleInq().replaceAll(word, "**");
//				inq.setTitleInq(clean);
//			}
//			if (inq.getTxtInq().contains(word)) {
//				String clean = "";
//				clean = inq.getTxtInq().replaceAll(word, "**");
//				inq.setTxtInq(clean);
//			}
//		}
//
//	}
//
//}