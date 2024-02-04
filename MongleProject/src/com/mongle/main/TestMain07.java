package com.mongle.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mongle.database.DataBase;
import com.mongle.service.Inquiry;
import com.mongle.sign.SignUp;
import com.mongle.yourapp.LogIn;

public class TestMain07 {

	public static void main(String[] args) {
		DataBase.dataLoad();
		SignUp.signUp();
		LogIn.logIn();


	}
}
