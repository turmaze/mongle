package com.mongle.service.mypage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongle.database.DataBase;
import com.mongle.resource.AttendList;
import com.mongle.resource.BankAccount;
import com.mongle.service.asset.GiveAccount;
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

			String nowEmoji = AttendList.list.get(0).getEmoji(); // 현재 설정된 출석 마크
			printCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), nowEmoji);

			String emojiString = "출석 마크"; // 출석 표시 마크 이름 (수정 예정?)

			System.out.printf("%22s1. %s 변경(현재: %s)\n", " ", emojiString, nowEmoji);
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			String sel = scan.nextLine();
			if (sel.equals("1")) {
				int r = attendEmojiChange(emojiString);

				if (r == 9) {
					return 9;
				}
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}
		}

	}

	private static int attendEmojiChange(String emojiString) {

		Scanner scan = new Scanner(System.in);

		boolean loop = true;

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader(emojiString);

			String[] emoji = { "O", "𖠌", "◡̎", "(ꔷ̥̑.̮ꔷ̥̑)", };
			int numEmoji = emoji.length;

			for (int i = 0; i < emoji.length; i++) {
				System.out.printf("%22s%d. %s\n", " ", i + 1, emoji[i]);
			}
			System.out.println();
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			String sel = scan.nextLine();

			String regex = String.format("^[1-%d]$", numEmoji);
			Pattern p1 = Pattern.compile(regex);
			Matcher m1 = p1.matcher(sel);

			if (m1.find()) {
				String nowEmoji = emoji[Integer.parseInt(sel)-1];
				AttendList.list.get(0).setEmoji(nowEmoji);
				MongleVisual.successPrint();
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 1;
			} else {
				MongleVisual.wrongInput();
			}

		}

		return 1;

	}

	public static void attendanceload() {
		JSONArray arr = (JSONArray) DataBase.getPrivateUser().get(0).get("attend");
		if (arr != null) {
			if (arr.size() > 0) {
				AttendList.list.add(new AttendList((ArrayList<String>) ((JSONObject) arr.get(0)).get("attenddate"),
						(String) ((JSONObject) arr.get(0)).get("stratedate"),
						(String) ((JSONObject) arr.get(0)).get("emoji")));
				return;
			}
		}
	}

	public static void autoAttendance() {

		Calendar yesterdate = Calendar.getInstance();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = date.format(formatter);
		String[] day = today.split("-");
		yesterdate.add(Calendar.DATE, -1);
		String yesterday = String.format("%02d-%02d-%02d", yesterdate.get(Calendar.YEAR),
				(yesterdate.get(Calendar.MONTH) + 1), yesterdate.get(Calendar.DATE));
		boolean b = true;

		ArrayList<String> pointdate = new ArrayList<String>() {
			{
				add(today);
			}
		};

		attendanceload();

		if (AttendList.list.size() == 0) { // 회원가입 후 첫 로그인
			AttendList.list.add(new AttendList(pointdate, "1", "O"));
			AttendList.getPoint();
			return;
		}

		for (String str : AttendList.list.get(0).getAttenddate()) {
			if (str.equals(today)) {
				return;
			}
		}

		int strate = Integer.parseInt(AttendList.list.get(0).getStratedate());

		for (String str : AttendList.list.get(0).getAttenddate()) {
			if (str.equals(yesterday)) {
				strate++;
				b = false;
			}
		}

		if (b) {
			strate = 1;
		}

		AttendList.list.get(0).setStratedate(strate + "");
		AttendList.list.get(0).getAttenddate().add(today);
		AttendList.getPoint();

	}

	private static void printCalendar(int year, int month, String nowEmoji) {
		LocalDate date = LocalDate.now();
		String dates = date + "";
		dates = dates.substring(dates.length() - 2);
		int today = Integer.parseInt(dates);
		int lastDay = getLastDay(year, month);
		int dayOfWeek = getDayOfWeek(year, month);

		ArrayList<Integer> attendlist = new ArrayList<Integer>();
		attendlist = attendDayList();

		System.out.printf("%15s======================================================\n", " ");
		System.out.printf("%15s                       %02d월\n", " ", month);
		System.out.printf("%15s======================================================\n", " ");
		System.out.printf("%8s\t[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\n", " ");

		System.out.printf("%8s\t", " "); // 정렬 공백
		for (int i = 0; i < dayOfWeek; i++) {
			System.out.print("\t");
		}

		for (int i = 1; i <= lastDay; i++) {

			System.out.printf("%3d\t", i);

			if ((i + dayOfWeek) % 7 == 0 && i != lastDay) {

				if (i < dayOfWeek) {
					// 첫째주
					System.out.println();
					System.out.printf("%8s\t", " "); // 정렬 공백
					for (int k = 1; k <= dayOfWeek; k++) {
						System.out.print("\t");
					}
					for (int k = 1; k <= 7 - dayOfWeek; k++) {
						if (attendlist.contains(k)) {
							System.out.printf("%3s\t", nowEmoji);
						} else if (k >= today) {
							System.out.printf("%3s\t", "");
						} else {
							System.out.printf("%3s\t", "X");
						}
					}
					System.out.println();
					System.out.printf("%8s\t", " "); // 정렬 공백
				} else {
					// 두번째주~마지막 전 주
					System.out.println();
					System.out.printf("%8s\t", " "); // 정렬 공백
					for (int k = i-6; k <= i; k++) {
						if (attendlist.contains(k)) {
							System.out.printf("%3s\t", nowEmoji);
						} else if (k > today) {
							System.out.printf("%3s\t", "");
						} else {
							System.out.printf("%3s\t", "X");
						}
					}
					System.out.println();
					System.out.printf("%8s\t", " "); // 정렬 공백
				}

			}

			if (i == lastDay) {
				// 마지막주
				System.out.println();
				Calendar lastWeek = Calendar.getInstance();
				lastWeek.set(year, month, i);
				int lastDayOfWeek = lastWeek.get(Calendar.DAY_OF_WEEK);

				System.out.printf("%8s\t", " ");

				for (int j = i-lastDayOfWeek+2; j <= i; j++) {
					if (attendlist.contains(j)) {
						System.out.printf("%3s\t", nowEmoji);
					} else if (j > today) {
						System.out.printf("%3s\t", "");
					} else {
						System.out.printf("%3s\t", "X");
					}
				}

				System.out.println();
				System.out.println();

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

	public static ArrayList<Integer> attendDayList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (String day : AttendList.list.get(0).getAttenddate()) {
			day = day.substring(day.length() - 2);
			list.add(Integer.parseInt(day));
		}
		Collections.sort(list);
		return list;
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
