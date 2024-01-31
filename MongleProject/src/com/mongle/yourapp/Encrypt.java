package com.mongle.yourapp;

import java.security.MessageDigest;
import java.security.SecureRandom;


public class Encrypt {
	public static String encrypt(String pw) {
		Encrypt encrypt = new Encrypt();

		//getSalt
		String salt = encrypt.getSalt();
		//System.out.println("salt : "+salt); // salt value
		
		
		
		return encrypt.getEncrypt(pw,salt);
		
	}

	
	private static String getEncrypt(String pw, String salt) {
		String output = "";
		
		try {
			// SHA256 알고리즘
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

	private String getSalt() {
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

}
