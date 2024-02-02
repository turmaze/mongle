package com.mongle.sign;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongle.database.DataBase;

public class Validate {

	public static boolean validId(String input) {
	
		input = input.toLowerCase();
	
		Pattern p = Pattern.compile("^[a-z0-9]{4,12}$");
		Matcher m = p.matcher(input);
	
		if (m.matches() && !isIdDuplicate(input)) {
			return m.matches();
		} else {
			// System.out.println(m.matches()); //testcode
			System.out.printf("\n%22s잘못된 입력이거나 중복입니다.\n\n", " ");
			return false;
		}
	}// id

	private static boolean isIdDuplicate(String id) {
	
		for (HashMap userData : DataBase.getUser()) {
			// if (userData.containsValue(id)) {
			if (id.equals(userData.get("id"))) {
				return true; // 중복된 ID가 있음
			}
		}
		return false; // 중복된 ID가 없음
	}

	public static boolean validPw(String input) {
	
		Pattern p = Pattern.compile("^[a-z0-9]{10,16}$");
		Matcher m = p.matcher(input);
	
		if (m.matches()) {
			return m.matches();
		} else {
			System.out.printf("\n%22s잘못된 입력입니다.\n\n", " ");
			return m.matches();
		}
	
	}// pw

	public static boolean validName(String input) {
	
		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			System.out.printf("\n%22s잘못된 입력입니다.\n", " ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n", " ");
	
			return m.matches();
		} else {
			return m.matches();
		}
	}// name

	public static boolean validBirth(String input) {
	
		if(input.length()>8||input.length()<8) {
			System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n"," ");
		}
	
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(4, 6));
		String regex;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			regex = "^((19|20)\\d{2})(0[1-9]|1[1,2])(0[1-9]|[12][0-9]|3[01])$";
		} else if (month == 2) {
			if (year % 4 == 0) {
				regex = "^((19|20)\\d{2})(02)(0[1-9]|1[0-9]|2[0-9])$";
			}
			regex = "^((19|20)\\d{2})(02)(0[1-9]|1[0-9]|2[0-8])$";
		} else {
			regex = "^((19|20)\\d{2})(0[1-9]|1[1,2])(0[1-9]|[12][0-9]|3[0])$";
		}
	
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		boolean matchfound = m.find();
		System.out.println();
		if (!matchfound) {
			 System.out.printf("\n%22s잘못된 입력입니다.\n"," ");
			 System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n"," ");
			return false;
		} else {
			return matchfound;
		}
	}// birth

	public static boolean validPhone(String phone) {
		int size = phone.length();
		String starter = phone.substring(0, 3);
	
		System.out.println(starter);
		if (size < 11 || size > 11 || !starter.equals("010")) {
			return false;
		} else {
			return true;
		}
	
	}

}
