package com.mongle.database;

import java.util.regex.Pattern;

public class Rpattern {
	
		Pattern p1 = Pattern.compile("^[0-9]{3}-[0-9]{2}-[0-9]{4}-[0-9]{3}");//1.Kb
		Pattern p3 = Pattern.compile("^[0-9]{3}-[0-9]{6}-[0-9]{2}-[0-9]{3}");//2.ibk
		Pattern p4 = Pattern.compile("^[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{2}");//3.nh
		Pattern p10 = Pattern.compile("^[0-9]{3}-[0-9]{2}-[0-9]{6}-[0-9]{1}");//4.DGB
		Pattern p11 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}-[0-9]{2}");//5.BNK
		Pattern p2 = Pattern.compile("^[0-9]{6}-[0-9]{2}-[0-9]{6}");//1.kb
		Pattern p5 = Pattern.compile("^[0-9]{3}-[0-9]{2}-[0-9]{6}");//6.신한//6.SC
		Pattern p6 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{6}");//신한//케이벵크
		Pattern p7 = Pattern.compile("^[0-9]{4}-[0-9]{3}-[0-9]{6}");//우리
		Pattern p8 = Pattern.compile("^[0-9]{3}-[0-9]{6}-[0-9]{5}");//KEB
		Pattern p9 = Pattern.compile("^[0-9]{3}-[0-9]{6}-[0-9]{3}");//외환,씨티
		Pattern p12 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{7}");//카카오

		Pattern[]block4 = {p1,p3,p4,p10,p11};
		Pattern[]block3 = {p2,p5,p6,p7,p8,p9,p12};
		
		String [] bankblock4 = {"KB국민은행","IBK기업은행", "NH농협은행","DGB대구은행","BNK부산은행"};
		String [] bankblock3 = {"KB 국민은행","신한은행-SC제일은행","신한은행-케이뱅크","우리은행","KEB하나은행","씨티은행-외환은행","카카오벵크",};
	
}
