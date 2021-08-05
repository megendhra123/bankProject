package bankpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class AccountSection {
	static String url="jdbc:mysql://localhost:3306/accounts";
	static String username="root";
	static String pwd="magi";
	
	
	static void accounts(String acNo,int pinNo) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url, username, pwd);
		Statement s=con.createStatement();
		ResultSet rs=s.executeQuery("select * from accountDetails2 where AccountNo = "+acNo+" AND Pin = "+pinNo);
		while(rs.next()) {
			System.out.println("Hi, "+rs.getString(1));
			System.out.println("Press\n 1 Withdraw,\n 2 for Depoist and\n 3 for balance enquiry");
			Scanner scanner=new Scanner(System.in);
			int in=scanner.nextInt();
			if(in==1) {
				withdraw(rs.getString(2),rs.getInt(3),rs.getInt(4) );
			}else if(in==2) {
			depoist(rs.getString(2),rs.getInt(3),rs.getInt(4));
			}else if(in==3) {
				accpountInfo(acNo, pinNo);
			}else {
				System.out.println("Enter a valid key");
				accounts(acNo, pinNo);
			}
			}
		con.close();
	}
	
	static void withdraw(String acNo,int pinNo, int rectDep) throws ClassNotFoundException, SQLException {
		System.out.println("Enter Your Withdraw amount");
		Scanner sc=new Scanner(System.in);
		int withAmt=sc.nextInt();
		int balAmt = 0;
		if(withAmt>rectDep) {
			System.out.println("You hava insufficient Amount to Withdraw\nYour Available balance : "+rectDep);
			withdraw( acNo, pinNo, rectDep);
		}
		else {
		 balAmt=rectDep-withAmt;
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url, username, pwd);
		PreparedStatement ps=con.prepareStatement("update accountDetails2 set Depoist = "+balAmt+" where AccountNo = "+acNo);
		ps.executeUpdate();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select *from accountDetails2 where Accountno = "+acNo+" AND Pin = "+pinNo);
		while(rs.next()) {
			System.out.println("Withdrawn Amount : "+withAmt);
			System.out.println("your Available Balance is : "+rs.getInt(4));
		}
		con.close();
		
		System.out.println("Enter 1 to Withdraw again or Enter any Key to Exit :  ");
		int prNum1=sc.nextInt();
		if(prNum1==1) {
			accounts(acNo, pinNo);
		}else {
			System.out.println("Thank you :)");
			AccountHome.home();
		}
	}
	
	
	static void depoist(String acNo,int pinNo, int rectDep) throws ClassNotFoundException, SQLException {
		
		System.out.println("\nEnter your DEPOIST amount : ");
		Scanner sc=new Scanner(System.in);
		int depAmt=sc.nextInt();
		int totDep=depAmt+rectDep;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con1=DriverManager.getConnection(url, username, pwd);
			PreparedStatement ps=con1.prepareStatement("update accountDetails2 set Depoist = "+totDep+" where AccountNo = "+acNo);
			ps.executeUpdate();
			System.out.println("Added Balance : "+depAmt+"\nTotal Balance : "+totDep);
			con1.close();
			
			System.out.println("Enter 1 to Depoist again or Enter any Key to Exit :  ");
			int prNum1=sc.nextInt();
			if(prNum1==1) {
				accounts(acNo, pinNo);
			}else {
				System.out.println("Thank you :)");
				AccountHome.home();
			}
	}
	
	static void accpountInfo(String acNo,int pinNo) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url, username, pwd);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select *from accountDetails2 where Accountno = "+acNo+" AND Pin = "+pinNo);
		while(rs.next()) {
			System.out.println("ACCOUNT DETAILS ");
			System.out.println("\nNAME : "+rs.getString(1)+"\nACCOUNT NO : "+rs.getString(2)+"\nPIN NO : "
		                                              +rs.getString(3)+"\nAVAILABLE BALANCE : "+rs.getString(4));
		}
		con.close();
		System.out.println("\nPress 1 to go back or Press any Key to Exit");
		Scanner sc=new Scanner(System.in);
		int in=sc.nextInt();
		if(in==1) {
			accounts(acNo, pinNo);
		}else {
			System.out.println("Thank you :)");
			AccountHome.home();
		}
	}
}
