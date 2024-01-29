package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Deposit {
	public static void main(String[] args) throws IOException {
		String response = getApi().toString();
		try {
			JSONParser parser = new JSONParser();
			
			JSONObject root = (JSONObject)parser.parse(response);
			
			System.out.println(root.get("total"));
			
//			JSONArray list = (JSONArray)root.get(root);
		} catch (Exception e) {
			System.out.println("Deposit,main");
			e.getStackTrace();
		}
		
		
		
	}

	private static StringBuilder getApi()
			throws UnsupportedEncodingException, MalformedURLException, IOException, ProtocolException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B190017/service/GetInsuredProductService202008/getProductList202008"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("lv9bpyNMeZHwgq4vZdHVxoieUgW3b1RwSLU5oQH1MJA6BCz4Y86MVwXLcW3ij7OL%2Be9wXLIx1CEuOaAKLyzxwA%3D%3D", "UTF-8") + "=lv9bpyNMeZHwgq4vZdHVxoieUgW3b1RwSLU5oQH1MJA6BCz4Y86MVwXLcW3ij7OL%2Be9wXLIx1CEuOaAKLyzxwA%3D%3D"); /* Service Key */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* 결과형식(xml/json) */
		urlBuilder.append(
				"&" + URLEncoder.encode("regnNm", "UTF-8") + "=" + URLEncoder.encode("은행", "UTF-8")); /* 금융권역 */
		urlBuilder.append("&" + URLEncoder.encode("fncIstNm", "UTF-8") + "="
				+ URLEncoder.encode("은행", "UTF-8")); /* 금융회사명 */
		urlBuilder.append(
				"&" + URLEncoder.encode("prdNm", "UTF-8") + "=" + URLEncoder.encode("예금", "UTF-8")); /* 금융상품명 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		
		return sb;
	}
}
