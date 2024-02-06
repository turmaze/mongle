package com.mongle.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private static ArrayList<HashMap> perInqList = new ArrayList<HashMap>();

	public static ArrayList<HashMap> getPerInqList() {
		return perInqList;
	}

	public static ArrayList<HashMap> getAnnList() {
		return annList;
	}

	public static ArrayList<HashMap> getInqList() {
		return inqList;
	}

	private static ArrayList<String> titleList = new ArrayList<String>();
	private static String sTitle;

	/**
	 * 관리자 공지사항 실행
	 */
	public static void adminAnnouncement() {
		try {
			Scanner scanner = new Scanner(System.in);

			String input = "7"; // 1,2,3,0이 아닌 값
			while (!input.equals("0")) { //
				// level 탐색
				HashMap<String, Object> selectedMap = DataBase.getPrivateUser().get(0); // 고정 값
				Object levelValue = selectedMap.get("level");
				Inquiry inquiry = new Inquiry();
				inquiry.loadInq(annList, ResourcePath.ANNO);

				// 관리자
				if (levelValue.equals("2")) {

					do {
						System.out.println();
						MongleVisual.menuHeader("관리자 공지사항");

						System.out.printf("\n%22s1. 공지사항 확인\n", " ");
						System.out.printf("%22s2. 생성\n", " ");
						System.out.printf("%22s3. 수정\n", " ");
						System.out.printf("%22s4. 삭제\n\n", " ");
						System.out.printf("%22s0. 이전으로\n\n", " ");

						MongleVisual.choiceGuidePrint();
						switch (input = scanner.nextLine()) {
						case "1":
							inquiry.showTxt(annList);
							MongleVisual.pusher();

							break;
						case "2":
							inquiry.createInq(annList);
							inquiry.saveInq(annList, ResourcePath.ANNO);
							break;
						case "3":
							inquiry.editInq(annList);
							inquiry.saveInq(annList, ResourcePath.ANNO);
							break;
						case "4":
							inquiry.deleteInq(annList);
							inquiry.saveInq(annList, ResourcePath.ANNO);
							break;
						case "0":
							MongleVisual.menuMove("관리자 페이지");
							// MainMenu.mainMenu("2");
							break;

						default:
							MongleVisual.wrongInput();
						}
					} while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")
							|| input.equals("0")));

				} // if

			} // while(true)

		} catch (Exception e) {
			System.out.println("announcement Error");
			e.printStackTrace();
		}

	} // adminAnnouncement() 끝

	/**
	 * 회원 공지사항 실행
	 * 
	 * @return 메뉴 이동의 위한 변수
	 */
	public static int mebmerAnnouncement() {
		try {
			Scanner scanner = new Scanner(System.in);

			String input = "7"; // 1,2,3,0이 아닌 값
			while (!input.equals("0")) { //
				// level 탐색
				HashMap<String, Object> selectedMap = DataBase.getPrivateUser().get(0); // 고정 값
				Object levelValue = selectedMap.get("level");
				Inquiry inquiry = new Inquiry();
				inquiry.loadInq(annList, ResourcePath.ANNO);

				// 회원
				if (levelValue.equals("1")) {

					System.out.println();
					MongleVisual.menuHeader("공지사항");
					inquiry.showTxt(annList);
					System.out.printf("%22s9. 홈으로\n", " ");
					System.out.printf("%22s0. 이전으로\n\n", " ");

					do {
						switch (input = scanner.nextLine()) {
						case "9":
							MongleVisual.menuMove("홈 화면");
							return 9;
						case "0":
							MongleVisual.menuMove("이전 화면");
							return 0;
						default:
							MongleVisual.wrongInput();
						}
					} while (!(input.equals("9") || input.equals("0")));

				} // if

			} // while(true)

		} catch (Exception e) {
			System.out.println("announcement Error");
			e.printStackTrace();
		}
		return 0;

	} // memberAnnouncement() 끝

	/**
	 * 문의 접수
	 * 
	 * @return 메뉴 이동의 위한 변수
	 */
	public static int inquiry() {
		Inquiry inq = new Inquiry();
		inq.loadInq(inqList, ResourcePath.INQUIRY);
		MongleVisual.menuHeader("문의하기");
		inq.createInq(inqList);
		inq.saveInq(inqList, ResourcePath.INQUIRY);

		System.out.printf("%22s문의가 접수되었습니다. 감사합니다.\n", " ");

		MongleVisual.menuMove("이전 화면");
		return 0;
	}

	/**
	 * 관리자 문의사항 페이지
	 */
	public static void adminInquiry() {
		Inquiry inquiry = new Inquiry();
		Scanner scanner = new Scanner(System.in);

		inquiry.loadInq(inqList, ResourcePath.INQUIRY);
		MongleVisual.menuHeader("문의 처리");

		if (inqList.isEmpty()) {
			System.out.printf("\n%22s문의가 없습니다\n\n", " ");
			MongleVisual.stopper();
			MongleVisual.pusher();

			return;
		}

		String input = "7";
		do {
			inquiry.showTitleList(inqList);
			System.out.printf("%22s총 %d개의 문의내역이 존재합니다.\n", " ", inqList.size());

			System.out.println();
			System.out.printf("%22s1. 답변하기\n", " ");
			System.out.printf("%22s2. 문의 삭제\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			switch (input = scanner.nextLine()) {
			case "1":
				MongleVisual.pusher();
				inquiry.showTxt(inqList);
				for (HashMap<String, Object> map : inqList) {
					if (map.containsKey("title")) { // title이라는 키가 있으면
						String inputTitle = (String) map.get("title"); // title의 value를 가져오고
						if (inputTitle.equalsIgnoreCase(sTitle)) { // 문자열 비교해서 맞으면

							System.out.printf("첫 줄에서 ':q!' 시 종료\n");
							System.out.printf("답변: ");

							StringBuilder contentBuilder = new StringBuilder(); // 내용을 저장할 StringBuilder를 생성합니다.
							while (true) {
								input = scanner.nextLine();
								if (input.equals(":q!")) {
									break; // :q!를 입력하면 루프를 종료합니다.
								}
								contentBuilder.append(input).append("\n"); // 내용을 StringBuilder에 추가하고 개행 문자를 넣습니다.
							}

							String content = contentBuilder.toString().trim(); // StringBuilder의 내용을 문자열로 변환하고 양쪽 공백을
																				// 제거합니다.
							map.put("comment", content);

							inquiry.saveInq(inqList, ResourcePath.INQUIRY);
							System.out.printf("%22s완료되었습니다.\n", " ");
							adminInquiry();

						}
					}
				} // for

			case "2":
				MongleVisual.pusher();
				inquiry.deleteInq(inqList);
				inquiry.saveInq(inqList, ResourcePath.INQUIRY);
				MongleVisual.menuMove("문의 처리 페이지");
				adminInquiry();
				break;
			case "0":
				MongleVisual.menuMove("관리자 페이지");
				MainMenu.mainMenu("2");
				break;

			default:
				MongleVisual.wrongInput();
			}
		} while (!(input.equals("1") || input.equals("2") || input.equals("0")));

	}

	/**
	 * 문의 내역 확인
	 * 
	 * @return 메뉴 이동의 위한 변수
	 */
	public static int checkInq() {
		Inquiry inq = new Inquiry();
		inq.loadInq(inqList, ResourcePath.INQUIRY);
		MongleVisual.menuHeader("문의내역");
		ArrayList<HashMap> perInqList = new ArrayList<HashMap>();
		for (HashMap<String, Object> map : inqList) {
			if (map.get("id").equals(DataBase.getPrivateUser().get(0).get("id"))) {
				perInqList.add(map);
			}
		}

		System.out.printf("%22s총 %d개의 문의내역이 존재합니다.\n", " ", perInqList.size());

		inq.showTxt(perInqList);

		return 0;
	}

	/**
	 * 금지어 마스킹
	 * 
	 * @param txt 사용자가 입력한 내용
	 * @return 금지어가 마스킹된 내용
	 */
	private static String masking(String txt) {
		String[] forbidden = { "바보", "시발", "개새", "새끼", "멍청이", "멍청아", "병신", "등신", "머저리" };

		for (String word : forbidden) {
			if (txt.contains(word)) {
				String clean = "";
				clean = txt.replaceAll(word, "**");
				return clean;
			}
		}
		return txt;
	}

	/**
	 * 생성
	 * 
	 * @param arrayList -> annList || inqList
	 * @return 메뉴 이동의 위한 변수
	 */
	private int createInq(ArrayList<HashMap> arrayList) {
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Object> inq = new HashMap<>();
		String value;

		inq.put("id", LogIn.primaryKey);

		do {
			System.out.println();
			System.out.printf("제목: ");
			value = scanner.nextLine();
		} while ((titleExists(arrayList, value))); // 중복제목, 공백 검사

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
		content = masking(content);
		inq.put("txt", content);

		arrayList.add(inq);
		// System.out.println(arrayList); //testcode
		return 0;
	}

	/**
	 * 중복 제목 확인
	 * 
	 * @param annList || inqList
	 * @param title
	 * @return 중복인지(true) 아닌지(false)
	 */
	private boolean titleExists(ArrayList<HashMap> arrayList, String title) {
		String[] forbidden = { "바보", "시발", "개새", "새끼", "멍청이", "멍청아", "병신", "등신", "머저리" };
		for (String word : forbidden) {
			if (title.contains(word)) {
				System.out.printf("금지어가 포함되어있습니다. 재입력해주세요.\n", " ");
				return true;
			}
		}

		for (HashMap<String, Object> map : arrayList) {
			if (map.containsKey("title")) {
				String existingTitle = (String) map.get("title");
				if (existingTitle.equalsIgnoreCase(title)) {
					System.out.println("중복입니다. 재입력해주세요.\n");
					return true;
				}
			}
		}
		if (title.trim().equals("") || title.trim().equals("\r\n") || title.trim().equals("\n")) {
			System.out.println("공백은 제목으로 사용할 수 없습니다.\n");
			return true;
		}

		return false;
	}

	/**
	 * 수정
	 * 
	 * @param arrayList -> annList || inqList
	 */
	private void editInq(ArrayList<HashMap> arrayList) {
		Scanner scanner = new Scanner(System.in);
		boolean found = false;
		Inquiry inquiry = new Inquiry();

		inquiry.showTitleList(annList);
		if (arrayList.isEmpty())
			return;
		do {
			System.out.printf("%22s수정할 글의 제목을 입력해주세요\n", " ");
			System.out.printf("%22s제목: ", " ");

			String input = scanner.nextLine();
			sTitle = input;

			for (HashMap<String, Object> map : arrayList) {
				if (map.containsKey("title")) { // title이라는 키가 있으면
					String title = (String) map.get("title"); // title의 value를 가져오고
					if (title.equalsIgnoreCase(input)) { // 문자열 비교해서 맞으면
						found = true;

						System.out.printf("%22s제목 \"%s\"를 찾았습니다.\n", " ", input);
						System.out.printf("마지막 줄에서 ':q!'입력 후 enter 시 종료\n");
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

						// arrayList.add(map);
						System.out.printf("%22s수정되었습니다.\n", " ");

						return;
					}
				}
			}

			if (!found) { // 찾지 못했으면
				System.out.printf("%22s제목 \"%s\"을 찾을 수 없습니다. 다시 입력해 주세요.\n", " ", input);
			}

		} while (!found); // 찾을때 까지

	}

	/**
	 * 삭제
	 * 
	 * @param arrayList -> annList || inqList
	 */
	private void deleteInq(ArrayList<HashMap> arrayList) {
		Scanner scanner = new Scanner(System.in);
		Inquiry inquiry = new Inquiry();

		System.out.printf("%22s1. 선택 삭제\n%22s2. 전체 삭제\n", " ", " ");
		System.out.printf("%22s0. 이전으로\n", " ");

		MongleVisual.choiceGuidePrint();

		String input = "7";
		do {
			switch (input = scanner.nextLine()) {
			case "1":
				inquiry.showTitleList(arrayList);
				System.out.printf("%22s삭제할 글의 제목을 입력하세요.\n%22s입력: ", " ", " ");
				String del = scanner.nextLine();
				boolean isDel = false;
				for (Iterator<HashMap> iterator = arrayList.iterator(); iterator.hasNext();) {
					HashMap<String, Object> map = iterator.next();
					if (map.get("title").equals(del)) {
						iterator.remove(); // 현재 항목 삭제
						isDel = true;
						System.out.printf("%22s\"%s\" 제목의 글이 삭제되었습니다.\n", " ", del);
						// break; // 일치하는 첫 번째 항목을 삭제한 후 반복문 종료
						return;
					}
				}
				if (!isDel) {
					System.out.printf("%22s\"%s\" 제목의 글을 찾을 수 없습니다.\n", " ", del);
				}
				return;

			case "2":
				arrayList.removeAll(arrayList);
				System.out.printf("%22s전부 삭제되었습니다.", " ");
				return;

			case "0":

				// 이전으로 감
				return;

			default:
				System.out.printf("%22s잘못된 번호입니다.\n%22s다시 입력해주세요\n", " ", " ");
			}
		} while (!(input.equals("1") || input.equals("2") || input.equals("0")));

	}

	/**
	 * 제목 확인
	 * 
	 * @param arrayList arrayList -> annList || inqList
	 */
	private void showTitleList(ArrayList<HashMap> arrayList) {

		titleList.clear();
		int count = 1;

		for (HashMap<String, Object> map : arrayList) {
			if (map.containsKey("title")) {
				String title = (String) map.get("title");
				titleList.add(title);
			}
		}

		System.out.printf("\n%22s-----------------------------------\n", " ");
		System.out.printf("%22s\t\t     목록\n", " ");
		for (String title : titleList) {
			System.out.printf("%22s%d. %s\n", " ", count++, title);

		}
		System.out.printf("\n%22s-----------------------------------\n\n", " ");

		if (arrayList.isEmpty()) {
			System.out.printf("%22s내용이 없습니다\n\n", " ");
			MongleVisual.stopper();
		}

	}

	/**
	 * 내용 확인
	 * 
	 * @param arrayList -> annList || inqList
	 * @return 메뉴 이동의 위한 변수
	 */
	private int showTxt(ArrayList<HashMap> arrayList) {
		Scanner scanner = new Scanner(System.in);
		Inquiry inquiry = new Inquiry();

		boolean found = false;

		String select;
		do {
			System.out.println();
			showTitleList(arrayList);
			if (arrayList.isEmpty())
				return 0;

			System.out.printf("%22s1. 내용확인\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			System.out.println();
			MongleVisual.choiceGuidePrint();

			switch (select = scanner.nextLine()) {
			case "1":
				do {
					inquiry.showTitleList(arrayList);

					System.out.printf("%22s확인할 글의 제목을 입력해주세요\n", " ");
					System.out.printf("%22s제목: ", " ");

					String input = scanner.nextLine();
					sTitle = input;

					for (HashMap<String, Object> map : arrayList) {
						if (map.containsKey("title")) { // title이라는 키가 있으면
							String title = (String) map.get("title"); // title의 value를 가져오고
							if (title.equalsIgnoreCase(input)) { // 문자열 비교해서 맞으면
								found = true;
								System.out.println("\n---------------------------------------------------------------");
								System.out.println("제목: " + map.get("title") + "\n"); // 제목
								System.out.println(map.get("txt")); // txt출력

								if (map.containsKey("comment")) {
									System.out.println(
											"\n---------------------------------------------------------------");
									System.out.println("답변: " + "\n"); // 답변
									System.out.println(map.get("comment"));
								}

								System.out.println("\n---------------------------------------------------------------");
								MongleVisual.stopper();
							}
						}
					}

					if (!found) { // 찾지 못했으면
						System.out.printf("\n%22s제목 \"%s\"을 찾을 수 없습니다. 다시 입력해 주세요.\n\n", " ", input);
					}

				} while (!found); // 찾을때 까지
				break;
			case "0":
				return 0;

			default:
				MongleVisual.wrongInput();
			}
		} while (!(select.equals("1") || select.equals("0")));
		return 0;
	}

	/**
	 * 저장
	 * 
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
			//System.out.printf("\n%22s저장완료\n\n", " "); // testcode

		} catch (Exception e) {
			System.out.println("DataBase.dataSave Error");
			e.printStackTrace();
		}

	}

	/**
	 * 불러오기
	 * 
	 * @param list -> annList || inqList
	 * @param path -> esourcePath.ANNO || ResourcePath.INQUIRY
	 */
	public void loadInq(ArrayList list, String path) {
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

}