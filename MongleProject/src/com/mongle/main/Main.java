package com.mongle.main;

import com.mongle.database.DataBase;
import com.mongle.yourapp.SignUp;

public class Main {
	public static void main(String[] args) {
		DataBase.dataLoad();
		//LogIn.LogIn();
		//MainMenu.MainMenu();
		
		
		SignUp.signUp();
		DataBase.dataSave();
		
		
		
		
		
		
		
		
		
		
	}//main
}
