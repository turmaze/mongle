package com.mongle.resource;

import java.util.ArrayList;

public class Investment {

	private int price;
	private int amount;

	private String bankDepo;
	private String titleDepo;
	private String realTitle;

	public static ArrayList<Investment> list = new ArrayList<>();

	public Investment(String realTitle , String bankDepo, String titleDepo, int price, int amount) {
		this.realTitle = realTitle;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;
		this.price = price;
		this.amount = amount;

	}


	public String getRealTitle() {
		return realTitle;
	}


	public void setRealTitle(String realTitle) {
		this.realTitle = realTitle;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getBankDepo() {
		return bankDepo;
	}


	public void setBankDepo(String bankDepo) {
		this.bankDepo = bankDepo;
	}


	public String getTitleDepo() {
		return titleDepo;
	}


	public void setTitleDepo(String titleDepo) {
		this.titleDepo = titleDepo;
	}


	public static ArrayList<Investment> getList() {
		return list;
	}


	public static void setList(ArrayList<Investment> list) {
		Investment.list = list;
	}


	@Override
	public String toString() {
		return String.format("Investment [price=%s, amount=%s, bankDepo=%s, titleDepo=%s]", price, amount, bankDepo,
				titleDepo);
	}

}
