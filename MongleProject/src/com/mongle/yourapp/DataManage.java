package com.mongle.yourapp;

import java.io.FileReader;
import java.security.KeyStore.TrustedCertificateEntry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.spi.CurrencyNameProvider;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.view.MongleVisual;

public class DataManage {
	public static void dataManage() {
		System.out.printf("\n%22s데이터 출력\n"," ");
		System.out.printf("\n%22s출력하시고 싶은 데이터를 선택해주세요\n"," ");
		System.out.printf("\n%22s1. 이용자수 (일,월,년)\n"," ");
		System.out.printf("\n%22s2. 총 연결된 계좌수\n"," ");
		System.out.printf("\n%22s3. 거래가 Mongle에서 일어난 횟수 (일,월,년)\n"," ");
		System.out.printf("\n%22s4. 은행별 등록된 계좌 계수\n"," ");
		System.out.println();
		MongleVisual.choiceGuidePrint();
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		
		if(choice.equals("1")) {
			userNum();
		}else if(choice.equals("2")){
			//accNum();
		}else if(choice.equals("3")) {
		//	transNum();
		}else if(choice.equals("4")) {
			//bankNum();
		}else {
		//	advanceCalc();
		}
		
	}
	
	

	private static int userNum() {
		int count = 0 ;
		
		System.out.printf("\n%22s1. 이용자수 (일)\n"," ");
		System.out.printf("\n%22s2. 이용자수 (월)\n"," ");
		System.out.printf("\n%22s3. 이용자수 (년)\n"," ");
		System.out.println();
		MongleVisual.choiceGuidePrint();
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		//dataGet(count);
		if(choice.equals("1")) {
			dataGet(1);
		}else if(choice.equals("2")){
			dataGet(2);
		}else if(choice.equals("3")) {
			dataGet(3);
		}
		return 0;
	}

	public static int dataGet(int count) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		ArrayList<String> checkdate = new ArrayList<String>();
		Calendar c1 = Calendar.getInstance();
		String currentDate = String.format("%tF\n", c1);
		currentDate = currentDate.replace("-", "");
		int numCount = 0;
		int cdate = 0;
		if(count ==1) {
			 cdate = Integer.parseInt(currentDate.substring(0, 8));
		}else if (count==2) {
			 cdate = Integer.parseInt(currentDate.substring(0, 6));
		}else if(count==3) {
			 cdate = Integer.parseInt(currentDate.substring(0, 4));
		}else {
			MongleVisual.wrongInput();
			return 0;
		}
		
		
		
		//System.out.println(currentDate);
		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			FileReader reader = new FileReader(ResourcePath.MEMBER);
			// JSON 데이터를 파싱하여 JSONArray로 변환
			JSONArray visit = (JSONArray) parser.parse(reader);
			
			for(Object obj : visit) {
				JSONObject jsonObject = (JSONObject)obj;
				JSONArray array = (JSONArray)jsonObject.get("attend");
				
				if(array!=null) {
					for(Object key: array) {
						JSONObject aten = (JSONObject)key;
						JSONArray n = (JSONArray) aten.get("attenddate");
                        ArrayList<String> date = (ArrayList<String>) aten.get("attenddate");
                        int attendence = 0;
                        for (String check : date) {
                        	check = check.replace("-", "");
                        	if(count ==1) {
                        		attendence = Integer.parseInt(check.substring(0, 8));
                        	}else if (count==2) {
                        		attendence = Integer.parseInt(currentDate.substring(0, 6));
                        	}else {
                        		attendence = Integer.parseInt(currentDate.substring(0, 4));
                        	}
                   			
                   		}
                            if(cdate==attendence) {
                            	numCount++;
                            }
                        }
					}
				}
			if(count ==1) {
				System.out.printf("\n%22s일별유저 : %d\r\n"," ",numCount);
			}else if (count==2) {
				System.out.printf("\n%22s월별유저 : %d\r\n"," ",numCount);
			}else {
				System.out.printf("\n%22s년별유저 : %d\r\n"," ",numCount);
				
			}
			
		} catch (Exception e) {
			System.out.println("DataManage.dataGet");
			e.printStackTrace();
		}
		return cdate;
		
	
	}
}
