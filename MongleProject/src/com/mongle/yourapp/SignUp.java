package com.mongle.yourapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mongle.view.MongleVisual;
import com.mongle.database.DataBase;
import com.mongle.yourapp.Encrypt;


public class SignUp {
	public static void signUp() {
		Scanner scan = new Scanner(System.in);
		
		MongleVisual.menuHeader("회원가입");
	
		//----------------id----------------
		String id;
		do {
		System.out.printf("\n%22s(4~12자리 영문과 숫자 조합)\n"," ");
		System.out.printf("%22s1.아이디: "," ");		
		id = scan.nextLine();
		}
		while(!DataBase.validId(id));

		
		//----------------pw----------------
		String pw1,pw2;
		do {
			do {
				System.out.printf("\n%22s(10~16자리 영문과 숫자 조합)\n"," ");
				System.out.printf("%22s2.비밀번호: "," ");		
				pw1 = scan.nextLine();
			} while(!DataBase.validPw(pw1));
	
			System.out.printf("\n%22s3.비밀번호 재확인: "," ");		
			pw2 = scan.nextLine();
			
			//DataBase.user pw 추가
			if (pw1.equals(pw2)) {
				HashMap<String, String> newUser = new HashMap<String, String>();  
				
				String finPw = Encrypt.encrypt(pw2); //pw 암호화
				
				newUser.put("비밀번호", finPw);  // 유효한 비밀번호를 HashMap에 저장
				DataBase.setUser(newUser);  // HashMap을 ArrayList에 추가
				break;
			} else {
				System.out.printf("\n%22s비밀번호가 다릅니다.\n\n", " ");
			}
			
		} while(!pw1.equals(pw2));
		
		//pw1을 DataBase.java의 HashMap("비밀번호",pw1)으로 저장하고 HashMap을 DataBase.java의 Arraylist에 저장하는 코드			// System.out.println(m.matches()); //testcode

		
		//test
		System.out.printf("\n%22stest ArrayList Test : ", " ");
		System.out.println(DataBase.getUser());
		//----------------name----------------
		
		
		
		
		
		//----------------final----------------
		
		DataBase.dataSave();
		scan.close();
	}

}

//System.out.printf("%22s(4~12자리 영문과 숫자 조합)\n"," ");
//System.out.printf("%22s1.아이디: "," ");		
//String id = scan.nextLine();
//
//while(!DataBase.validId(id)) {
//	System.out.printf("%22s(4~12자리 영문과 숫자 조합)\n"," ");
//	System.out.printf("%22s1.아이디: "," ");		
//	id = scan.nextLine();
//}
