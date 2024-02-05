package com.mongle.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mongle.resource.BankAccount;
import com.mongle.resource.Investment;
import com.mongle.service.InvestService;

public class InvestmentView {
   
   
      
   public static int addmenu() {
      
      Scanner scan = new Scanner(System.in);
      boolean loop = true;
      while(loop) {
         
         MongleVisual.menuHeader("투자 관리");
         
         System.out.printf("%22s1. 주식\n", " " );
         System.out.printf("%22s2. 골드\n", " " );
         System.out.printf("%22s3. 환전\n", " ");
         System.out.printf("%22s4. 대출\n", " ");
         System.out.printf("%22s9. 홈으로\n", " ");
         System.out.printf("%22s0. 이전으로\n", " ");
          
         
         System.out.printf("%22s사용자 입력 : ", " ");
         String num = scan.nextLine();
         
         if(num.equals("1")) { // 주식
            stocksave();
         }else if(num.equals("2")) { //골드
            goldsave();
         }else if(num.equals("3")) {// 환전
            Exchangesave();
         }else if(num.equals("4")) { // 대출
            loansave();
            
         }else if(num.equals("9")) { // 홈으로
            return 9;
            
         }else if(num.equals("0")) { // 이전으로
            return 0;
            
         } else {
            loop = false;
            
         }
      }
      return 0;
      
   }

   private static void loansave() { // 대출
      
      Scanner scan = new Scanner(System.in);
      boolean loop = true;
      System.out.println();
      System.out.printf("%22s상세 보기\n", " ");
   
      
      printAsciiTable(Investment.list,"대출");
   
      
      while(loop) {
         
         System.out.printf("%22s 옵션 : \n", " ");
         System.out.printf("%22s1. 해제 \n", " ");
         System.out.printf("%22s2. 이전으로\n", " ");
         System.out.println();
         System.out.printf("%22s사용자 입력 : ", " ");
         String num4 = scan.nextLine();
         
         if(num4.equals("1")) {
            // 삭제 후 상세 불러오기
            System.out.printf("%22s해제하고 싶은 상품 번호를 입력해 주세요 :", " ");
            int removeN = scan.nextInt();
            removeLoanInvestmentByJ(Investment.list, removeN,"대출");
            printAsciiTable(Investment.list,"대출");
         break;
         }else if(num4.equals("2")) {
            loop = false;
            
         }
   }// while(loop4)
         
      
   }




   


   private static void Exchangesave() {
      Scanner scan = new Scanner(System.in);
      boolean loop = true;
      System.out.println();
      System.out.printf("%22s상세 보기\n", " ");
      
      printAsciiTable(Investment.list,"환전");
   
      
      while(loop) {
         
         System.out.printf("%22s 옵션 : \n", " ");
         System.out.printf("%22s1. 해제 \n", " ");
         System.out.printf("%22s2. 이전으로\n", " ");
         System.out.println();
         System.out.printf("%22s사용자 입력 : ", " ");
         String num4 = scan.nextLine();
         
         if(num4.equals("1")) {
            System.out.printf("%22s해제하고 싶은 상품 번호를 입력해 주세요 :", " ");
            int removeN = scan.nextInt();
            System.out.println("정말로 해지하시겠습니까?");
            transaction(removeN, "환전");
            //totalmoney(Investment.list,removeN,"금");
            removeLoanInvestmentByJ(Investment.list, removeN,"환전");
            printAsciiTable(Investment.list,"환전");
         
            
            break;
            
         }else if(num4.equals("2")) {
            loop = false;
            
         }
   }
      
      
   }




   



   private static void goldsave() {
      Scanner scan = new Scanner(System.in);
      boolean loop = true;
      System.out.println();
      System.out.printf("%22s상세 보기\n", " ");
      
      
      printAsciiTable(Investment.list,"금");
      
      while(loop) {
         
         System.out.printf("%22s 옵션 : \n", " ");
         System.out.printf("%22s1. 해제 \n", " ");
         System.out.printf("%22s2. 이전으로\n", " ");
         System.out.println();
         System.out.printf("%22s사용자 입력 : ", " ");
         String num3 = scan.nextLine();
         
         if(num3.equals("1")) {
            // 삭제 후 상세 불러오기
            System.out.printf("%22s해제하고 싶은 상품 번호를 입력해 주세요 :", " ");
            int removeN = scan.nextInt();
            System.out.println("정말로 해지하시겠습니까?");
            transaction(removeN, "금");
            //totalmoney(Investment.list,removeN,"금");
            removeLoanInvestmentByJ(Investment.list, removeN,"금");
            printAsciiTable(Investment.list,"금");
            
            
            
         break;
         }else if(num3.equals("2")) {
            loop = false;
            
         }
      }
   
   }



   




   private static void stocksave() {
      
      Scanner scan = new Scanner(System.in);
      boolean loop = true;
      boolean loop2 = true;
      System.out.println();
      System.out.printf("%22s상세 보기\n", " ");
      
      
      printAsciiTable(Investment.list,"주식");
      
                  
      
      while(loop) { 
         System.out.println();
         System.out.printf("%22s 옵션 : \n", " ");
         System.out.printf("%22s1. 매수\n" , " ");
         System.out.printf("%22s2. 일괄매도\n", " ");
         System.out.printf("%22s0. 이전으로\n", " ");
         System.out.println();
         System.out.printf("%22s사용자 입력 : " , " ");
         String num2 = scan.nextLine();

         if(num2.equals("1")) { ////  매수
            
            System.out.printf("%22s매수할 상품번호를 선택하시오: " , " ");
            int removeN = scan.nextInt();
                 
            System.out.printf("%22s매수할 금액을 입력해 주세요 : ", " ");
            String price = scan.nextLine();
            System.out.printf("%22s 수량을 입력해 주세요 : ", " ");
            int num = scan.nextInt();
          
            //InvestService.transaction(price,num);
 
            // 매수하고 상세보기 불러오기

         }else if(num2.equals("2")) { // 일괄매도
            
            System.out.printf("%22s일괄매도할 상품번호를 선택하시오:", " ");
            int removeN = scan.nextInt();
           
            System.out.printf("%22s정말로 해지하시겠습니까? (y/n) : \n", " ");
            System.out.printf("%22s 사용자 입력 : " , " ");
            while(loop2) {
            String answer = scan.nextLine();
            	if(answer.equals("y")) {
			         transaction(removeN, "주식");
			         //totalmoney(Investment.list,removeN,"금");
		            removeLoanInvestmentByJ(Investment.list, removeN,"주식");
	                 printAsciiTable(Investment.list,"주식");
				     break;
				            }else if (answer.equals("n")) {
				            	System.out.printf("%22s이전으로 돌아갑니다.", " ");
				            	break;
				            }
            }
         }else if(num2.equals("0")) {
        	 loop = false;
         }
      }
            
            
            
          
           
         
      
      
      
   }



   public static void printAsciiTable(ArrayList<Investment> data,String invest) { // 표에 반복해서 출력하는 메서드
         int j = 0;
      for (int i = 0; i < data.size(); i++) {
         if (invest.equals(data.get(i).getRealTitle())){
         System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", j + 1, data.get(i).getBankDepo(),
               data.get(i).getTitleDepo(), data.get(i).getPrice(), data.get(i).getAmount());
         j++;
         }
         
      }
   }
   

   
   private static void removeLoanInvestmentByJ(ArrayList<Investment> data, int removeN, String invest) {
         int j = 0;
       for (int i = 0; i < data.size(); i++) {
            if (invest.equals(data.get(i).getRealTitle())) {
               j++;
                if (j == removeN) {
                    data.remove(i);
                    return; // 삭제 후 바로 메서드 종료
                }
            }
       }
   }
   
   private static int totalmoney(ArrayList<Investment> data, int removeN, String invest) {
      int j = 0;
      int total = 0;
    for (int i = 0; i < data.size(); i++) {
         if (invest.equals(data.get(i).getRealTitle())) {
           j++;
             if (j == removeN) {
               total= (int)data.get(i).getPrice() * data.get(i).getAmount();
             }
         }
    }
    return total;
}
   public static int transaction(int removeN ,String invest) {
      Scanner scan = new Scanner(System.in);
      
      MongleVisual.pusher();

      String header = "+---+---------------------+-----------------------+-----------------------+-----------------+";
      System.out.printf("%22s%s\n", " ", header);
      System.out.printf("%22s|번호|       금융사   \t|         상품명      \t|         계좌번호\t\t|       잔액       |\n", " ");
      System.out.printf("%22s%s\n", " ", header);
      List<BankAccount> filteredList = BankAccount.list.stream().filter(acc -> acc.getTitleDepo().contains("예금"))
            .collect(Collectors.toList());
      print(filteredList); // json 에서 가져온 데이터
      System.out.printf("%22s%s\n", " ", header);
      System.out.printf("%22s 계좌를 선택해주세요.\n", " ");
      System.out.printf("%22s0. 이전으로\n", " ");
      boolean loop = true;

      while (loop) {
         System.out.printf("%22s선택(번호) : ", " ");
         String sel = scan.nextLine();
         int totalPrice = totalmoney(Investment.list,removeN, invest );
         
         if (sel.equals("0")) {
            MongleVisual.pusher();
            System.out.printf("%22s이전으로 돌아갑니다.\n", " ");
            MongleVisual.stopper();
            return 0;
         }

         try {
            if (Integer.parseInt(sel) >= 1 && Integer.parseInt(sel) <= filteredList.size()) {

               for (BankAccount acc : BankAccount.list) {
                  if (acc.getAccountNumber()
                        .equals(filteredList.get(Integer.parseInt(sel) - 1).getAccountNumber())) {
                     if (totalPrice>0) {

                        int rest = acc.getDepositAmount() + totalPrice;
                        BankAccount.list.set(BankAccount.list.indexOf(acc), new BankAccount(acc.getBankDepo(),
                              acc.getTitleDepo(), acc.getAccountNumber(), rest));
                        
                        System.out.println();
                        
                        System.out.printf("%22s해제가 완료되었습니다.\n", " ");
                        //System.out.printf("%22s해제후 잔액은 %,d원입니다.\n", " ", rest);
                        MongleVisual.stopper();
                        loop = false;
                     }
                     
                  }
               }
            } else {
               System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
            }
         } catch (NumberFormatException e) {
            System.out.printf("%22s올바른 번호를 입력해주세요.\n", " ");
         }
      }
      return 0;

   }
   
   public static void print(List<BankAccount> data) { // 표에 반복해서 출력하는 메서드
      for (int i = 0; i < data.size(); i++) {
         System.out.printf("%22s|%-3d|%-14s\t|%-18s\t|%15s\t|%,15d원|\n", " ", i + 1, data.get(i).getBankDepo(),
               data.get(i).getTitleDepo(), data.get(i).getAccountNumber(), data.get(i).getDepositAmount());

      }
   }


   

}