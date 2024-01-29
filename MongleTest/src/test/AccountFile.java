package test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;



public class AccountFile {
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private static final String FILE_PATH = "C:\\class\\code\\java\\MongleTest\\src\\test\\users.txt"; // 사용자 정보가 저장된 파일 경로

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern p1 = Pattern.compile("^[0-9]{3}[0-9]{2}[0-9]{4}[0-9]{3}");//1.Kb
        Pattern p3 = Pattern.compile("^[0-9]{3}[0-9]{6}[0-9]{2}[0-9]{3}");//2.ibk
        Pattern p4 = Pattern.compile("^[0-9]{3}[0-9]{4}[0-9]{4}[0-9]{2}");//3.nh
        Pattern p10 = Pattern.compile("^[0-9]{3}[0-9]{2}[0-9]{6}[0-9]{1}");//4.DGB
        Pattern p11 = Pattern.compile("^[0-9]{3}[0-9]{3}[0-9]{4}[0-9]{2}");//5.BNK
        Pattern p2 = Pattern.compile("^[0-9]{6}[0-9]{2}[0-9]{6}");//1.kb
        Pattern p5 = Pattern.compile("^[0-9]{3}[0-9]{2}[0-9]{6}");//6.신한//6.SC
        Pattern p6 = Pattern.compile("^[0-9]{3}[0-9]{3}[0-9]{6}");//신한//케이벵크
        Pattern p7 = Pattern.compile("^[0-9]{4}[0-9]{3}[0-9]{6}");//우리
        Pattern p8 = Pattern.compile("^[0-9]{3}[0-9]{6}[0-9]{5}");//KEB
        Pattern p9 = Pattern.compile("^[0-9]{3}[0-9]{6}[0-9]{3}");//외환,씨티
        Pattern p12 = Pattern.compile("^[0-9]{4}[0-9]{2}[0-9]{7}");//카카오
        
        
        
        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        System.out.print("주민등록번호를 입력하세요: ");
        String idNumber = scanner.nextLine();

        String accountNumber = openAccount(password, idNumber);
        if (accountNumber != null) {
            System.out.println("계좌가 개설되었습니다. 계좌번호: " + accountNumber);
        } else {
            System.out.println("비밀번호나 주민등록번호가 일치하지 않습니다.");
        }

        scanner.close();
    }

    public static String openAccount(String password, String idNumber) {
        try {
            File file = new File(FILE_PATH);
            
            
            Scanner scanner = new Scanner(file);
          
            while (scanner.hasNextLine()) { //hasnextLine() > 다음줄이 있는지 없는지 true or false
                String line = scanner.nextLine(); //라인 단위로 파일 읽어오기 (줄)
                String[] userInfo = line.split(",");

                String storedPassword = userInfo[0];
            
                String storedIdNumber = userInfo[1];
         

                if (password.equals(storedPassword) && idNumber.equals(storedIdNumber)) {
                    // 계좌번호 생성
                    String accountNumber = generateAccountNumber();
                    scanner.close();
                    return accountNumber;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + FILE_PATH);
            e.printStackTrace();
        }
        return null;
    }

    public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }
}

