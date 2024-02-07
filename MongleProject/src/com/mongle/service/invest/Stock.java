package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;
import com.mongle.resource.UserData;
import com.mongle.service.InvestService;
import com.mongle.view.MongleVisual;

/**
 * 주식 클래스
 */
public class Stock {
	/**
	 * 주식 상품 보유 내역
	 */

	public static String TOKEN; // api 접근 토큰
	public static String TOKENexpired; // api 접근 토큰 만료일
	public static int buyPrice = 0; // 주식 매수가
	public static int buyAmount = 0; // 주식 매수 수량
	public static String sel = ""; // 매수한 주식 명
	public static ArrayList<Investment> listStock = new ArrayList<>();

	/**
	 * 매입한 주식 가격 Getter
	 * 
	 * @return 매입한 주식 가격
	 */
	public static int getBuyPrice() {
		return buyPrice;
	}

	/**
	 * 매입한 주식 가격 Setter
	 * 
	 * @return buyPrice 매입한 주식 가격
	 */
	public static void setBuyPrice(int buyPrice) {
		Stock.buyPrice = buyPrice;
	}

	/**
	 * 매입한 주식 수량 Getter
	 * 
	 * @return 매입한 주식 수량
	 */
	public static int getBuyAmount() {
		return buyAmount;
	}

	/**
	 * 매입한 주식 수량 Setter
	 * 
	 * @return buyArice 매입한 주식 수량
	 */
	public static void setBuyAmount(int buyAmount) {
		Stock.buyAmount = buyAmount;
	}

	/**
	 * 매입한 주식 이름 Getter
	 * 
	 * @return 매입한 주식 이름
	 */
	public static String getSel() {
		return sel;
	}

	/**
	 * 매입한 주식 이름 Setter
	 * 
	 * @return buyArice 매입한 주식 이름
	 */
	public static void setSel(String sel) {
		Stock.sel = sel;
	}

	/**
	 * 주식 시세 검색 및 주문
	 * 
	 * @return 메뉴 이동을 위한 변수
	 */
	public static int stockService() {
		try {

			Scanner scan = new Scanner(System.in);
			Boolean loop = true;

			while (loop) {

				MongleVisual.pusher();

				MongleVisual.menuHeader("주식");
				String stockURL = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=S3mJZVa%2B2sPWWlGQUV7WgSNe4Fd3yYWAts4pwm9aPIOJVQY4NZqBVvp4bQT%2Fm6iH023rAE5yPGI7gi%2FAZKlxng%3D%3D&numOfRows=1&resultType=json";

				System.out.printf("%22s1. 종목명으로 검색\n", " ");
				System.out.printf("%22s2. 코드명으로 검색\n", " ");
				System.out.printf("%22s9. 홈으로\n", " ");
				System.out.printf("%22s0. 이전으로\n", " ");
				MongleVisual.choiceGuidePrint();
				;

				String sel = scan.nextLine();

				System.out.println();

				if (sel.equals("1")) {
					System.out.printf("%22s종목명: ", " ");
					sel = scan.nextLine();
					if (sel.equals("")) {
						MongleVisual.wrongInput();
						continue;
					}
					sel = "&itmsNm=" + URLEncoder.encode(sel, "UTF-8");

				} else if (sel.equals("2")) {
					System.out.printf("%22s코드명: ", " ");
					sel = scan.nextLine();
					if (sel.equals("")) {
						MongleVisual.wrongInput();
						continue;
					}
					sel = "&likeSrtnCd=" + URLEncoder.encode(sel, "UTF-8");
				} else if (sel.equals("9")) {
					MongleVisual.menuMove("홈 화면");
					return 9;
				} else if (sel.equals("0")) {
					MongleVisual.menuMove("이전 화면");
					return 0;
				} else {
					MongleVisual.wrongInput();
					continue;
				}
				System.out.println();

				stockURL += sel;

				URL url = new URL(stockURL);
				// JSON 결과
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				String result = bf.readLine();

				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(result);
				JSONObject response = (JSONObject) jsonObject.get("response");
				JSONObject body = (JSONObject) response.get("body");
				JSONObject items = (JSONObject) body.get("items");
				Long totalCount = (Long) body.get("totalCount");
				JSONArray item = (JSONArray) items.get("item");
				// System.out.println(jsonObject);

				if (totalCount == 0) {
					System.out.printf("%22s검색 결과가 없습니다.\n", " ");
					MongleVisual.stopper();
					continue;
				}

				result = stockPrice((String) ((JSONObject) item.get(0)).get("srtnCd"));
				response = (JSONObject) parser.parse(result);
				body = (JSONObject) response.get("output");
				int nowPrice = Integer.parseInt((String) body.get("stck_prpr"));
				double priceRate = Double.parseDouble((String) body.get("prdy_ctrt"));
				int startPrice = Integer.parseInt((String) body.get("stck_oprc"));
				int highPrice = Integer.parseInt((String) body.get("stck_hgpr"));
				int lowPrice = Integer.parseInt((String) body.get("stck_lwpr"));
				String name = (String) ((JSONObject) item.get(0)).get("itmsNm");
				Stock.setSel(name);
				MongleVisual.pusher();

				String midHeader = "+---------------+---------------+---------------+---------------+---------------+";
				String header = "+-------------------------------------------------------------------------------+";
				System.out.println(header);
				System.out.printf("|%37s종목명\t\t\t\t\t|\n", " ");
				System.out.printf("|%36s%s\t\t\t\t\t|\n", " ", name);
				System.out.println(midHeader);
				System.out.printf("|%5s현재가\t|%4s전일비\t|%5s시가\t|%5s고가\t|%5s저가\t|\n", "", " ", " ", " ", " ");
				System.out.printf("|%5s%,d\t|%5s%,.2f\t|%5s%,d\t|%5s%,d\t|%5s%,d\t|\n", " ", nowPrice, " ", priceRate,
						" ", startPrice, " ", highPrice, " ", lowPrice);
				System.out.println(midHeader);

				System.out.println();
				System.out.println();

				System.out.printf("%22s1. 구매\n", " ");
				System.out.printf("%22s2. 다시 검색하기\n", " ");
				System.out.printf("%22s9. 홈으로\n", " ");
				System.out.printf("%22s0. 이전으로\n", " ");
				MongleVisual.choiceGuidePrint();
				String sel2 = scan.nextLine();
				if (sel2.equals("1")) {

					MongleVisual.pusher();

					String amount = "";

					System.out.println(header);
					System.out.printf("|%36s%s\t\t\t\t\t|\n", " ", name);
					System.out.printf("|%36s%s\t\t\t\t|\n", " ", "현재가: " + String.format("%,d", nowPrice));
					System.out.println(header);

					while (true) {
						System.out.printf("%22s수량(숫자): ", " ");
						amount = scan.nextLine();
						String regex = "^[0-9]+$";
						Pattern p1 = Pattern.compile(regex);
						Matcher m1 = p1.matcher(amount);
						if (!m1.find()) {
							System.out.printf("%22s정확한 숫자를 입력해 주시기 바랍니다.\n", " ");
						} else {
							break;
						}
					}
					System.out.printf("%22s총 구매 대금: %,d원\n", " ", nowPrice * Integer.parseInt(amount));
					System.out.printf("%22s구매하시겠습니까? (y/n)\n", " ");
					System.out.printf("%22s선택: ", " ");
					sel = scan.nextLine();
					if (sel.equals("y")) {
						int a = InvestService.transaction(name, nowPrice, Integer.parseInt(amount));
						if (a != 1) {
							buyPrice = nowPrice;
							buyAmount = Integer.parseInt(amount);
							Investment.list.add(new Investment("주식", Stock.getSel(), "주식", Stock.getBuyPrice(),
									Stock.getBuyAmount()));
						}
						// listStock.add(new Investment(Stock.getSel(),"대출", Stock.getBuyPrice(),
						// Stock.getBuyAmount()));
//						Investment.list.add(new Investment("주식", Stock.getSel(),"주식", Stock.getBuyPrice(), Stock.getBuyAmount()));

					} else if (sel.equals("n")) {
						System.out.printf("%22s거래가 취소되었습니다.\n", " ");
						MongleVisual.stopper();
					} else {
						System.out.printf("%22s입력이 올바르지 않습니다.\n", " ");
						MongleVisual.stopper();
					}

				} else if (sel2.equals("2")) {
					MongleVisual.stopper();
				} else if (sel2.equals("9")) {
					MongleVisual.menuMove("홈 화면");
					return 9;
				} else if (sel2.equals("0")) {
					MongleVisual.menuMove("이전 화면");
					return 0;
				}

				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Stock.main");
			e.printStackTrace();
		}
		return 0;

	}// stock

	/**
	 * 주식 시세 정보 호출
	 * 
	 * @param 시세를 검색할 주식 종목코드
	 * @return 검색한 주식의 현재시세 관련 정보
	 */
	public static String stockPrice(String cd) {

		// 국내 주식 시세 조회
		String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price";
		String tr_id = "FHKST01010100";
		String data = "?fid_cond_mrkt_div_code=J" + // FID 조건 시장 분류 코드
				"&fid_input_iscd=" + cd; // FID 입력 종목코드

		getToken();
		if (invalidToken()) {
			serverToken();
			setToken();
		}

		String result = "";
		try {
			result = httpGetConnection(url, data, tr_id);
		} catch (Exception e) {
			System.out.println("Stock.stockPrice");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 주식 시세 api 호출
	 * 
	 * @param UrlData   api 주소
	 * @param ParamData 메뉴코드
	 * @param TrId      종목코드
	 * @return 검색한 주식의 현재시세 관련 정보
	 */
	@SuppressWarnings("finally")
	public static String httpGetConnection(String UrlData, String ParamData, String TrId) throws IOException {
		String totalUrl = "";
		totalUrl = UrlData.trim().toString();

		URL url = null;
		HttpURLConnection conn = null;

		String responseData = "";
		BufferedReader br = null;

		StringBuffer sb = new StringBuffer();
		String returnData = "";

		try {
			url = new URL(totalUrl + ParamData);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("authorization", "Bearer " + TOKEN);
			conn.setRequestProperty("appKey", "PSseRvmqtHg5kx9cJfs7JFzpbPBQbXxT4Wn8");
			conn.setRequestProperty("appSecret",
					"ozZWypajrAAc6Qg9XBV8Vi8qyof2EFf//16gsk3nc7XtMOjOrbtmb+v7qKBvYwJJJ/ni4TXLK2Dp0seaE4zIgak+EVrWl+02xHcAiOwRUD9q+bhOkGsBrb4ZmEKuTxrwqog8sBK19oo7ktQ9naCW0XtjNrB0g52ZhbAuIBfwFroN5szX2SY=");
			conn.setRequestProperty("tr_id", TrId);
			conn.setDoOutput(true);

			conn.connect();

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		} finally {
			try {
				String result = "";
				while ((responseData = br.readLine()) != null) {
					result += responseData;
				}
				if (br != null) {
					br.close();
				}
				return result;
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
			}
		}
	}

	/**
	 * 주식 시세 api 접근 토큰 발급
	 */
	public static void serverToken() {

		try {
			String APP_KEY = "PSseRvmqtHg5kx9cJfs7JFzpbPBQbXxT4Wn8";
			String APP_SECRET = "ozZWypajrAAc6Qg9XBV8Vi8qyof2EFf//16gsk3nc7XtMOjOrbtmb+v7qKBvYwJJJ/ni4TXLK2Dp0seaE4zIgak+EVrWl+02xHcAiOwRUD9q+bhOkGsBrb4ZmEKuTxrwqog8sBK19oo7ktQ9naCW0XtjNrB0g52ZhbAuIBfwFroN5szX2SY=";
			String URL_BASE = "https://openapivts.koreainvestment.com:29443";
			String PATH = "oauth2/tokenP";
			String URL = URL_BASE + "/" + PATH;

			// Request body
			String requestBody = String.format(
					"{\"grant_type\":\"client_credentials\",\"appkey\":\"%s\",\"appsecret\":\"%s\"}", APP_KEY,
					APP_SECRET);

			// Set up the HTTP connection
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoOutput(true);

			// Write request body
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = requestBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			// Get response
			try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
				String response = br.readLine();
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(response);

				TOKEN = (String) jsonObject.get("access_token");
				TOKENexpired = (String) jsonObject.get("access_token_token_expired");
			}

			// Close the connection
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 토큰 유효성 검사
	 * 
	 * @return 토큰 만료 여부
	 */
	private static boolean invalidToken() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = date.format(formatter);
		if (TOKENexpired != null) {
			String[] day = TOKENexpired.split(" ");
			if (Integer.parseInt(day[0].replace("-", "")) > Integer.parseInt(today.replace("-", ""))
					|| TOKENexpired == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 토큰 정보 파일 쓰기
	 */
	public static void setToken() {

		try {

			String path = "src\\com\\mongle\\service\\invest\\token.dat";
			FileWriter writer = new FileWriter(path);
			writer.write(TOKEN);
			writer.write("\r\n");
			writer.write(TOKENexpired);
			writer.write("\r\n");

			writer.close();

		} catch (Exception e) {
			System.out.println("Stock.setToken");
			e.printStackTrace();
		}

	}

	/**
	 * 토큰 정보 파일 읽기
	 */
	public static void getToken() {

		try {

			String path = "src\\com\\mongle\\service\\invest\\token.dat";
			File file = new File(path);

			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));

				TOKEN = reader.readLine();
				TOKENexpired = reader.readLine();

				reader.close();
			}

		} catch (Exception e) {
			System.out.println("DataBase.dataLoad Error");
			e.printStackTrace();
		}
	}

}