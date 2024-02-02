package com.mongle.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.LogIn;

public class Inquiry {
	private static ArrayList<HashMap> annList = new ArrayList<HashMap>();
	private static ArrayList<HashMap> inqList = new ArrayList<HashMap>();

	private static String title;
	private static String detail;
	
	
	
	public static void announcement() {
		try {
			Scanner scanner = new Scanner(System.in);
	
//			//level 탐색
//			HashMap<String, Object> selectedMap = DataBase.getPrivateUser().get(0); //고정 값
//			Object levelValue = selectedMap.get("level");
//		
//			//관리자
//			if(levelValue.equals("2")) {
			if(true) {
				Inquiry inquiry = new Inquiry();
				inquiry.loadInq(annList,ResourcePath.ANNO);
				
				MongleVisual.menuHeader("관리자 공지사항");
				//공지사항 제목 목록
				System.out.printf("\n%22s1. 생성\n"," ");
				System.out.printf("%22s2. 수정\n"," ");
				System.out.printf("%22s3. 삭제\n\n"," ");
				System.out.printf("%22s0. 이전으로\n\n"," ");
				
				
				String input = "7"; //1,2,3,0이 아닌 값
				do {
					switch(input = scanner.nextLine()) {
						case "1":
							System.out.printf("%22s\n"," ");
							inquiry.createInq();
							inquiry.saveInq(annList,ResourcePath.ANNO);
							break;
						case "2":
							System.out.printf("%22s\n"," ");
							inquiry.editInq();
							inquiry.saveInq(annList,ResourcePath.ANNO);
							break;
						case "3":
							System.out.printf("%22s\n"," ");
							inquiry.deleteInq();
							inquiry.saveInq(annList,ResourcePath.ANNO);
							break;
						case "0":
							System.out.printf("%22s0.입력 됨\n"," ");

							break;
							
						default :
							System.out.printf("%22s잘못된 번호입니다.\n 다시 입력해주세요\n"," ");
						}
				} while(!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("0")) );

				
							
				
			} //if(levelValue.equals("2"))
			
//			else { // 회원,  블랙리스트"3"은 여기까지 안옴 //문의사항에서 사용할 code
//				
//				System.out.println("문의사항에 필요한 else문");
//			}

//			writer.write(gson.toJson(annList));
//			inquiry.loadInq(annList);
//			
//			writer.flush(); // 버퍼 비우기
//
//			writer.close();

			
		} catch (Exception e) {
			System.out.println("announcement Error");
			e.printStackTrace();
		}

	}
	
	private void createInq() {
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Object> inq = new HashMap<>();
		String value;
		
		
		//inq.put("id", LogIn.primaryKey);
		inq.put("id", "primaryKey");
		
		
		System.out.print("제목: ");
		value = scanner.nextLine();
		while (value.contains(":q!")) {
		    System.out.print("오류. 재입력.\n");
		    System.out.printf("제목: ");
		    value = scanner.nextLine();
		}
	
		inq.put("title", value);
		
		
        System.out.printf("첫 줄에서 ':q!' 시 종료\n");
        System.out.printf("내용: ");

        StringBuilder contentBuilder = new StringBuilder(); // 내용을 저장할 StringBuilder를 생성합니다.
        while (true) {
            value = scanner.nextLine();
            if (value.equals(":q!")) {
                break; // :q!를 입력하면 루프를 종료합니다.
            }
            contentBuilder.append(value).append("\n"); // 내용을 StringBuilder에 추가하고 개행 문자를 넣습니다.
        }

        String content = contentBuilder.toString().trim(); // StringBuilder의 내용을 문자열로 변환하고 양쪽 공백을 제거합니다.
        inq.put("txt", content);

        annList.add(inq);
        System.out.println(annList);
	}
	
	
	private void editInq() {
		Scanner scanner = new Scanner(System.in);		
		
	}
	private void deleteInq() {
		Scanner scanner = new Scanner(System.in);
		
	}
	

		
	/**
 
		annList || inqList
		ResourcePath.ANNO || ResourcePath.INQUIRY
	 
	 */
	private void saveInq(ArrayList list, String path) {
		try {

			// set pretty printing
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File(path);
			// System.out.println(file.getAbsolutePath());// 경로 찾는 테스트 코드
			FileWriter writer = new FileWriter(file, false); // 덮쓰

			// String json = gson.toJson(user);
			writer.write(gson.toJson(list));
			writer.flush(); // 버퍼 비우기

			writer.close();
			System.out.printf("\n%22s저장완료 ", " "); // testcode

		} catch (Exception e) {
			System.out.println("DataBase.dataSave Error");
			e.printStackTrace();
		}

		
	}
	
	private void loadInq(ArrayList list, String path) {
		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			FileReader reader = new FileReader(path);
			// JSON 데이터를 파싱하여 JSONArray로 변환
			JSONArray userList = (JSONArray) parser.parse(reader);

			list.clear(); // 기존 리스트를 비움
			Iterator<Object> iterator = userList.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();
				HashMap<String, Object> userData = new HashMap<>();
				// 가정: JSON 객체의 모든 키는 문자열이고, 값도 문자열임
				for (Object key : jsonObject.keySet()) {
					userData.put((String) key, jsonObject.get(key));
				}
				list.add(userData); // 읽은 데이터를 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	
	
	private void inquiry() {
		
	}
	

	
	
	
	
	
	
//	private String titleInq;
//	private String txtInq;
//	private String reply;
//
//	public String getReply() {
//		return reply;
//	}
//
//	public void setReply(String reply) {
//		this.reply = reply;
//	}
//
//	public Inquiry(String titleInq, String txtInq) {
//		super();
//		this.titleInq = titleInq;
//		this.txtInq = txtInq;
//	}
//
//	public String getTitleInq() {
//		return titleInq;
//	}
//
//	public void setTitleInq(String titleInq) {
//		this.titleInq = titleInq;
//	}
//
//	public String getTxtInq() {
//		return txtInq;
//	}
//
//	public void setTxtInq(String txtInq) {
//		this.txtInq = txtInq;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("Inquiry [titleInq=%s, txtInq=%s]", titleInq, txtInq);
//	}

}