package com.mongle.service.mypage;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.LogIn;

public class AttendanceCheck {

	public static int attendanceCheckService() {
		Scanner scan = new Scanner(System.in);

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = now.format(formatter);
		String[] date = today.split("-");

		while (true) {
			
			MongleVisual.pusher();
			
			MongleVisual.menuHeader("출석 체크");

			System.out.println();

			printCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]));

			System.out.println();
			System.out.println();

			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.printf("%22s선택(번호): ", " ");

			String sel = scan.nextLine();
			if (sel.equals("1")) {

			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}
		}
		
	}
	
	public static void autoAttendance() {
		
		int point = Integer.parseInt((String) DataBase.getPrivateUser().get(0).get("point"));
		point += 10;
		
		DataBase.getPrivateUser().get(0).replace("point", point+"");
		
	}

	private static void printCalendar(int year, int month) {
		int lastDay = getLastDay(year, month);
		int dayOfWeek = getDayOfWeek(year, month);

		// System.out.println(lastDay);
		// System.out.println(dayOfWeek);

		System.out.printf("%15s======================================================\n", " ");
		System.out.printf("%15s                       %02d월\n", " ", month);
		System.out.printf("%15s======================================================\n", " ");
		System.out.printf("%8s\t[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\n", " ");

		System.out.printf("%8s\t", " ");	//정렬 공백
		for (int i = 0; i < dayOfWeek; i++) {
			System.out.print("\t");
		}

		for (int i = 1; i <= lastDay; i++) {

			System.out.printf("%3d\t", i);

			if ((i + dayOfWeek) % 7 == 0 && i != lastDay) {

				if (i < dayOfWeek) {
					//첫째주
					System.out.println();
					System.out.printf("%8s\t", " ");	//정렬 공백
					for (int k = 0; k < dayOfWeek; k++) {
						System.out.print("\t");
					}
					for (int k = 0; k < 7 - dayOfWeek; k++) {
						System.out.printf("%3s\t", "X");
					}
					System.out.println();
					System.out.printf("%8s\t", " ");	//정렬 공백
				} else {
					//두번째주~마지막 전 주
					System.out.println();
					System.out.printf("%8s\t", " ");	//정렬 공백
					System.out.printf("%3s\t", "X");
					System.out.printf("%3s\t", "X");
					System.out.printf("%3s\t", "X");
					System.out.printf("%3s\t", "X");
					System.out.printf("%3s\t", "X");
					System.out.printf("%3s\t", "X");
					System.out.printf("%3s\t", "X");
					System.out.println();
					System.out.printf("%8s\t", " ");	//정렬 공백
				}

			}

			if (i == lastDay) {
				//마지막주
				System.out.println();
				Calendar lastWeek = Calendar.getInstance();
				lastWeek.set(year, month, i);
				int lastDayOfWeek = lastWeek.get(Calendar.DAY_OF_WEEK);
				
				System.out.printf("%8s\t", " ");
				
				for (int j=1; j<lastDayOfWeek; j++) {
					System.out.printf("%3s\t", "X");
				}

			}

		}
//		for (int i=1; i<=lastDay; i++) {
//			
//			System.out.printf("%3d\t", i);
//			
//			//토요일 개행
//			//if (i % 7 == 6) {
//			if ((i + dayOfWeek) % 7 == 0) {
//				System.out.println();
//				System.out.print("\t\t");
//				System.out.printf("%3s\t", "X");
//				System.out.printf("%3s\t", "X");
//				System.out.printf("%3s\t", "X");
//				System.out.printf("%3s\t", "X");
//				System.out.printf("%3s\t", "X");
//				System.out.printf("%3s\t", "X");
//				System.out.printf("%3s\t", "X");
//				
//				System.out.println();
//				System.out.printf("%8s\t", " ");
//				
//			}
//			
//		}

	}

	private static int getLastDay(int year, int month) {

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			return isLeafYear(year) ? 29 : 28;
		}

		return 0;
	}

	// 메서드명 패턴
	// 1. 반환값 getXXX()
	// 2. setXXX(인자값)
	// 3. boolean isXXX()

	private static boolean isLeafYear(int year) {

		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}

	}

	private static int getDayOfWeek(int year, int month) {

		// 서기 1년 1월 1일 ~ year년 month월 오늘
		int date = 1;

		int sum = 0; // 누적 변수

		// 1. 1년 1월 1일 ~ 2023년 12월 31일 > 1년 365일
		for (int i = 1; i < year; i++) {

			sum += 365;

			if (isLeafYear(i) == true) {
				sum++;
			}

		}

		// 2. 2024년 1월 1일 ~ 2024년 3월 31일 < 1달씩
		for (int i = 1; i < month; i++) {
			sum += getLastDay(year, i);
		}

		// 3. 2024년 1월 오늘
		sum += date;

		return sum % 7;
	}

}
