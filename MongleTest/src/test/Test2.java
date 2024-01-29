package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import test.Depo.InfoProduct;

public class Test2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Depo.getDeposit();

	}

	public void DepositSignUp(int sel, List<InfoProduct> data) {
		Scanner sc = new Scanner(System.in);
		//	
			data.get(sel);
			sel-=1;
			System.out.printf("|%-3d|%-12s|%-15s\t|%5s개월|%6s%%|%6s%%|\n",sel+1, data.get(sel).getBank(),
					data.get(sel).getTitle(), data.get(sel).getPeriod(), data.get(sel).getRate(), data.get(sel).getMaxRate());
			
			System.out.println("선택한 상품이 맞으신가요?(y/n)");
			String answer = sc.nextLine();
			if(answer.equals("y") || answer.equals("Y")) {
				//Reconfirm();
				System.out.println("비밀번호 입력하세요.");
			}else{
				System.out.println("비밀번호가 불일치 합니다.");
				//돌아가기
			}
			
			
	}//DepositSignUp

	public void Reconfirm() { 
		Scanner sc = new Scanner(System.in);
		System.out.println("비밀번호를 입력해 주세요:");
		String rpw = sc.nextLine();
		if(rpw == pw) {
			//가입성공
			 
			
		}else if{
			//비밀번호 불일치
		}
	}//Reconfirm

}//RootClass
