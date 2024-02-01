package com.mongle.view;

import java.util.Iterator;
import java.util.Scanner;

import com.mongle.resource.Investment;
import com.mongle.service.invest.Exchange;
import com.mongle.service.invest.Gold;
import com.mongle.service.invest.Loan;
import com.mongle.service.invest.Stock;

public class InvestmentView {
	
	public static void main(String[] args) {
		
		boolean loop = true;
		boolean loop3 = true;
		boolean loop4 = true;
		boolean loop5 = true;
		boolean loop6 = true;
	
		
		
		Scanner scan = new Scanner(System.in);
		
		while(loop) {
			
			menuHeader("투자 관리");
			
			System.out.println("1. 예적금");
			System.out.println("2. 주식");
			System.out.println("3. 골드");
			System.out.println("4. 환전");
			System.out.println("5. 대출");
			System.out.println("6. 펀드");
			System.out.println("7. 이전으로");
			
			System.out.print("사용자 입력 : ");
			String num = scan.nextLine();
			
			if(num.equals("1")) {
				
			}else if(num.equals("2")) {
				stocksave();
	
			}else if(num.equals("3")) { // 골드
				System.out.println();
				System.out.println(" 상세 정보 ");
				
				Gold.listGold.stream().forEach(g -> {
					System.out.printf("%d\t%,d원\n", g.getPrice(), g.getAmount());
				});
			
				
				while(loop3) {
					
					System.out.println(" 옵션 : ");
					System.out.println("1. 해제 ");
					System.out.println("2. 이전으로");
					System.out.println();
					System.out.print("사용자 입력 : ");
					String num3 = scan.nextLine();
					
					if(num3.equals("1")) {
						// 삭제 후 상세 불러오기
					}else if(num3.equals("2")) {
						loop3 = false;
						
					}
			}// while(loop3)
			}else if(num.equals("4")) {// 환전
				System.out.println();
				System.out.println("상세 보기");
				
				Exchange.listExchange.stream().forEach(e -> {
					System.out.printf("%d\t%,d원\n", e.getPrice(), e.getAmount());
				});
			
				
				while(loop4) {
					
					System.out.println(" 옵션 : ");
					System.out.println("1. 해제 ");
					System.out.println("2. 이전으로");
					System.out.println();
					System.out.print("사용자 입력 : ");
					String num4 = scan.nextLine();
					
					if(num4.equals("1")) {
						// 삭제후 상세 불러오기
					}else if(num4.equals("2")) {
						loop4 = false;
						
					}
			}// while(loop4)
				
				
			}else if(num.equals("5")) { // 대출
				System.out.println();
				System.out.println("상세보기");
				
				Loan.listLoan.stream().forEach(l -> {
					System.out.printf("%s\t%s\n", l.getBankDepo(), l.getTitleDepo());
				});
				
				
				while(loop5) {
					
					System.out.println(" 옵션 : ");
					System.out.println("1. 해제 ");
					System.out.println("2. 이전으로");
					System.out.println();
					System.out.print("사용자 입력 : ");
					String num5 = scan.nextLine();
					
					if(num5.equals("1")) {
						// 삭제후 상세 불러오기
					}else if(num5.equals("2")) {
						loop5 = false;
						
					}
			} //while(loop5)
			}else if(num.equals("6")) { // 펀드
				System.out.println();
				System.out.println("상세보기");
				
			
				
				
				while(loop6) {
					
					System.out.println(" 옵션 : ");
					System.out.println("1. 해제 ");
					System.out.println("2. 이전으로");
					System.out.println();
					System.out.print("사용자 입력 : ");
					String num6 = scan.nextLine();
					
					if(num6.equals("1")) {
						// 삭제후 상세 불러오기
					}else if(num6.equals("2")) {
						loop5 = false;
						
					}
			} //while(loop6)
			} else {
				loop = false;
				
			}
			
			
		}// while(loop)
		
		
		
		
	}//main
	


	private static void stocksave() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop2 = true;
		System.out.println();
		System.out.println("상세정보");
		
		// 받아와서 상세로 보여주기..
//		Investment.list.stream().forEach(s -> {
//			System.out.printf("%s\t%s\t%,d원\n", s.getTitleDepo(), s.getPrice(), s.getAmount());
//		});
//		
		Stock.listStock.stream().forEach(s -> {
			System.out.printf("%s\t%s\t%,d원\n", s.getBankDepo(), s.getPrice(), s.getAmount());
		});
	
		            
		
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
				
				if(num3.equals(Stock.getSel())) {
					Stock.listStock.remove(num3);
					
				}
				
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



	public static void menuHeader(String titleName) {
	    // 메뉴 헤더 화면
	    System.out.printf("%22s===================================\n"," ");
	    System.out.printf("%40s\n",titleName);
	    System.out.printf("%22s===================================\n"," ");

	}
	

}