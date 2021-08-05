package bankpro;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountHome {
static int prnum;
	
	static void home() throws ClassNotFoundException, SQLException{
	System.out.println("Do you have an account then PRESS 1");
	System.out.println("If you Don't have an account then PRESS 2");
	Scanner sc=new Scanner(System.in);
	prnum=sc.nextInt();
	if(prnum==1) {
		LoginSection.login();
	}else if(prnum==2){
		RegisterSection.register();
	}else {
		System.out.println("You Entered a WRONG number !!!");
		home();
	}
	}
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	home();
}
}
