package com.mongle.service.mypage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.LogIn;

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
	
	public static boolean validSafe(String id) {
		for (HashMap map : DataBase.getUser()) {
			if (map.get("id").equals(id)) {
				return map.get("safesendsetting").equals("1");
			}
		}
		return false;
	}
	
	public static void safeGetMoney() {
		
		if (!validSafe(LogIn.primaryKey)) {
			return;
		}
		
		Scanner scan = new Scanner(System.in);
		
		String fromwho = null;
		String fromacc = null;
		String towho = null;
		String toacc = null;
		String money = null;
		String senddate = null;
		
		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			String path = ResourcePath.SAFE+"//"+LogIn.primaryKey+".dat";
			FileReader reader = new FileReader(path);
			File file = new File(path);
			
			if (!file.exists()) {
				return;
			}
			
			JSONObject jobj = (JSONObject) parser.parse(reader);
			
			fromwho = (String) jobj.get("보내는분");
			fromacc = (String) jobj.get("출금 계좌");
			towho = (String) jobj.get("받는분");
			toacc = (String) jobj.get("송금 계좌");
			money = (String) jobj.get("금액");
			senddate = (String) jobj.get("날짜");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
