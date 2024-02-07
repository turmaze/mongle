package com.mongle.service.loan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.mongle.resource.BankAccount;
import com.mongle.resource.History;
import com.mongle.service.asset.GiveAccount;
import com.mongle.view.MongleVisual;

/**
 * 대출클래스
 */
public class ManageLoan {
	/**
	 * 대출 리스트
	 */
	public static ArrayList<ManageLoan> loanlist = new ArrayList<>();

	/**
	 * 대출 생성자
	 * 
	 * @param loanName       대출명
	 * @param principal      대출금액
	 * @param rate           이자율
	 * @param loanPeriod대출기간
	 * @param rPeriod        남은기간
	 */
	public ManageLoan(String loanName, int principal, float rate, int loanPeriod, int rPeriod) {
		super();
		this.loanName = loanName;
		this.principal = principal;
		this.rate = rate;
		this.loanPeriod = loanPeriod;
		this.rPeriod = rPeriod;
	}

	private String loanName;
	private int principal;
	private float rate;
	private int loanPeriod;
	private int rPeriod;

	/**
	 * 대출명 Getter
	 * 
	 * @return 대출명
	 */
	public String getloanName() {
		return loanName;
	}

	/**
	 * 대출명 Setter
	 * 
	 * @param loanName 대출명
	 */
	public void setloanName(String loanName) {
		this.loanName = loanName;
	}

	/**
	 * 원금 Getter
	 * 
	 * @return 대출 금액
	 */
	public int getprincipal() {
		return principal;
	}

	/**
	 * 원금 Setter
	 * 
	 * @param principal 원금
	 */
	public void setprincipal(int principal) {
		this.principal = principal;
	}

	/**
	 * 이자율 Getter
	 * 
	 * @return 이자율
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * 이자율 Setter
	 * 
	 * @param rate 이자율
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}

	/**
	 * 대출 기간 Getter
	 * 
	 * @return 대출기간
	 */
	public int getloanPeriod() {
		return loanPeriod;
	}

	/**
	 * 대출 기간 Setter
	 * 
	 * @param loanPeriod 대출기간
	 */
	public void setloanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	/**
	 * 대출 남은기간 getter
	 * 
	 * @return 남은기간
	 */
	public int getrPeriod() {
		return rPeriod;
	}

	/**
	 * 남은기간 Setter
	 * 
	 * @param 연장기간
	 */
	public void setrPeriod(int rPeriod) {
		this.rPeriod += rPeriod;
	}

	/**
	 * "Loan [대출명=" + loanName + ", 원금=" + principal + ", 이자율=" + rate + ", 대출 기간="
	 * + loanPeriod + ", 남은기간=" + rPeriod + "]";
	 */
	@Override
	public String toString() {
		return "Loan [대출명=" + loanName + ", 원금=" + principal + ", 이자율=" + rate + ", 대출 기간=" + loanPeriod + ", 남은기간="
				+ rPeriod + "]";
	}

	/**
	 * 대출 상품 저장 메서드
	 */
	public static void openLoan() {
		LoanFile.load();

		ManageLoan loan = new ManageLoan(null, 0, 0, 0, 0);
		for (ManageLoan acc : LoanFile.filelist) {

			loan = acc;
			LoanFile.filelist.remove(acc);
			break;
		}
		loanlist.add(loan);

		LoanFile.save();

	}

	/**
	 * 대출 상품 출력 메서드
	 */
	public static int checkLoan() {

		Scanner scan = new Scanner(System.in);
		int r = -1;

		while (true) {

			String header = "+---------------+---------------+--------+-----------+----------+";
			System.out.printf("%s\n", header);
			System.out.printf("|    대출명  \t|     원금   \t| 이자율 | 대출기간 | 남은기간 |\n", " ");
			System.out.printf("%s\n", header);
			printLoanTable(loanlist);
			System.out.printf("%s\n", header);

			System.out.printf("%22s0. 이전으로\n", " ");
			MongleVisual.choiceGuidePrint();

			String sel = scan.nextLine();
			if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			} else {
				MongleVisual.wrongInput();
			}

		}

	}

	/**
	 * 대출 상품 출력 규격 메서드
	 * 
	 * @param data 보유 대출 리스트
	 */
	public static void printLoanTable(ArrayList<ManageLoan> data) {
		for (int i = 0; i < data.size(); i++) {
			System.out.printf("|%-9s\t|%,12d원\t|%6.1f%% |%5d개월 |%5d개월 |\n", data.get(i).getloanName(),
					data.get(i).getprincipal(), data.get(i).getRate(), data.get(i).getloanPeriod(),
					data.get(i).getrPeriod());

		}
	}

	/**
	 * 대출 기간 연장 메서드
	 */
	public static int extension() {
		Scanner sc = new Scanner(System.in);
		String sel;
		int exten = 0;
		Scanner scan = new Scanner(System.in);
		boolean tf = true;
		
		System.out.printf("%22s대출을 연장 하시겠습니까?(y/n)", " ");
		sel = scan.nextLine();
		
		if (sel.equals("y") || sel.equals("Y")) {
			while(tf) {
			System.out.printf("%22s기간을 몇 개월 연장 하시겠습니까?", " ");
			exten = scan.nextInt();
			if(exten > 0 && exten<=12) {
			loanlist.get(0).setrPeriod(exten);
			checkLoan();
			tf=false;
			}else {
				System.out.println();
				System.out.printf("%22s최대 연장 기간은 12개월 입니다.\n", " ");
			}
		}
	}else if(sel.equals("n") || sel.equals("N")) {
		System.out.printf("%22s이전 화면으로 돌아갑니다.\n", " ");
		return 0;
	}else {
		MongleVisual.wrongInput();
	}
		return 0;
	}

	/**
	 * 중도 상환 수수료 계산기
	 */
	public static int calculrator() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			String header = "+---------------+---------------+--------+-----------+----------+";
			System.out.printf("%s\n", header);
			System.out.printf("|    대출명  \t|     원금   \t| 이자율 | 대출기간 | 남은기간 |\n", " ");
			System.out.printf("%s\n", header);
			printLoanTable(loanlist);
			System.out.printf("%s\n", header);
			System.out.println();
			System.out.printf("%22s첫상환 후 3개월 이내 시 수수료 1%% 적용됩니다\n ", " ");
			System.out.printf("%21s첫상환 후 6개월 이내 시 수수료 0.5%% 적용됩니다\n ", " ");
			System.out.printf("%21s첫상환 후 6개월 이상 시 수수료 가 없습니다\n ", " ");
			System.out.println();
			int a = loanlist.get(0).getloanPeriod();
			int b = loanlist.get(0).getrPeriod();
			int c = loanlist.get(0).getprincipal();

			int result = a - b;
			if(result>0) {
			    if (result <= 3) {
				System.out.printf("%22s%s을 %d개월 상환 했습니다,중도 상환시 수수료 %,d원 입니다\n ", " ", loanlist.get(0).getloanName(),
						result, c / 100);

			} else if (result > 3 && result <= 6) {
				System.out.printf("%22s%s을 %d개월 상환 했습니다,중도 상환시 수수료 %,d원 입니다\n ", " ", loanlist.get(0).getloanName(),
						result, (c / 100) / 2);
			} else{
				System.out.printf("%22s%s을 6개월 이상 상환 하셔서 수수료가 없습니다 ! \n", " ", loanlist.get(0).getloanName());
			}
		}
			
			System.out.println();
			System.out.printf("%22s0. 이전으로", " ");
			MongleVisual.choiceGuidePrint();
			//중도 상환 수수료 계산기 상환 개월 수 최소값 0
			String sel = scan.nextLine();
			if (sel.equals("0")) {
				MongleVisual.menuMove("이전 화면");
				return 0;
			} else {
				MongleVisual.wrongInput();
			}
			return 0;
		}
	}
}
