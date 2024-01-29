package com.mongle.service.invest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DepositReal {
	public static void main(String[] args) {
		 String response = getApi();
		 
		 try {
				JSONParser parser = new JSONParser();
				
				JSONObject root = (JSONObject)parser.parse(response);
				
				JSONArray list = (JSONArray)root.get("products");
				
				for (Object product : list) {
					String bank = (String)((JSONObject)product).get("kor_co_nm"); 
					String title = (String)((JSONObject)product).get("fin_prdt_nm");
					String exp = (String)((JSONObject)product).get("mtrt_int");
					
					System.out.printf("[%s]%s-%s\n", bank, title, exp);
					
				}
			} catch (Exception e) {
				System.out.println("Deposit,main");
				e.getStackTrace();
			}
	}

	private static String getApi() {
		StringBuilder vStringBuilder = new StringBuilder();
		String vStringLine;
		try {

	            URL vURL = new URL("https://finlife.fss.or.kr/finlifeapi/depositProductsSearch.xml?auth=e06ef138c067a4ff1a42504d0fefda36&topFinGrpNo=020000&pageNo=1");
	            HttpURLConnection vHttpURLConnection = (HttpURLConnection) vURL.openConnection();

	            vHttpURLConnection.setConnectTimeout(5000);
	            vHttpURLConnection.setReadTimeout(5000);
	            vHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0");
	            vHttpURLConnection.setRequestMethod("GET");
	            vHttpURLConnection.setDoOutput(true);

	            if (vHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                BufferedReader vBufferedReader = new BufferedReader(
	                    new InputStreamReader(vHttpURLConnection.getInputStream(), "UTF-8")
	                );

	                while ((vStringLine = vBufferedReader.readLine()) != null) {
	                    vStringBuilder.append(vStringLine).append("\n");
	                }

	                vBufferedReader.close();

	                System.out.println("\n");
	                System.out.println("요청 성공:");
	                System.out.println(" " + vHttpURLConnection.getResponseCode());
	                System.out.println(" " + vHttpURLConnection.getResponseMessage());
	                System.out.println(" " + vStringBuilder.toString());

	            } else {
	                System.out.println("\n");
	                System.out.println("요청 실패:");
	                System.out.println(" " + vHttpURLConnection.getResponseCode());
	                System.out.println(" " + vHttpURLConnection.getResponseMessage());
	                System.out.println(" " + vStringBuilder.toString());
	            }

	            
	        } catch (Exception ex){
	            System.out.println("ex: " + ex.toString());
	        }
		
		return vStringBuilder.toString();
	}

}
