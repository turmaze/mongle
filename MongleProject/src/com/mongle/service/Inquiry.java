package com.mongle.service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.view.MongleVisual;

public class Inquiry {
	private static ArrayList<HashMap> annList = new ArrayList<HashMap>();
	private static ArrayList<HashMap> inqList = new ArrayList<HashMap>();

	private String title;
	private String detail;
	
	
	
	public static void announcement() {
		try {
			Scanner scanner = new Scanner(System.in);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File(ResourcePath.MEMBER);
			FileWriter writer = new FileWriter(file, false);

			//level 탐색
			HashMap<String, Object> selectedMap = DataBase.getPrivateUser().get(0); //고정 값
			Object levelValue = selectedMap.get("level");
		
			//관리자
			if(levelValue.equals("2")) {
				MongleVisual.menuHeader("관리자 공지사항");
				//공지사항 제목 목록
				System.out.printf("\n%22s1. 생성\n"," ");
				System.out.printf("%22s2. 수정\n"," ");
				System.out.printf("%22s3. 삭제\n\n"," ");
				System.out.printf("%22s0. 이전으로\n\n"," ");
				
				Inquiry inquiry = new Inquiry();
				
				String input = "7"; //1,2,3,0이 아닌 값
				do {
					switch(input = scanner.nextLine()) {
						case "1":
							System.out.printf("%22\n"," ");
							inquiry.createInq();

							break;
						case "2":
							System.out.printf("%22s\n"," ");
							inquiry.editInq();

							break;
						case "3":
							System.out.printf("%22s\n"," ");
							inquiry.deleteInq();
							
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

			writer.write(gson.toJson(annList));
			writer.flush(); // 버퍼 비우기

			writer.close();

			
		} catch (Exception e) {
			System.out.println("announcement Error");
			e.printStackTrace();
		}

	}
	
	private void createInq() {
		
		
	}
	private void editInq() {
		
		
	}
	private void deleteInq() {
		
		
	}
	
	private void loadInq() {
		
	}
	private void saveInq() {
		
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