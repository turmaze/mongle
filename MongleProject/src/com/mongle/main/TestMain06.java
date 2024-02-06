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
String regex = "(((19|20)([2468][048]|[13579][26]|0[48])|2000)/02/29|((19|20)[0-9]{2}/(0[469]|11)/(0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}/(0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}/02/(0[1-9]|1[0-9]|2[0-8])))";
		programRun();
	}

	private static void programRun() {
		DataBase.dataLoad();
		StartPage.startPage();
		DataBase.dataSave();
	}
	
}

