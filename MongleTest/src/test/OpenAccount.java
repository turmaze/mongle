package test;

import java.util.ArrayList;

public class OpenAccount {
	static ArrayList<String> DepositList = new ArrayList<String>();
public static void main(String[] args) {

}
	public static void SaveDeposit(String bankDepo, String titleDepo) {
		// TODO Auto-generated method stub

		DepositList.add(bankDepo);
		DepositList.add(titleDepo);
		System.out.printf("%22s"," ",DepositList);
		
		//클래스를 Account 이름으로 하나 만들어서 > 상품 정보, 잔액 , 상품 이름 > 클래스 배열 < 저장되게 0 ㅡ 3
		//송금하기 에서 계좌 번호,입금할 금액 받으면 > 계좌번호(잔액)에 연동되게 > 
		//상품 가입하면 그 은행에 맞는 계좌번호 난수로 생성.
		//계좌번호 마다 잔액 int로 변수 하나 있어야함 
		

	}

	public static void OpenDeposit() {
		
		System.out.printf("%22s",DepositList," ");
		//System.out.printf("%22s선택(번호) : ", " ");
	}
}
