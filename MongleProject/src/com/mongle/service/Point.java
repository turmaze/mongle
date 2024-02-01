package com.mongle.service;

import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class Point {

	public static void pointService() {
		Scanner scan = new Scanner(System.in);

		while (true) {
			MongleVisual.menuHeader("포 인 트");

			System.out.println();
			System.out.printf("%22s보유 포인트", " ");
			System.out.println();

			System.out.printf("%22s1. 포인트 내역", " ");
			System.out.printf("%22s9. 홈으로", " ");
			System.out.printf("%22s0. 이전으로", " ");

			String sel = scan.nextLine();
			if (sel.equals("1")) {

			} else if (sel.equals("9")) {
				break;
			} else if (sel.equals("0")) {
				break;
			}
		}

	}
	
	public static void getPoint() {
		
	}
	
	public static void pointList() {
		
	}

}
