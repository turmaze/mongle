package com.mongle.service.mypage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;

public class SafeSend {

	public static int safeSendService() {

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {

			MongleVisual.pusher();

			MongleVisual.menuHeader("안심송금");

			System.out.println();
			System.out.println();

			System.out.printf("%22s1. 사용 설정\n", " ");
			System.out.printf("%22s2. 미사용 설정\n", " ");
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.printf("%22s선택(숫자): ", " ");
			String sel = scan.nextLine();

			if (sel.equals("1")) {
				
				MongleVisual.pusher();
				
				for (HashMap map : DataBase.getPrivateUser()) {
					map.replace("safesendsetting", "1");
				}
				
				System.out.printf("%22s안심송금 서비스 사용으로 설정되었습니다.\n", " ");
				
				MongleVisual.stopper();
			} else if (sel.equals("2")) {
				
				MongleVisual.pusher();
				
				for (HashMap map : DataBase.getPrivateUser()) {
					map.replace("safesendsetting", "0");
				}
				
				System.out.printf("%22s안심송금 서비스 미사용으로 설정되었습니다.\n", " ");
				
				MongleVisual.stopper();
			} else if (sel.equals("9")) {
				return 9;
			} else if (sel.equals("0")) {
				return 0;
			}

		}
		return 0;

	}
	
	public static void safeSendMoney(String fromwho, String fromacc
									, String towho, String toacc
									, String money, String senddate) {
		
		Scanner scan = new Scanner(System.in);
		
		if (DataBase.getPrivateUser().get(0).get("safesendsetting").equals("1")) {
			System.out.printf("%22s%s님으로부터 송금이 들어왔습니다.\n", " ", fromwho);
			System.out.printf("%22s금액: %,d원\n", " ", money);
			System.out.printf("%22s송금을 받으시겠습니까?(y/n)", " ");
			String sel = scan.nextLine();
			sel = sel.toLowerCase();
			
			if (sel.equals("y")) {
				DataBase.getPrivateUser().get(0).get("acc"); //계좌에 금액 추가
			} else if (sel.equals("n")) {
				//fromwho의 fromacc로 money 반환 및 senddate 날짜 입력
			} else {
				MongleVisual.wrongInput();
			}
			
		}
		return;
	}
	
	public static void safeSendAlam() {
		String path = "송금 내역 경로"; //파일명=받는사람
    	File file = new File(path);
    	String wire = null;
    	try {
    		
    		if (file.exists()) {
    			BufferedReader reader = new BufferedReader(new FileReader(file));
    			
    			wire = reader.readLine();
    			
    			reader.close();
    		}
			
		} catch (Exception e) {
			System.out.println("SafeSend.safeSendAlam");
			e.printStackTrace();
		}
	}

}
