package com.mongle.loan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.asset.GiveAccount;
import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.view.MongleVisual;

public class Loan {

	public static ArrayList<Loan> loanlist = new ArrayList<>();

	
	private String loanName;//대출명
	private int principal; //원금
	private float rate; //이자율
	private int loanPeriod; // 대출기간(개월)
	private int rPeriod; //남은기간

	public String getloanName() {
		return loanName;
	}


	public void setloanName(String loanName) {
		this.loanName = loanName;
	}


	public int getprincipal() {
		return principal;
	}


	public void setprincipal(int principal) {
		this.principal = principal;
	}


	public float getRate() {
		return rate;
	}


	public void setRate(float rate) {
		this.rate = rate;
	}


	public int getloanPeriod() {
		return loanPeriod;
	}


	public void setloanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}


	public int getrPeriod() {
		return rPeriod;
	}


	public void setrPeriod(int rPeriod) {
		this.rPeriod += rPeriod;
	}


	

	
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
	
	
	
	
	public static  void openLoan() {
		LoanFile.load();

		Loan loan = new Loan(null, 0, 0, 0, 0);
		for(Loan acc : LoanFile.filelist) {

			loan = acc;
			LoanFile.filelist.remove(acc);
			break;
		}
		loanlist.add(loan);
		
		LoanFile.save();
	
		
	}
	
	
	
	public static void checkLoan() {
		// 헤더 출력
		Scanner scan = new Scanner(System.in);
		int r = -1;

		while (true) {

			String header = "+---------------+---------------+--------+---------+--------+";
			System.out.printf("%s\n", header);
			System.out.printf("|      대출명    \t|       원금   \t|  이자율  |  대출기간 | 남은기간  |\n", " ");
			System.out.printf("%s\n", header);
			printLoanTable(loanlist); 
			System.out.printf("%s\n", header);

			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			String sel = scan.nextLine();
			if(sel.equals("0")) {
				break;
			}
		

		}

	}
	
	public static void printLoanTable(ArrayList<Loan> data) { 
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("|%-9s\t|%,12d원\t|%6.1f%% |%5d개월 |%5d개월 |\n",  data.get(i).getloanName(),
					data.get(i).getprincipal(), data.get(i).getRate(), data.get(i).getloanPeriod(),data.get(i).getrPeriod());

		}
	}


	public static void extension() {
		Scanner sc = new Scanner(System.in);
		String sel;
		int exten = 0;
		System.out.printf("%22s대출을 연장 하시겠습니까?(y/n)"," ");
		sel = sc.nextLine();
	
		if(sel.equals("y") || sel.equals("Y")) {
			//연장
			System.out.printf("%22s기간을 몇 개월 연장 하시겠습니까?"," ");
			exten += sc.nextInt();
				
			loanlist.get(0).setrPeriod(exten);
			checkLoan();
			
			
		}else {
			System.out.printf("%22s9. 홈으로\n", " ");
			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();
			System.out.printf("%22s이전 화면으로 돌아갑니다."," ");
			
		}
	
		
	}


	public static void calculrator() {
		//  중도 상환 수수료 계산기
		String header = "+---------------+---------------+--------+---------+--------+";
		System.out.printf("%s\n", header);
		System.out.printf("|      대출명    \t|       원금   \t|  이자율  |  대출기간 | 남은기간  |\n", " ");
		System.out.printf("%s\n", header);
		printLoanTable(loanlist); 
		System.out.printf("%s\n", header);
		System.out.println();
		int a = loanlist.get(0).getloanPeriod();
		int b = loanlist.get(0).getrPeriod();
		int c = loanlist.get(0).getprincipal();
		
		int result = a-b;
		
		if(result <= 3) {
			System.out.printf("%22s %s을 중도 상환시 수수료 %,d원 입니다\n. "," ",loanlist.get(0).getloanName(),c/100);
		}
		else if(result > 3 && result <= 6) {
			System.out.printf("%22s %s을 중도 상환시 수수료 %,d원 입니다\n. "," ",loanlist.get(0).getloanName(),(c/100)/2);
		}
		else {
			System.out.printf("%22s %s을 6개월 이상 상환 하셔서 수수료가 없습니다 ! \n"," ",loanlist.get(0).getloanName());
		}
		
		
		
		
	}
}
