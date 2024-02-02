package com.mongle.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.service.invest.Exchange;
import com.mongle.service.invest.Gold;
import com.mongle.service.invest.Loan;
import com.mongle.service.invest.Stock;

public class InvestmentView {
	
	
		
	public static void addmenu() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			
			MongleVisual.menuHeader("투자 관리");
			
			System.out.println("1. 주식");
			System.out.println("2. 골드");
			System.out.println("3. 펀드");
			System.out.println("4. 환전");
			System.out.println("5. 대출");
			System.out.println("6. 이전으로");
			
			System.out.print("사용자 입력 : ");
			String num = scan.nextLine();
			
			if(num.equals("1")) { // 주식
				stocksave();
			}else if(num.equals("2")) { //골드
				goldsave();
			}else if(num.equals("3")) { // 펀드
				fundsave();
			}else if(num.equals("4")) {// 환전
				Exchangesave();
			}else if(num.equals("5")) { // 대출
				loansave();
				
			} else {
				loop = false;
				
			}
		}
			
		//return 0;
		
	}

	private static void loansave() { // 대출
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		System.out.println();
		System.out.println("상세 보기");
	
		
		printAsciiTable(Investment.list,"대출");
	
		
		while(loop) {
			
			System.out.println(" 옵션 : ");
			System.out.println("1. 해제 ");
			System.out.println("2. 이전으로");
			System.out.println();
			System.out.print("사용자 입력 : ");
			String num4 = scan.nextLine();
			
			if(num4.equals("1")) {
				// 삭제후 상세 불러오기
			}else if(num4.equals("2")) {
				loop = false;
				
			}
	}// while(loop4)
			
		
	}




	


	private static void Exchangesave() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		System.out.println();
		System.out.println("상세 보기");
		
		printAsciiTable(Investment.list,"환전");
	
		
		while(loop) {
			
			System.out.println(" 옵션 : ");
			System.out.println("1. 해제 ");
			System.out.println("2. 이전으로");
			System.out.println();
			System.out.print("사용자 입력 : ");
			String num4 = scan.nextLine();
			
			if(num4.equals("1")) {
				// 삭제후 상세 불러오기
			}else if(num4.equals("2")) {
				loop = false;
				
			}
	}
		
		
	}



	private static void fundsave() {
//		Scanner scan = new Scanner(System.in);
//		boolean loop = true;
//		System.out.println();
//		System.out.println(" 상세 정보 ");
//
//
//		printAsciiTable(Investment.list);
//		
//		while(loop) {
//			
//			System.out.println(" 옵션 : ");
//			System.out.println("1. 해제 ");
//			System.out.println("2. 이전으로");
//			System.out.println();
//			System.out.print("사용자 입력 : ");
//			String num3 = scan.nextLine();
//			
//			if(num3.equals("1")) {
//				// 삭제 후 상세 불러오기
//			}else if(num3.equals("2")) {
//				loop = false;
//				
//			}
//	}
		
		
	}



	private static void goldsave() {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		System.out.println();
		System.out.println("상세정보");
		
		printAsciiTable(Investment.list,"금");
		
		while(loop) {
			
			System.out.println(" 옵션 : ");
			System.out.println("1. 해제 ");
			System.out.println("2. 이전으로");
			System.out.println();
			System.out.print("사용자 입력 : ");
			String num3 = scan.nextLine();
			
			if(num3.equals("1")) {
				// 삭제 후 상세 불러오기
				System.out.println("해제하고싶은 번호를 입력해주세요 :");
				int removeN = scan.nextInt();
			removeLoanInvestmentByJ(Investment.list, removeN);
			printAsciiTable(Investment.list,"금");
			break;
			}else if(num3.equals("2")) {
				loop = false;
				
			}
		}
	
	}



	

	private static void stocksave() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop2 = true;
		System.out.println();
		System.out.println("상세정보");
		
		
		printAsciiTable(Investment.list,"주식");
		
		            
		
		while(loop2) { 
			
			System.out.println(" 옵션 : ");
			System.out.println("1. 매수 ");
			System.out.println("2. 일괄매도");
			System.out.println("3. 이전으로");
			System.out.println();
			System.out.print("사용자 입력 : ");
			String num2 = scan.nextLine();
			
			if(num2.equals("1")) { // 매수
				
				System.out.print("매수할 상품번호를 선택하시오 : ");
				String num3= scan.nextLine();
				
				
				System.out.println("매수할 금액을 입력해 주세요 : ");
				String price = scan.nextLine();
				
				
				
				// 매수하고 상세보기 불러오기
				
				
	
			}else if(num2.equals("2")) { // 일괄매도
				
				System.out.print("일괄매도할 상품번호를 선택하시오:");
				String num4 = scan.nextLine();
				System.out.print("일괄매도할 금액을 입력해 주세요 :");
				String price2 = scan.nextLine();
				
				// 매도가 완료됐다는 알림과 함께 상세보기
				System.out.println();
			}else if(num2.equals("3")) {
				loop2 = false;
				
			}
		}
		
	}



	public static void printAsciiTable(ArrayList<Investment> data,String invest) { // 표에 반복해서 출력하는 메서드
			int j = 0;
		for (int i = 0; i < data.size(); i++) {
			if (invest.equals(data.get(i).getRealTitle())){
			System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", j + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getPrice(), data.get(i).getAmount());
			j++;
			}
			
		}
	}
	
	private static void removeLoanInvestmentByJ(ArrayList<Investment> data, int removeN) {
			int j = 0;
		 for (int i = 0; i < data.size(); i++) {
	         if ("금".equals(data.get(i).getRealTitle())) {
	            j++;
	             if (j == removeN) {
	                 data.remove(i);
	                 return; // 삭제 후 바로 메서드 종료
	             }
	         }
		 }
	}
	



	

}