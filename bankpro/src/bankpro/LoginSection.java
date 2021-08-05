package bankpro;

import java.sql.SQLException;
import java.util.Scanner;


public class LoginSection {
	static String lAcNo;
	static int lPinNo;
	
	static void login() throws ClassNotFoundException, SQLException {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter You ACCOUNT Number");
		lAcNo=sc.next();
		if(lAcNo.length()==16 ) {
			System.out.println("Enter You PIN Number");
		    lPinNo=sc.nextInt();
		    
		    //checking is entered pin is 4 digit or not
			int temPinNo=lPinNo;
			int count=0;
			while(temPinNo!=0) {
				temPinNo=temPinNo/10;
				count++;
			}
			if(count==4) {
				
			         IsAccountExists.isAccountExists(lAcNo);
			         IsAccountExists.isPinExists(lPinNo);
			         if(IsAccountExists.bA==true && IsAccountExists.bP==true) {
			        	 AccountSection.accounts(lAcNo,lPinNo);
			         }else if(IsAccountExists.bA==false){
						System.out.println("Invalid Vaild Account No Entered !!");
						AccountHome.home();
					}else {
						System.out.println("Incorrect Pin Entered");
						login();
					}
			         
			}else {
				System.out.println("Enter Only 4 digit PIN Number");
				login();
			}
		}
		else {
			System.out.println("Enter Only 16 digit ACCOUNT Number");
			login();
			
		}
		
	}

}
