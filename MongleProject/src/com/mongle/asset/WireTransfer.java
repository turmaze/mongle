package com.mongle.asset;

import java.util.Scanner;

import com.mongle.resource.BankAccount;

public class WireTransfer {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
			
		
		
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
	
	private static void transferMoney(Scanner scanner) {
		// 송금로직 구현
		 System.out.println("현재 계좌 잔액: " + balance + "원");
	     System.out.print("송금할 금액을 입력하세요: ");
	     
	     int transferAmount = scanner.nextInt(); // int 형태로 송금액 입력 받기
	     
	     System.out.print("수취인 계좌를 입력하세요: ");
	     String recipientAccount = scanner.next(); 
	     
	     BankAccount recipient = BankAccount.findAccount(recipientAccount); // 수취인 계좌 찾기
	     
	        if (recipient != null) {
	            if (transferAmount > 0 && transferAmount <= balance) {
	                balance -= transferAmount; // 송금액만큼 잔액에서 차감
	                recipient.deposit(transferAmount); // 수취인 계좌에 송금액 입금
	                System.out.println("송금이 완료되었습니다.");
	            } else {
	                System.out.println("잔액이 부족하거나 올바르지 않은 금액입니다.");
	            }
	        } else {
	            System.out.println("수취인 계좌를 찾을 수 없습니다.");
	        }


	  // 입력한 계좌번호 확인 후 송금 여부 묻기
	        
	     System.out.println("입력하신 계좌번호 : " + recipientAccount);
	     System.out.print("이 계좌번호로 송금하시겠습니까? (Y/N): ");
	     String confirmation = scanner.next();

	     if (confirmation.equalsIgnoreCase("Y")) {
	         System.out.println("송금이 완료되었습니다.");
	         // 송금 로직 추가
	     } else {
	         System.out.println("송금이 취소되었습니다.");
	     }
	     System.out.println(result);
	     
	     // 메뉴 활성화
	     System.out.println("1. 이전으로");
	     System.out.println("2. 홈으로");
	     System.out.print("메뉴를 선택하세요: ");
	     int menuChoice = scanner.nextInt();

	     switch (menuChoice) {
	         case 1:
	             // 이전으로 돌아가는 로직 추가
	             break;
	         case 2:
	             System.out.println("홈으로 이동합니다.");
	             return;
	         default:
	             System.out.println("올바른 메뉴를 선택하세요.");
	     }
	 }	
	 
	
	
	
	 private static void reserveTransfer(Scanner scanner) {
		// 예약송금 로직 구현
	  System.out.print("예약할 시간을 입력하세요.\n");

      // 년도는 수정이 불가능하므로 고정
      int year = 2024;

      // 이번달과 다음달까지 선택 가능하도록 설정
      System.out.print("월을 입력하세요. 예약은 다음달까지만 가능합니다. (이번달: 1, 다음달: 2): ");
      int month = scanner.nextInt();

      if (month != 1 && month != 2) {
          System.out.println("[오류 발생] 올바른 월을 입력하세요.");
          return;
      }

      int maxDay;
      if (month == 1) {
          maxDay = 31; // 이번달의 말일
      } else {
          maxDay = 30; // 다음달의 말일
      }

      System.out.print("일을 입력하세요 (1일부터 " + maxDay + "일까지): ");
      int day = scanner.nextInt();

      if (day < 1 || day > maxDay) {
          System.out.println("[오류 발생] 올바른 일을 입력하세요.");
          return;
      }

      // 시간은 정수형태로 입력받도록 함
      System.out.print("시간을 입력하세요: "); //시간단위로만 입력받을 수 있음 ex) 13:26(x) // 13 (0)
      int hour = scanner.nextInt();

      if (hour < 0) {
          System.out.println("[오류 발생] 정수단위로 입력해주세요.");
          return;
      }

      // 송금 확인 메시지
      System.out.print("[" + year + "년 " + month + "월 " + day + "일 " + hour + "시]에 예약 송금하시겠습니까? (예/아니오): ");
      String confirm = scanner.next();

      if (confirm.equalsIgnoreCase("예")) {
          // 예약 송금 로직
          System.out.println("예약이 완료되었습니다. 예약 시간: " + year + "년 " + month + "월 " + day + "일 " + hour + "시");
      } else if (confirm.equalsIgnoreCase("아니오")) {
          System.out.println("예약이 취소되었습니다.");
      } else {
          System.out.println("올바른 선택을 해주세요.");
      }
   // 메뉴 활성화
      System.out.println("1. 이전으로");
      System.out.println("2. 홈으로");
      System.out.print("메뉴를 선택하세요: ");
      int menuChoice = scanner.nextInt();

      switch (menuChoice) {
          case 1:
              // 이전으로 돌아가는 로직 추가
              break;
          case 2:
              System.out.println("홈으로 이동합니다.");
              return;
          default:
              System.out.println("올바른 메뉴를 선택하세요.");
      }
  }
	
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
    who = scanner.next();

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


private static void menuheader(String titleName) {
		// 메뉴 헤더 화면
        System.out.printf("%22s===================================\n"," ");
        System.out.printf("%40s\n",titleName);
        System.out.printf("%22s===================================\n"," ");
	}
}
