package com.mongle.service.invest;

public class InfoProduct { // 상품 정보 클래스
	private String bank;
	private String title;
	private String period;
	private double rate;
	private double maxRate;

	public InfoProduct(String bank, String title, String period, double rate, double maxRate) {
		this.bank = bank;
		this.title = title;
		this.period = period;
		this.rate = rate;
		this.maxRate = maxRate;
	}

	public String getBank() {
		return bank;
	}

	public String getTitle() {
		return title;
	}

	public String getPeriod() {
		return period;
	}

	public double getRate() {
		return rate;
	}

	public double getMaxRate() {
		return maxRate;
	}

}