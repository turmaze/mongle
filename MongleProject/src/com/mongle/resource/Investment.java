package com.mongle.resource;

import java.util.ArrayList;

/**
 * 투자 클래스
 */
public class Investment {

	private int price;
	private int amount;

	private String bankDepo;
	private String titleDepo;
	private String realTitle;

	/**
	 * 투자 리스트
	 */
	public static ArrayList<Investment> list = new ArrayList<>();

	/**
	 * 투자 생성자
	 * 
	 * @param realTitle 투자 분야
	 * @param bankDepo  투자 금융사
	 * @param titleDepo 투자 내용
	 * @param price     가격
	 * @param amount    수량
	 */
	public Investment(String realTitle, String bankDepo, String titleDepo, int price, int amount) {
		this.realTitle = realTitle;
		this.bankDepo = bankDepo;
		this.titleDepo = titleDepo;
		this.price = price;
		this.amount = amount;

	}

	/**
	 * 투자 분야 Getter
	 * 
	 * @return 투자 분야
	 */
	public String getRealTitle() {
		return realTitle;
	}

	/**
	 * 투자 분야 Setter
	 * 
	 * @param realTitle 투자 분야
	 */
	public void setRealTitle(String realTitle) {
		this.realTitle = realTitle;
	}

	/**
	 * 가격 Getter
	 * 
	 * @return 가격
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 가격 Setter
	 * 
	 * @param price 가격
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 수량 Getter
	 * 
	 * @return 수량
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * 수량 Setter
	 * 
	 * @param amount 수량
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * 투자 금융사 Getter
	 * 
	 * @return 투자 금융사
	 */
	public String getBankDepo() {
		return bankDepo;
	}

	/**
	 * 투자 금융사 Setter
	 * 
	 * @param bankDepo 투자 금융사
	 */
	public void setBankDepo(String bankDepo) {
		this.bankDepo = bankDepo;
	}

	/**
	 * 투자 내용 Getter
	 * 
	 * @return 투자 내용
	 */
	public String getTitleDepo() {
		return titleDepo;
	}

	/**
	 * 투자 내용 Setter
	 * 
	 * @param titleDepo 투자 내용
	 */
	public void setTitleDepo(String titleDepo) {
		this.titleDepo = titleDepo;
	}

	/**
	 * 투자 리스트 Getter
	 * 
	 * @return 투자 리스트
	 */
	public static ArrayList<Investment> getList() {
		return list;
	}

	/**
	 * 투자 리스트 Setter
	 * 
	 * @param list 투자 리스트
	 */
	public static void setList(ArrayList<Investment> list) {
		Investment.list = list;
	}

	/**
	 * 투자 toString() String.format("Investment [price=%s, amount=%s, bankDepo=%s,
	 * titleDepo=%s]", price, amount, bankDepo, titleDepo);
	 */
	@Override
	public String toString() {
		return String.format("Investment [price=%s, amount=%s, bankDepo=%s, titleDepo=%s]", price, amount, bankDepo,
				titleDepo);
	}

}
