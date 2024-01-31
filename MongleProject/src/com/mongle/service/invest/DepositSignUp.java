package com.mongle.service.invest;

import java.util.Scanner;

import com.mongle.asset.AssetService;

public class DepositSignUp {
	public static String account;

	public static void main(String[] args) {
		
	}
	//첫입금 할 돈 
	// 나중에 입금 > 저장되게 
	public static void signUp(String bankDepo, String titleDepo) {
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("%21s 선택한 상품이 맞으신가요?(y/n)"," ");
		String answer = sc.nextLine();
		if (answer.equals("y") || answer.equals("Y")) {
//			Reconfirm(); ///비밀번호 검사 
			 	//일치하면
			System.out.printf("%22s가입 성공\n"," ");	
			
			AssetService.openDepo(bankDepo, titleDepo);
		
		} else {
			System.out.printf("%22s비밀번호가 불일치 합니다."," ");
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
