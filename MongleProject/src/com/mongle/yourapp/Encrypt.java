package com.mongle.yourapp;

import java.security.MessageDigest;
import java.security.SecureRandom;


public class Encrypt {
	
	public static String AbcSalt;
	
	/**
	 * password 암호화
	 * @param pw
	 * @return 암호화된 문자열
	 */
	public static String encrypt(String pw) {
		Encrypt encrypt = new Encrypt();

		//getSalt
		String salt = Encrypt.getAbcJuice();
		//System.out.println("salt : "+salt); // salt value
		AbcSalt = salt;
		
		return Encrypt.getEncrypt(pw,salt);
		
	}


	private static String getEncrypt(String pw, String salt) {
		String output = "";
		
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//System.out.println("before pw+salt "+pw+salt); // test
			md.update((pw+salt).getBytes());
			byte[] pwSalt = md.digest();
			
			//byte to string
			StringBuffer sb = new StringBuffer();
			for(byte temp : pwSalt) {
				sb.append(String.format("%02x", temp));
			}
			output = sb.toString();
			
			//System.out.println("pwSalt "+output); // test
			
		} catch (Exception e) {
			System.out.println("Encrypt.getEncrypt");
			e.printStackTrace();
		}
		
		return output;
	}

	public static String getAbcJuice() { //getSalt
		SecureRandom secureRandom = new SecureRandom();  
		byte[] salt = new byte[20];

		// create rnd
		secureRandom.nextBytes(salt);
		
		//3. byte to String
		StringBuffer sb = new StringBuffer();
		for(byte temp : salt) {
			sb.append(String.format("%02x", temp));
		};
		
		return sb.toString();
	}
	
	/**
	 * 로그인 시 비밀번호 대조
	 * @param 입력받은 pw 값
	 * @param 사용자 데이터에 저장된 salt 값
	 * @return 암호화된 문자열
	 */
	public static String LogInPw(String pw , String salt) {
		return getEncrypt(pw,salt);	
	}
}
