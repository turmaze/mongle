package com.mongle.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.mongle.view.MongleVisual;

public class AttendanceCheck {
	
	public static void attendanceCheckService() {
		Scanner scan = new Scanner(System.in);
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = now.format(formatter);
		String[] date = today.split("-");

		while (true) {
			MongleVisual.menuHeader("출석 체크");

			System.out.println();
			
			printCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			
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
	
	private static void printCalendar(int year, int month) {
		int lastDay = getLastDay(year, month);
		int dayOfWeek = getDayOfWeek(year, month);
		
		//System.out.println(lastDay);
		//System.out.println(dayOfWeek);
		
		System.out.println("====================================================");
		System.out.printf("                   %02d월\n", month);
		System.out.println("====================================================");
		System.out.println("[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]");
		
		for (int i=0; i<dayOfWeek; i++) {
			System.out.print("\t");
		}
		
		for (int i=1; i<=lastDay; i++) {
			
			System.out.printf("%3d\t", i);
			
			//토요일 개행
			//if (i % 7 == 6) {
			if ((i + dayOfWeek) % 7 == 0) {
				System.out.println();
			}
		}
		
	}
	
private static int getLastDay(int year, int month) {
		
		switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return 31;
			case 4: case 6: case 9: case 11:
				return 30;
			case 2:
				return isLeafYear(year) ? 29 : 28;
		}
		
		return 0;
	}
	
	//메서드명 패턴
	//1. 반환값 getXXX()
	//2. setXXX(인자값)
	//3. boolean isXXX()

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
		
		//서기 1년 1월 1일 ~ year년 month월 오늘
		int date = 1;
		
		int sum = 0; //누적 변수
		
		//1. 1년 1월 1일 ~ 2023년 12월 31일 > 1년 365일
		for (int i=1; i<year; i++) {
			
			sum += 365;
			
			if (isLeafYear(i)==true) {
				sum++;
			}
			
		}
		
		//2. 2024년 1월 1일 ~ 2024년 3월 31일 < 1달씩
		for (int i=1; i<month; i++) {
			sum += getLastDay(year, i);
		}
		
		//3. 2024년 1월 오늘
		sum += date;
		
		return sum % 7;
	}
	
}
