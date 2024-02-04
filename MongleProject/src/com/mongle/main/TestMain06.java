package com.mongle.main;

import java.time.LocalDate;
import java.util.Calendar;

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
		DataBase.dataLoad();
		StartPage.startPage();
		DataBase.changeData();
		DataBase.dataSave();
//		LocalDate ldate = LocalDate.now();
//		String dates = ldate+"";
//		Calendar date = Calendar.getInstance();
//		System.out.println(ldate);
//		System.out.println(date.get(Calendar.DATE));
		
	}
	
}

