package bankpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;


public class RegisterSection {
	static String url="jdbc:mysql://localhost:3306/accounts";
	static String username="root";
	static String pwd="magi";
	
	static String rName;
	static String rAcno;
	static int rPin;
	
	static void register() throws ClassNotFoundException, SQLException{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter your Name : ");
	rName=sc.nextLine();
	System.out.println("Enter you Account No : ");
	rAcno=sc.next();
	if(rAcno.length()!=16) {
		System.out.println("Enter only 16 Digit Account Number");
		register();
	}else {
	IsAccountExists.isAccountExists(rAcno);
	}
	if(IsAccountExists.bA==false) {
		pinGenerator();
	}else {
		System.out.println("The Entered Account No is already Exists..");
		register();
	}
	}

	private static void pinGenerator() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Random r=new Random();
		int r1[]=new int[4];
		int pinTemp,prPin, j=3;
		boolean b2 = true;
		
		System.out.println("Your Pin : ");
		for(int i=0;i<4;i++) {
		 r1[i]=r.nextInt(8)+1;
		 System.out.print(r1[i]);
		}
		System.out.println();
		System.out.println("ReType the pin : ");
		Scanner sc=new Scanner(System.in);
		prPin=sc.nextInt();
		rPin=prPin;
			while(prPin!=0) {
				pinTemp=prPin%10;
				if(pinTemp==r1[j]) {
					j--;
				}else {
					b2=false;
				}
				prPin=prPin/10;
			}
			
			if(b2==false) {
				System.out.println("pin Incorrectly entered");
				pinGenerator();
			}
			else {
	             IsAccountExists.isPinExists(rPin);
				if( IsAccountExists.bP==false) {
					completeRegistation();
				}else {
					System.out.println("The Entered pin is Already exists..");
					pinGenerator();
				}
				}
			}

	private static  void completeRegistation() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con3=DriverManager.getConnection(url, username, pwd);
		PreparedStatement ps3=con3.prepareStatement("insert into accountDetails2 values(?,?,?,?)");
		ps3.setString(1, rName);
		ps3.setString(2,  rAcno);
		ps3.setInt(3, rPin);
		ps3.setInt(4, 0);
		ps3.executeUpdate();
		con3.close();
		AccountSection.accounts(rAcno,rPin);
		
	}

}
