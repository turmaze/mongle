package test;

import java.util.Random;
import java.util.Scanner;

public class AccountTest {
	
	
	
	private static final int ACCOUNT_NUMBER_LENGTH = 10; 
	private static final String[] VALID_IDS = { "930102", "950203", "970304" }; 
	private static final String[] VALID_PW = { "1234", "5678" };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String accountNumber = null;
		System.out.println("이자율 5%인 상품이 있습니다. 가입 하시겠습니까? (y/n) : ");
		String answer = scanner.nextLine();
		if (answer.equals("y") || answer.equals("Y")) {
			System.out.print("비밀번호를 입력하세요: ");
			String password = scanner.nextLine();

			System.out.print("주민등록번호를 입력하세요: ");
			String idNumber = scanner.nextLine();

			
			 accountNumber = openAccount(password, idNumber);
			if (accountNumber != null) {
				System.out.println("계좌가 개설되었습니다. 계좌번호: " + accountNumber);
			} else {
				System.out.println("비밀번호나 주민등록번호가 일치하지 않습니다.");
			}
		}else {
			System.out.println("다음에 더좋은 상품이 있으면 알림 드리겠습니다 !");
		}
		scanner.close();
		
		System.out.println(accountNumber);
	}//main

	public static String openAccount(String password, String idNumber) {
	
		boolean isValidId = false;
		for (String id : VALID_IDS) {
			if (id.equals(idNumber)) {
				isValidId = true;
				break;
			}
		}

	
		if (isValidId && checkPassword(password)) {
		
			String accountNumber = generateAccountNumber();
			return accountNumber;
		}
		return null;
	}//openAccount

	public static boolean checkPassword(String password) {

		boolean isValidpw = false;
		for (String pw : VALID_PW) {
			if (pw.equals(password)) {
				isValidpw = true;
				return true;
			}
		}
		return false;
	}//checkPassword

	public static String generateAccountNumber() {
		
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}//generateAccountNumber
}
