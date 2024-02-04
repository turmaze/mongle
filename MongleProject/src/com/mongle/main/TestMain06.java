package com.mongle.main;

import java.time.LocalDate;

import javax.xml.crypto.Data;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.resource.ResourcePath;
import com.mongle.service.MypageService;
import com.mongle.service.invest.Stock;
import com.mongle.service.mypage.AttendanceCheck;
import com.mongle.sign.SignOut;
import com.mongle.yourapp.LogIn;
import com.mongle.yourapp.MainMenu;
import com.mongle.yourapp.StartPage;

public class TestMain06 {
	
	public static void main(String[] args) {
//		DataBase.dataLoad();
//		
//		StartPage.startPage();
//		
//		DataBase.changeData();
//		
//		DataBase.dataSave();
		LocalDate date = LocalDate.now();
		System.out.println(date);
		String dates = date+"";
		System.out.println(dates);
		System.out.println(dates.substring(dates.length()-2));
		int datei = Integer.parseInt((dates.substring(dates.length()-2)));
		System.out.println(datei);
		//int todayDate = ((String)date);
		//System.out.println(todayDate);
		
	}
	
}

