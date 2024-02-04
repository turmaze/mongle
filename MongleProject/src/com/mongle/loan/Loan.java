package com.mongle.loan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.mongle.asset.GiveAccount;
import com.mongle.resource.BankAccount;

public class Loan {

	public static ArrayList<Loan> loanlist = new ArrayList<>();
	
	private String loanName;//대출명
	private int principal; //원금
	private float rate; //이자율
	private int loanPeriod; // 대출기간(개월)
	private int period; //남은기간

	public String getLoanName() {
		return loanName;
	}


	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}


	public int getPrincipal() {
		return principal;
	}


	public void setPrincipal(int principal) {
		this.principal = principal;
	}


	public float getRate() {
		return rate;
	}


	public void setRate(float rate) {
		this.rate = rate;
	}


	public int getLoanPeriod() {
		return loanPeriod;
	}


	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}


	public int getrPeriod() {
		return rPeriod;
	}


	public void setrPeriod(int rPeriod) {
		this.rPeriod = rPeriod;
	}


	private int rPeriod; //남은 기간(개월)

	
	public Loan(String loanName, int principal, float rate, int loanPeriod, int rPeriod) {
		super();
		this.loanName = loanName;
		this.principal = principal;
		this.rate = rate;
		this.loanPeriod = loanPeriod;
		this.rPeriod = rPeriod;
	}


	@Override
	public String toString() {
		return "Loan [대출명=" + loanName + ", 원금=" + principal + ", 이자율=" + rate + ", 대출 기간="
				+ loanPeriod + ", 남은기간=" + rPeriod + "]";
	}
	
	
	
	
}
