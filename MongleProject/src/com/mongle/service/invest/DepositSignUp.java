package com.mongle.service.invest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DepositSignUp {
	public String account;

	public static void main(String[] args) {


	}
	
	public static void signUp(String bankDepo, String titleDepo) {
		Scanner sc = new Scanner(System.in);

		System.out.println("선택한 상품이 맞으신가요?(y/n)");
		String answer = sc.nextLine();
		if (answer.equals("y") || answer.equals("Y")) {
//			Reconfirm(); ///비밀번호 검사 
//			String account = data.get(sel).getBank() + data.get(sel).getTitle() + data.get(sel).getPeriod()
//					+ data.get(sel).getRate() + data.get(sel).getMaxRate();
//			this.account = account;
			System.out.println("가입 성공");

		} else {
			System.out.println("비밀번호가 불일치 합니다.");
			// 돌아가기
		}

	}// DepositSignUp

//	public boolean Reconfirm() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("비밀번호를 입력해 주세요:");
//		String rpw = sc.nextLine();
//		if (rpw == pw) {
//			// 가입성공
//			return true;
//
//		} else {
//			// 비밀번호 불일치
//			System.out.println("비밀번호가 불일치합니다.");
//			return false;
//			// 이전화면으로 돌아가기
//		}
//	}// Reconfirm
	
	
}// RootClass
