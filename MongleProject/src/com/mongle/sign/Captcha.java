package com.mongle.sign;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import com.github.lalyos.jfiglet.FigletFont;

public class Captcha {
	
	/**
	 * capcha 실행 메서드
	 * @return 캡챠 성공(true) 실패(false)
	 */
    public static boolean captcha() {
        Scanner scanner = new Scanner(System.in);
        String captcha;
        String input;
        int count = 0; 

        while (count < 3) {
            captcha = generateCaptcha();
            System.out.printf(asciiArt(captcha));
            //System.out.printf("%22s%s\n"," ",captcha); testcode
            System.out.printf("%22s이미지의 문자를 입력하세요.\n"," ");
            System.out.printf("%22s입력: "," ");
            input = scanner.nextLine();
            
            if (captcha.equals(input)) {
                System.out.printf("%22s성공\n\n"," ");
                return true;
            } else {
                count++;
                if (count < 3) {
                    System.out.printf("%22s실패\n%22s다시 입력해주세요. (남은 시도 횟수: %d)\n"," ", " ", (3 - count));
                } else {
                    System.out.printf("%22s실패\n%22s입력 횟수를 초과했습니다.\n%22s프로그램을 종료합니다.\n"," ", " ", " ");
                    //signUp 강제 종료 코드 signUp 클래스에 작성 여기서 return false 받아서
                }
            }
        } //while
        return false;        
    }
    
    /**
     * 임의의 6자리 문자 생성
     * @return
     */
    private static String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            captcha.append(characters.charAt(index));
        }        
        return captcha.toString();
    }
    
    /**
     * Captcha 이미지 생성
     * @param generateCaptcha()
     * @return Captcha image
     */
    private static String asciiArt(String input) {     
    	String asciiArt;
		try {		
			asciiArt = FigletFont.convertOneLine(input);        
	        return asciiArt;
		} catch (IOException e) {		
			e.printStackTrace();
			return " ";
		}     

    }
}
