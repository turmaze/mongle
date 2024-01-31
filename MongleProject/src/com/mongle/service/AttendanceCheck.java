package com.mongle.service;

import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class AttendanceCheck {
	
	public static void attendanceCheckService() {
		Scanner scan = new Scanner(System.in);

		while (true) {
			MongleVisual.menuHeader("출석 체크");

			System.out.println();
			
			
			
			System.out.println();

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
	
}
