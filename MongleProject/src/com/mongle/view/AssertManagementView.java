package com.mongle.view;

import java.util.Scanner;

import com.mongle.asset.DepoSave;
import com.mongle.service.AssetService;

public class AssertManagementView {
	
	public static int assertadd() {
		
		boolean loop = true;
		int r = -1;
		while (loop) {
			MongleVisual.menuHeader("자산 관리");
			System.out.println();
			System.out.printf("%22s 1.계좌관리\n", " ");
			System.out.printf("%22s 2.투자관리\n", " ");
			System.out.printf("%22s 3.대출관리\n", " ");
			
			System.out.printf("%22s 9.홈으로\n", " ");
			System.out.printf("%22s 0.이전으로\n", " ");
			System.out.printf("%22s번호를 입력하세요:", " ");

			Scanner sc = new Scanner(System.in);
			String sel = sc.nextLine();
			//
			if(sel.equals("1")) {
				r = AssetService.assmenu();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
				
			}else if(sel.equals("2")) {
				r = InvestmentView.addmenu();
				if (r == 9) {
					return 9;
				} else {
					continue;
				}
				
			}else if(sel.equals("9")) {
				return 9;
				
			}else if(sel.equals("9")) {
				return 0;
				
			}else {
				loop = false;
				
			}
	
			
		
		
	}
		return 0;
	
	

}
}
