package com.mongle.service.mypage;

import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;

public class Point {
	
	public static int pointService() {
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			MongleVisual.pusher();
			
			MongleVisual.menuHeader("포 인 트");

			System.out.println();
			System.out.printf("%22s보유 포인트", " ");
			System.out.println();

			System.out.printf("%22s1. 포인트 내역\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");

			String sel = scan.nextLine();
			if (sel.equals("1")) {

			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}
		}

	}
	
}
