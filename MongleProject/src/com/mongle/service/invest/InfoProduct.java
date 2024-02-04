package com.mongle.service.invest;

/**
 * 금융 상품 정보 클래스
 */
public class InfoProduct {
	private String bank;
	private String title;
	private String period;
	private double rate;
	private double maxRate;

	/**
	 * 금융 상품 정보 생성자
	 * 
	 * @param bank    금융사 이름
	 * @param title   금융 상품 이름
	 * @param period  금융 상품 기간
	 * @param rate    평균 or 최저 금리
	 * @param maxRate 최고 금리
	 */
	public InfoProduct(String bank, String title, String period, double rate, double maxRate) {
		this.bank = bank;
		this.title = title;
		this.period = period;
		this.rate = rate;
		this.maxRate = maxRate;
	}

	/**
	 * 금융사 이름 Getter
	 * 
	 * @return 금융사 이름
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * 금융 상품 이름 Getter
	 * 
	 * @return 금융 상품 이름
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 금융 상품 기간 Getter
	 * 
	 * @return 금융 상품 기간
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * 평균 or 최저 금리 Getter
	 * 
	 * @return 평균 or 최저 금리
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * 최고 금리 Getter
	 * 
	 * @return 최고 금리
	 */
	public double getMaxRate() {
		return maxRate;
	}

}