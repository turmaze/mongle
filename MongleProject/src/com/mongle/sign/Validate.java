package com.mongle.sign;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongle.database.DataBase;

/**
 * 유효성 검사 클래스
 */
public class Validate {
	/**
	 * Id 유효성 검사
	 * 
	 * @param input 입력 받은 id
	 * @return 유효 유무
	 */
	public static boolean validId(String input) {
		input = input.toLowerCase();
		Pattern p = Pattern.compile("^[a-z0-9]{4,12}$");
		Matcher m = p.matcher(input);

		if (m.matches() && !isIdDuplicate(input)) {
			return m.matches();
		} else {
			System.out.printf("\n%22s잘못된 입력이거나 중복입니다.\n\n", " ");
			return false;
		}
	}// id

	/**
	 * Id 중복 검사
	 * 
	 * @param id 입력 받은 id
	 * @return 중복 유무
	 */
	public static boolean isIdDuplicate(String id) {
		for (HashMap userData : DataBase.getUser()) {
			if (id.equals(userData.get("id"))) {
				return true; // 중복된 ID가 있음
			}
		}
		return false; // 중복된 ID가 없음
	}

	/**
	 * 비밀번호 유효성 검사
	 * 
	 * @param input 입력 받은 비밀번호
	 * @return 유효 유무
	 */
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

	/**
	 * 이름 유효성 검사
	 * 
	 * @param input 입력 받은 이름
	 * @return 유효 유무
	 */
	public static boolean validName(String input) {
		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			System.out.printf("\n%22s잘못된 입력입니다.\n", " ");
			System.out.printf("\n%22s재입력 해주세요.\n", " ");
			return false;
		} else {
			return true;
		}
	}// name

	/**
	 * 생년월일 유효성 검사
	 * 
	 * @param input 입력받은 생년월일
	 * @return 유효 유무
	 */
	public static boolean validBirth(String input) {
		if (input.length() > 8 || input.length() < 8) {
			System.out.printf("\n%22s잘못된 입력입니다.\n", " ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n", " ");
			return false;
		}
		int year = Integer.parseInt(input.substring(0, 4));
		if (year > 2020 || year < 1940) {
			System.out.printf("\n%22s생년월일이 1940년 부터 2020년 사이여야 합니다.\n", " ");
			return false;
		}
		int month = Integer.parseInt(input.substring(4, 6));
		String regex;
		regex = "((19|20)\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01]))";

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			regex = "((19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01]))";
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
			System.out.printf("\n%22s잘못된 입력입니다.\n", " ");
			System.out.printf("\n%22s도움이 필요하시면 고객센터에 문의 해주세요\n", " ");
			return false;
		} else {
			return matchfound;
		}
	}// birth

	/**
	 * 번호 유효성 검사
	 * 
	 * @param phone 입력 받은 번호
	 * @return 유효 유무
	 */
	public static boolean validPhone(String phone) {
		int size = phone.length();
		if (phone.length() < 4) {
			System.out.printf("\n%22s잘못된 입력입니다.\n", " ");
			return false;
		}
		String starter = phone.substring(0, 3);

		if (size < 11 || size > 11 || !starter.equals("010")) {
			System.out.printf("\n%22s잘못된 입력입니다.\n", " ");
			return false;
		} else {
			return true;
		}
	}

}
