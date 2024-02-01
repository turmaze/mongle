package com.mongle.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import com.mongle.database.DataBase;
import com.mongle.service.invest.Stock;
import com.mongle.service.mypage.AttendanceCheck;
import com.mongle.yourapp.LogIn;
import com.mongle.yourapp.SignUp;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		DataBase.dataLoad();
		//SignUp.signUp();
		
		//DataBase.changeData(DataBase.getUser(),"name","change"); //sample
		
		LogIn.logIn();
		//DataBase.loadPrivateUser(LogIn.primaryKey);
		//MainMenu.MainMenu();
		
		//Stock.stockService();
		//AttendanceCheck.attendanceCheckService();
		
		DataBase.dataSave();
		
		
		
		
		
		
		
		
	}//main
}
