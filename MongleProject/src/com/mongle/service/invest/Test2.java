package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test2 {
	public static void main(String[] args) {
		/*
		 * (Data) OpenAPI - URL > 요청 - JSON or XML > 응답
		 * 
		 * Data + OpenAPI
		 */
		
		String clientId = "sX7SFoMBLyHb0z9pvXdp";
		String clientSecret = "j4RWVPu1iA";

		String text = null;
		try {
			text = URLEncoder.encode("", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		//주소?키=값&키=값&키=값
		String apiURL = "https://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=efebe52a92c17a5bcee4c231f829a349&topFinGrpNo=020000&pageNo=1"; // JSON 결과
		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
		// // XML 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

		// System.out.println(responseBody);
		try {
			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(responseBody);

			//System.out.println(root.get("result"));
			JSONObject result = (JSONObject)root.get("result");
			System.out.println(result.get("err_msg"));
			
//			//도서 목록
//			JSONArray list = (JSONArray)root.get("items");
//			
//			for (Object book : list) {
//				String title = (String)((JSONObject)book).get("title");
//				System.out.println(title);
//				
//				System.out.println(((JSONObject)book).get("link"));
//			}

		} catch (Exception e) {
			System.out.println("emain");
			e.getStackTrace();
		}

	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 오류 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}
	}

}
