package com.mongle.main;

import javax.xml.crypto.Data;

import com.mongle.database.DataBase;
import com.mongle.resource.BankAccount;
import com.mongle.resource.ResourcePath;
import com.mongle.service.MypageService;
import com.mongle.service.invest.Stock;
import com.mongle.sign.SignOut;
import com.mongle.yourapp.LogIn;
import com.mongle.yourapp.MainMenu;
import com.mongle.yourapp.StartPage;

public class TestMain06 {
	
	public static void main(String[] args) {
		DataBase.dataLoad(ResourcePath.MEMBER);
		StartPage.startPage();
//		LogIn.logIn();
		
		//DataBase.changeData(DataBase.getPrivateUser(), "계좌", BankAccount.list);
		//DataBase.changeData(DataBase.getUser(), "계좌", BankAccount.list);
		System.out.println(DataBase.getUser());
		DataBase.changeData();
		System.out.println(DataBase.getUser());
		
		DataBase.dataSave(ResourcePath.MEMBER);
		//SignOut.signOutService();
		//MypageService.mypageService();
		//MainMenu.mainMenu("1");
		//Stock.stockService();
		
	}
	
}

