package com.mongle.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongle.database.DataBase;
import com.mongle.resource.ResourcePath;
import com.mongle.view.MongleVisual;
import com.mongle.yourapp.LogIn;
import com.mongle.yourapp.MainMenu;

public class Inquiry {
	private static ArrayList<HashMap> annList = new ArrayList<HashMap>();
	private static ArrayList<HashMap> inqList = new ArrayList<HashMap>();

	private static ArrayList<String> TitleList = new ArrayList<String>();
	
	/**
	 * 관리자 공지사항 실행
	 */
	public static void adminAnnouncement() {
		try {
			Scanner scanner = new Scanner(System.in);
			
			String input = "7"; //1,2,3,0이 아닌 값	
			while(!input.equals("0")) {	//
				//level 탐색
				HashMap<String, Object> selectedMap = DataBase.getPrivateUser().get(0); //고정 값
				Object levelValue = selectedMap.get("level");
				Inquiry inquiry = new Inquiry();
				inquiry.loadInq(annList,ResourcePath.ANNO);
				
				//관리자
				if(levelValue.equals("2")) {
					
					System.out.println();
					MongleVisual.menuHeader("관리자 공지사항");
	
					System.out.printf("\n%22s1. 공지사항 확인\n"," ");
					System.out.printf("%22s2. 생성\n"," ");
					System.out.printf("%22s3. 수정\n"," ");
					System.out.printf("%22s4. 삭제\n\n"," ");
					System.out.printf("%22s0. 이전으로\n\n"," ");
					
					
					System.out.printf("%22s입력: ", " ");
					do {
						switch(input = scanner.nextLine()) {
							case "1":
								inquiry.showTxt(annList);
								
								break;
							case "2":
								inquiry.createInq(annList);
								inquiry.saveInq(annList,ResourcePath.ANNO);
								break;
							case "3":
								inquiry.editInq(annList);
								inquiry.saveInq(annList,ResourcePath.ANNO);
								break;
							case "4":
								inquiry.deleteInq(annList);
								inquiry.saveInq(annList,ResourcePath.ANNO);
								break;
							case "0":
								System.out.printf("%22s관리자 페이지로 이동합니다.\n\n"," ");
								break;
								
							default :
								System.out.printf("%22s잘못된 번호입니다.\n%22s다시 입력해주세요\n"," "," ");
							}
					} while(!(input.equals("1") || input.equals("2") ||
							  input.equals("3") || input.equals("4") || input.equals("0")) );
	
				} //if
												
			} //while(true)
			
			
		} catch (Exception e) {
			System.out.println("announcement Error");
			e.printStackTrace();
		}

	} //adminAnnouncement() 끝
	
	/**
	 * 회원 공지사항 실행
	 */
	public static void mebmerAnnouncement() {
		try {
			Scanner scanner = new Scanner(System.in);
			
			String input = "7"; //1,2,3,0이 아닌 값	
			while(!input.equals("0")) {	//
				//level 탐색
				HashMap<String, Object> selectedMap = DataBase.getPrivateUser().get(0); //고정 값
				Object levelValue = selectedMap.get("level");
				Inquiry inquiry = new Inquiry();
				inquiry.loadInq(annList,ResourcePath.ANNO);
				
				//회원
				if(levelValue.equals("1")) {
					
					System.out.println();
					MongleVisual.menuHeader("공지사항");
	
					System.out.printf("\n%22s1. 공지사항 확인\n"," ");

					System.out.printf("%22s0. 이전으로\n\n"," ");
					
					
					System.out.printf("%22s입력: ", " ");
					do {
						switch(input = scanner.nextLine()) {
							case "1":
								inquiry.showTxt(annList);								
								break;
							case "0":
								System.out.printf("%22s회원 페이지로 이동합니다.\n\n"," ");
								break;
								
							default :
								System.out.printf("%22s잘못된 번호입니다.\n%22s다시 입력해주세요\n"," "," ");
							}
					} while(!(input.equals("1") || input.equals("0")) );
	
				} //if
												
			} //while(true)
			
			
		} catch (Exception e) {
			System.out.println("announcement Error");
			e.printStackTrace();
		}

	} //memberAnnouncement() 끝
	
	/**
	 * 문의사항 실행
	 */
	private void inquiry() {
		//1. 목록 확인
		//2. 글 수정 -> 수정할 글의 제목 받기 -> 수정내용 {답변:내용}으로 추가
		//3. 글 삭제 
		//0. 이전으로
		
	}
	
	/**
	 * 생성
	 * @param arrayList -> annList || inqList
	 */
	private void createInq(ArrayList<HashMap> arrayList) {
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Object> inq = new HashMap<>();
		String value;
		
		
		inq.put("id", LogIn.primaryKey);
		//inq.put("id", "primaryKey"); //test
		
		
		System.out.print("제목: ");
		value = scanner.nextLine();
		while ( (titleExists(arrayList,value)) ) {
		    System.out.print("중복입니다. 재입력해주세요.\n");
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
        //System.out.println(arrayList); //testcode
	}
	

	/**
	 * 	중복 제목 확인
	 * @param annList || inqList
	 * @param title
	 * @return 중복인지(true) 아닌지(false)
	 */
	private boolean titleExists(ArrayList<HashMap> arrayList, String title) {
	    for (HashMap<String, Object> map : arrayList) {
	        if (map.containsKey("title")) {
	            String existingTitle = (String) map.get("title");
	            if (existingTitle.equalsIgnoreCase(title)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	
	/**
	 * 수정
	 * @param arrayList -> annList || inqList
	 */
	private void editInq(ArrayList<HashMap> arrayList) {
	    Scanner scanner = new Scanner(System.in);
	    boolean found = false;
	    Inquiry inquiry = new Inquiry();
	    
	    
		inquiry.showTitleList(annList);	    
	    do {
	        System.out.printf("%22s수정할 글의 제목을 입력해주세요\n"," ");
	        System.out.printf("%22s제목: "," ");
	        
	        String input = scanner.nextLine();
	        
	        for (HashMap<String, Object> map : arrayList) {
	            if (map.containsKey("title")) {  //title이라는 키가 있으면
	                String title = (String) map.get("title"); //title의 value를 가져오고
	                if (title.equalsIgnoreCase(input)) { //문자열 비교해서 맞으면
	                    found = true;

	                    System.out.printf("%22s제목 \"%s\"를 찾았습니다.\n"," ", input);                    
	                    System.out.printf("첫 줄에서 ':q!' 시 종료\n");
	                    System.out.printf("수정: ");

	                    StringBuilder contentBuilder = new StringBuilder(); // 내용을 저장할 StringBuilder를 생성합니다.
	                    while (true) {
	                        input = scanner.nextLine();
	                        if (input.equals(":q!")) {
	                            break; // :q!를 입력하면 루프를 종료합니다.
	                        }
	                        contentBuilder.append(input).append("\n"); // 내용을 StringBuilder에 추가하고 개행 문자를 넣습니다.
	                    }

	                    String content = contentBuilder.toString().trim(); // StringBuilder의 내용을 문자열로 변환하고 양쪽 공백을 제거합니다.
	                    map.put("txt", content);

	                    //annList.add(map);
	                    System.out.printf("%22s수정되었습니다.\n"," ");                    

	                    return;
	                }
	            }
	        }
	         
	        if (!found) { //찾지 못했으면
	            System.out.printf("%22s제목 \"%s\"을 찾을 수 없습니다. 다시 입력해 주세요.\n", " ", input);
	        }
	        
	    } while (!found); //찾을때 까지
	    	    
	}
	
	/**
	 * 삭제
	 * @param arrayList -> annList || inqList
	 */
	private void deleteInq(ArrayList<HashMap> arrayList) {
		Scanner scanner = new Scanner(System.in);
		Inquiry inquiry = new Inquiry();
		
		System.out.printf("%22s1. 선택 삭제\n%22s2. 전체 삭제\n", " "," ");
		System.out.printf("%22s입력: ", " ");
		
		String input = "7";
		do {
			switch(input = scanner.nextLine()) {
				case "1":
					System.out.printf("%22s삭제할 글의 제목을 입력하세요.\n%22s입력: ", " "," ");
		            String del = scanner.nextLine();
		            boolean isDel = false;
		            for (Iterator<HashMap> iterator = arrayList.iterator(); iterator.hasNext();) {
		                HashMap<String, Object> map = iterator.next();
		                if (map.get("title").equals(del)) {
		                    iterator.remove(); // 현재 항목 삭제
		                    isDel = true;
		                    System.out.printf("%22s\"%s\" 제목의 글이 삭제되었습니다.\n", " ", del);
		                    break; // 일치하는 첫 번째 항목을 삭제한 후 반복문 종료
		                }
		            }
		            if (!isDel) {
		                System.out.printf("%22s\"%s\" 제목의 글을 찾을 수 없습니다.\n", " ", del);
		            }
					break;
					
				case "2":
					arrayList.removeAll(arrayList);
					System.out.printf("%22s전부 삭제되었습니다.", " ");
					break;

				case "0":

					//이전으로 감
					break;
					
				default :
					System.out.printf("%22s잘못된 번호입니다.\n%22s다시 입력해주세요\n"," "," ");
				}
		} while(!(input.equals("1") || input.equals("2") ||input.equals("0")) );
				
	}
	
	/**
	 * 제목 확인
	 * @param arrayList arrayList -> annList || inqList
	 */
	private void showTitleList(ArrayList<HashMap> arrayList) {
		TitleList.clear();
		int count=1;

	    for (HashMap<String, Object> map : arrayList) {
	        if (map.containsKey("title")) {
	            String title = (String) map.get("title");
	            TitleList.add(title);
	        }
	    }

	    System.out.printf("\n%22s-----------------------------------\n"," ");
	    System.out.printf("%22s\t\t     목록\n"," ");
	    for (String title : TitleList) {
		    System.out.printf("%22s%d. %s\n"," ",count++, title);
		    
		}
	    System.out.printf("\n%22s-----------------------------------\n\n"," ");
	    	
	}
	
	
	/**
	 * 내용 확인
	 * @param arrayList -> annList || inqList
	 */
	private void showTxt(ArrayList<HashMap> arrayList) {
		Scanner scanner = new Scanner(System.in); 
		Inquiry inquiry = new Inquiry();

		boolean found = false;
		
		String select;
		do {
			System.out.println();
			showTitleList(arrayList);
			System.out.printf("%22s1. 내용확인\n%22s0. 이전으로\n\n", " "," ");
			System.out.printf("%22s입력: ", " ");
			
			switch(select = scanner.nextLine()) {
				case "1":
					do {
						inquiry.showTitleList(arrayList);
						
				        System.out.printf("%22s확인할 글의 제목을 입력해주세요\n"," ");
				        System.out.printf("%22s제목: "," ");
				        
				        String input = scanner.nextLine();
				        
				        for (HashMap<String, Object> map : arrayList) {
				            if (map.containsKey("title")) {  //title이라는 키가 있으면
				            	String title = (String) map.get("title"); //title의 value를 가져오고
				                if (title.equalsIgnoreCase(input)) { //문자열 비교해서 맞으면
				                    found = true;
				                    System.out.println("\n---------------------------------------------------------------");
				                    System.out.println(map.get("txt")); //txt출력
				                    System.out.println("\n---------------------------------------------------------------");	                 
				                }
				            }
				        }
				         
				        if (!found) { //찾지 못했으면
				            System.out.printf("%22s제목 \"%s\"을 찾을 수 없습니다. 다시 입력해 주세요.\n", " ", input);
				        }
				        
				    } while (!found); //찾을때 까지
					
					break;
				case "0":
					//이전으로 감
					break;
					
				default :
					System.out.printf("%22s잘못된 번호입니다.\n%22s다시 입력해주세요\n"," "," ");
			}	
		} while(!(select.equals("1") || select.equals("0")) );    
		
	}
	
	
	/**
	 * 저장
	 * @param list -> annList || inqList
	 * @param path -> esourcePath.ANNO || ResourcePath.INQUIRY
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
			System.out.printf("\n%22s저장완료\n\n", " "); // testcode

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