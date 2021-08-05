package bankpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IsAccountExists {
	static String url="jdbc:mysql://localhost:3306/accounts";
	static String username="root";
	static String pwd="magi";
	
	  static boolean bA;
	   static boolean bP;
	
	 static void isAccountExists(String acNo) throws ClassNotFoundException, SQLException {
		
		 
		 Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, username, pwd);
			Statement s=con.createStatement();
                                                                                            
			ResultSet rs=s.executeQuery("select count(*) from accountDetails2 where AccountNo = "+acNo);
		
			while(rs.next()) {
				if(rs.getInt(1)==0)
				{
					System.out.println(rs.getInt(1));
					bA=false;
				}
				else {
					bA=true;
				}
			}
			con.close();
			
	}
	 
	 static void isPinExists(int aPin) throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, username, pwd);
			Statement s=con.createStatement();
			
			ResultSet rs1=s.executeQuery("select count(*) from accountDetails2 where Pin = "+aPin);
			while(rs1.next()) {
				if(rs1.getInt(1)==0)
				{
					bP=false;
				}
				else {
					
					bP=true;
				}
			}
			
			con.close();	
		 
	 }

}
