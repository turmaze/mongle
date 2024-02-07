package com.mongle.yourapp;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.resource.ResourcePath;
import com.mongle.view.MongleVisual;

public class DataManage {
	public static void dataManage() {
		System.out.printf("\n%22s데이터 출력\n", " ");
		System.out.printf("\n%22s출력하시고 싶은 데이터를 선택해주세요\n", " ");
		System.out.printf("\n%22s1. 이용자수 (일,월,년)\n", " ");
		System.out.printf("\n%22s2. 총 연결된 계좌수\n", " ");
		System.out.printf("\n%22s3. 거래가 Mongle에서 일어난 횟수 (일,월,년)\n", " ");
		System.out.printf("\n%22s4. 은행별 등록된 계좌 계수\n", " ");
		System.out.printf("\n%22s5. 모든 데이터 통계 보기\n", " ");

		System.out.println();
		MongleVisual.choiceGuidePrint();
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();

		if (choice.equals("1")) {
			dataGet(userNum());
		} else if (choice.equals("2")) {
			accNum();
		} else if (choice.equals("3")) {
			transNum(userNum());
		} else if (choice.equals("4")) {
			bankNum();
		} else if(choice.equals("5")){
			advanceCalc();
		}else {
			return;
		}

	}

	private static void advanceCalc() {
		int choice = userNum();
		System.out.printf("\n%22s1. 기간별 이용자", " ");
		dataGet(choice);
		System.out.println();
		
		System.out.printf("\n%22s2. 총계좌수", " ");
		accNum();
		System.out.println();
		
		System.out.printf("\n%22s3. 기간별 이체수", " ");
		transNum(choice);
		System.out.println();
		
		System.out.printf("\n%22s4. 계좌은행 분포", " ");
		bankNum();
		System.out.println();
		
	}

	private static void transNum(int now) {
		String choice = String.valueOf(now);
		int count = 0;
		ArrayList<String> checkdate = new ArrayList<String>();
		Calendar c1 = Calendar.getInstance();
		String currentDate = String.format("%tF\n", c1);
		int numCount = 0;
		int cdate = 0;
//		
		String currentday = "";
		if (choice.equals("1")) {
			count = 1;
			currentday = currentDate.substring(0, 10);
		} else if (choice.equals("2")) {
			count = 2;
			currentday = currentDate.substring(0, 7);
		} else if (choice.equals("3")) {
			count = 3;
			currentday = currentDate.substring(0, 4);
		} else {
			MongleVisual.wrongInput();
		}

		int num = 0;
		ArrayList<String> acc = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			FileReader reader = new FileReader(ResourcePath.MEMBER);
			// JSON 데이터를 파싱하여 JSONArray로 변환
			JSONArray accin = (JSONArray) parser.parse(reader);

			for (Object obj : accin) {
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray array = (JSONArray) jsonObject.get("account");
				if (array != null) {
					for (Object key : array) {
						JSONObject accget = (JSONObject) key;
						JSONArray date = (JSONArray) accget.get("history");
						for (Object action : date) {
							JSONObject numnum = (JSONObject) action;
							if (((String) numnum.get("date")).contains(currentday)) {
								num++;
							}
						}
					}
				}
			}
			
			if (count == 1) {
				System.out.printf("\n%22s[일별이체 : %d]\r\n", " ", num);
			} else if (count == 2) {
				System.out.printf("\n%22s[월별이체 : %d]\r\n", " ", num);
			} else {
				System.out.printf("\n%22s[년별이체 : %d]\r\n", " ", num);
			}
			
		} catch (Exception e) {
			System.out.println("DataManage.accNum");
			e.printStackTrace();
		}

	}

	private static void bankNum() {
		int num = 0;
		ArrayList<String> bankname = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			FileReader reader = new FileReader(ResourcePath.MEMBER);
			// JSON 데이터를 파싱하여 JSONArray로 변환
			JSONArray accin = (JSONArray) parser.parse(reader);
			for (Object obj : accin) {
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray array = (JSONArray) jsonObject.get("account");
				if (array != null) {
					for (Object key : array) {
						JSONObject accget = (JSONObject) key;
						String addition = (String) accget.get("bankDepo");
						bankname.add(addition);
						
					}
				}
			}
			bankname.sort(null);
			
			HashSet<String>set = new HashSet<String>(bankname);
			
			List<String> uniqueExt = new ArrayList<String>(set);
			for(int i = 0 ; i<uniqueExt.size();i++) {
				int repeat = 0;
				for(int j = 0 ; j <bankname.size();j++) {
					if(uniqueExt.get(i).equals(bankname.get(j))) {
						repeat++;
					}
				}
				System.out.printf("\n%22s[%s: %d개]\r\n", " ", uniqueExt.get(i),repeat);
			}
		} catch (Exception e) {
			System.out.println("DataManage.accNum");
			e.printStackTrace();
		}

	}

	private static void accNum() {
		int num = 0;
		ArrayList<String> acc = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			FileReader reader = new FileReader(ResourcePath.MEMBER);
			// JSON 데이터를 파싱하여 JSONArray로 변환
			JSONArray accin = (JSONArray) parser.parse(reader);
			for (Object obj : accin) {
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray array = (JSONArray) jsonObject.get("account");
				if (array != null) {
					for (Object key : array) {
						JSONObject accget = (JSONObject) key;
						String addition = (String) accget.get("accountNumber");

						if (!addition.equals(null)) {
							num++;
						}
					}
				}
			}
			System.out.printf("\n%22s[총계좌수: %d]\r\n", " ", num);
		} catch (Exception e) {
			System.out.println("DataManage.accNum");
			e.printStackTrace();
		}

	}

	private static int userNum() {
		int count = 0;

		System.out.printf("\n%22s1. 단위 (일)\n", " ");
		System.out.printf("\n%22s2. 단위 (월)\n", " ");
		System.out.printf("\n%22s3. 단위 (년)\n", " ");
		System.out.println();
		MongleVisual.choiceGuidePrint();
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		// dataGet(count);
		if (choice.equals("1")) {
			return 1;
		} else if (choice.equals("2")) {
			return 2;
		} else if (choice.equals("3")) {
			return 3;
		}
		return 0;
	}

	public static int dataGet(int count) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> checkdate = new ArrayList<String>();
		Calendar c1 = Calendar.getInstance();
		String currentDate = String.format("%tF\n", c1);
		currentDate = currentDate.replace("-", "");
		int numCount = 0;
		int cdate = 0;
		if (count == 1) {
			cdate = Integer.parseInt(currentDate.substring(0, 8));
		} else if (count == 2) {
			cdate = Integer.parseInt(currentDate.substring(0, 6));
		} else if (count == 3) {
			cdate = Integer.parseInt(currentDate.substring(0, 4));
		} else {
			MongleVisual.wrongInput();
			return 0;
		}

		// System.out.println(currentDate);
		JSONParser parser = new JSONParser();
		try {
			// FileReader 객체 생성
			FileReader reader = new FileReader(ResourcePath.MEMBER);
			// JSON 데이터를 파싱하여 JSONArray로 변환
			JSONArray visit = (JSONArray) parser.parse(reader);

			for (Object obj : visit) {
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray array = (JSONArray) jsonObject.get("attend");

				if (array != null) {
					for (Object key : array) {
						JSONObject aten = (JSONObject) key;
						JSONArray n = (JSONArray) aten.get("attenddate");
						ArrayList<String> date = (ArrayList<String>) aten.get("attenddate");
						int attendence = 0;
						for (String check : date) {
							check = check.replace("-", "");
							if (count == 1) {
								attendence = Integer.parseInt(check.substring(0, 8));
							} else if (count == 2) {
								attendence = Integer.parseInt(currentDate.substring(0, 6));
							} else {
								attendence = Integer.parseInt(currentDate.substring(0, 4));
							}

						}
						if (cdate == attendence) {
							numCount++;
						}
					}
				}
			}
			if (count == 1) {
				System.out.printf("\n%22s[일별유저 : %d]\r\n", " ", numCount);
			} else if (count == 2) {
				System.out.printf("\n%22s[월별유저 : %d]\r\n", " ", numCount);
			} else {
				System.out.printf("\n%22s[년별유저 : %d]\r\n", " ", numCount);

			}

		} catch (Exception e) {
			System.out.println("DataManage.dataGet");
			e.printStackTrace();
		}
		return cdate;

	}
}
