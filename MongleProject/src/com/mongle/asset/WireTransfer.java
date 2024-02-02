package com.mongle.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.view.MongleVisual;

public class WireTransfer {
	
	public static void extracted() {
		Scanner scanner = new Scanner(System.in);
	
	boolean loop = true;
	while(loop) {
		MongleVisual.menuHeader("계좌 관리");
		System.out.println();
		System.out.printf("%22s 1.송금하기\n", " ");
		System.out.printf("%22s 2.더치페이\n", " ");
		System.out.printf("%22s 3.예약송금\n", " ");
		System.out.printf("%22s 0.이전으로\n", " ");
		System.out.printf("%22s번호를 입력하세요:", " ");
		String sel = scanner.nextLine();
		
		if(sel.equals("1")) {
			//송금하기
			
		}else if(sel.equals("2")) {
			//더치페이
			dutchpay();
		}else if(sel.equals("3")) {
			//예약송금
		}else if(sel.equals("0")) {
			//이전으로
		}
	}
		
	}//extracted
	
	private static void dutchpay() {
		// 더치페이 인원 설정 로직 구현
		Scanner scanner = new Scanner(System.in);
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
        
            break;
        default:
            System.out.println("올바른 공유 옵션을 선택하세요.");
    	}
	}//dutchpay
	
	private static void shareMessage() {
		String who = "";
	    System.out.println("메세지로 공유하기 - \"" + who + "\"에게 지불 요청 메시지를 전송합니다.");
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

//        private static void transferMoney(int depositAmount ) {
//		Scanner sc = new Scanner(System.in);
//		int result = 0;
//		
//		System.out.println("보내실 금액을 입력하여 주세요:");
//		int b = sc.nextInt();
//		if(depositAmount >= b){
//			result=depositAmount-b;
//			BankAccount.list.set();
//		} 
		
	
	}

	private static void transferMoney(String titleName) {
		// 메뉴 헤더 화면
		System.out.printf("%22s===================================\n", " ");
		System.out.printf("%40s\n", titleName);
		System.out.printf("%22s===================================\n", " ");

	}
	
	public static int transaction(int price, int num) {
		Scanner scan = new Scanner(System.in);
		
		MongleVisual.pusher();

		String header = "+---+---------------------+-----------------------+-----------------------+-----------------+";
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n", " ");
		System.out.printf("%22s%s\n", " ", header);
		List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
				.collect(Collectors.toList());
		print(filteredList); // json 에서 가져온 데이터
		System.out.printf("%22s%s\n", " ", header);
		System.out.printf("%22s송금할 계좌를 선택해 주세요.\n", " ");
		System.out.printf("%22s0. 이전으로\n", " ");
		boolean loop = true;

		while (loop) {
			System.out.printf("%22s선택(번호) : ", " ");
			String sel = scan.nextLine();
			int totalPrice = price * num;
			
			if (sel.equals("0")) {
				return 0;
			}

			try {
				if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {

					for (BankAccount acc : BankAccount.list) {
						if (acc.getAccountNumber()
								.equals(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber())) {
							if (acc.getDepositAmount() > totalPrice) {

								int rest = acc.getDepositAmount() - totalPrice;
								BankAccount.list.set(BankAccount.list.indexOf(acc), new BankAccount(acc.getBankDepo(),
										acc.getTitleDepo(), acc.getAccountNumber(), rest));
								
								System.out.println();
								System.out.printf("%22s주문가격 %,d원(시장가) / 주문 수량 : %s\n", " ", price, num);
								System.out.printf("%22s거래가 완료되었습니다.\n", " ");
								System.out.printf("%22s거래 후 잔액은 %,d원입니다.\n", " ", rest);
								System.out.printf("%22s홈 화면으로 돌아가려면 엔터를 눌러주세요.\n", " ");
								scan.nextLine();
								loop = false;
							} else if (acc.getDepositAmount() < totalPrice) {
								System.out.printf("%22s계좌의 잔액이 부족합니다.\n", " ");
								System.out.printf("%22s다시 선택해주세요.\n", " ");
							}
						}
					}
				} else {
					System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
				}
			} catch (NumberFormatException e) {
				System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
			}
		}
		return 0;

	}
	public static void print(List<BankAccount> data) { // 표에 반복해서 출력하는 메서드
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", i + 1, data.get(i).getBankDepo(),
					data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

		}
	}
}
