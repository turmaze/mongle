package com.mongle.asset;

import java.util.Scanner;

public class WireTransfer {

	private static double  balance; //초기 잔액 변수 선언
	private static String accountBalance;
	//private static Object titleName;
	private static String who;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
			
		//초기잔액 설정
		System.out.println("초기 잔액을 입력하세요: ");
		balance = scanner.nextDouble();
			
			while(true) {
				menuheader("송	금");
				System.out.println("1. 송금하기");
				System.out.println("2. 송금예약");
				System.out.println("3. 더치페이");
				System.out.println("4. 종료하기");
				System.out.print("메뉴 번호를 선택하세요: ");
				int choice = scanner.nextInt();
				
				if(choice >=1 && choice <= 4) {
					switch(choice) {
						case 1: 
							menuheader("1. 송금");
							transferMoney(scanner);
							break;
						case 2:
							menuheader("2. 송금 예약");
							reserveTransfer(scanner);
							break;
						case 3:
							menuheader("3. 더치페이");
							dutchpay(scanner);
							break;
						case 4:
							System.out.println("4. 프로그램을 종료합니다.");
							return;
					}
					
				} else {
					System.out.println("1부터 4까지의 숫자를 입력하세요.");
				}
			}
			
}//main
	
private static void dutchpay(Scanner scanner) {
		// 더치페이 인원 설정 로직 구현
	System.out.print("더치페이 금액을 설정하세요 (정수형태): ");
    int amount = 0;

    // 정수 입력을 위한 예외 처리
    try {
        amount = scanner.nextInt();
    } catch (Exception e) {
        System.out.println("[오류 발생] 정수단위로 입력해주세요.");
        scanner.nextLine(); // 버퍼 비우기
        return;
    }

    if (amount < 0) {
        System.out.println("[오류 발생] 음수는 입력할 수 없습니다.");
        return;
    }

    // 더치페이 금액 설정 로직
    System.out.println("더치페이 금액이 " + amount + "원으로 설정되었습니다.");
	
    // 더치페이 금액 설정 로직 구현

    System.out.print("지불 대상자를 입력하세요: ");
    String who = scanner.next();

    // 공유하기-메세지/카카오톡으로 전송하는 창 띄우기
    System.out.println("1. 메시지로 공유하기");
    System.out.println("2. 카카오톡으로 공유하기");
    System.out.print("공유 옵션을 선택하세요: ");
    int shareChoice = scanner.nextInt();
    
    switch (shareChoice) {
        case 1:
            shareMessage();
            break;
        case 2:
            shareKakaoTalk();
            break;
        default:
            System.out.println("올바른 공유 옵션을 선택하세요.");
    }
	}

private static void shareMessage() {
    System.out.println("메세지로 공유하기 - \"" + who + "\"에게 지불 요청 메시지를 전송합니다.");
	}

private static void shareKakaoTalk() {
    System.out.println("카카오톡으로 공유하기 - \"" + who + "\"에게 지불 요청 메시지를 전송합니다.");
}






private static void reserveTransfer(Scanner scanner) {
		// 예약송금 로직 구현
	}

private static void transferMoney(Scanner scanner) {
	// 송금로직 구현
	 System.out.println("현재 계좌 잔액: " + balance + "원");
     System.out.print("송금할 금액을 입력하세요: ");
     double transferAmount = scanner.nextDouble();
     double result = 0;
     System.out.println("송금 전 계좌 잔액: " + balance + "원");
     if (transferAmount > 0 && transferAmount <= balance) {
       result = balance-transferAmount;
    	 System.out.println(result);
         System.out.println("송금이 완료되었습니다.");
     } else {
         System.out.println("잔액이 부족하거나 올바르지 않은 금액입니다.");
     }

     System.out.println(result);
 }

private static void menuheader(String titleName) {
		// 메뉴 헤더 화면
        System.out.printf("%22s===================================\n"," ");
        System.out.printf("%40s\n",titleName);
        System.out.printf("%22s===================================\n"," ");
		
	}
}
