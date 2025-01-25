package StudentDetails;

import java.util.*;
import java.sql.*;

public class Student 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		try( sc; )
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:xe", "username", "password");
			
			CallableStatement cs = con.prepareCall("{call studentInfo(?,?,?,?,?,?,?,?,?)}");

      //Filled details
			System.out.print("Enter the Student Id: ");
			int id = sc.nextInt();
			
			System.out.print("Enter the Student RollNo.: ");
			int rollno = sc.nextInt();
			
			System.out.print("Enter the Student Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			
			System.out.print("Enter the Student Branch: ");
			String branch = sc.nextLine();
			
			System.out.print("Enter the HomeNo: ");
			int Hno = sc.nextInt();
			
			System.out.print("Enter the City: ");
			String city = sc.next();
			
			System.out.print("Enter the Pincode: ");
			int pincode = sc.nextInt();
			
			System.out.print("Enter the Mail-ID: ");
			String mailId = sc.next();
			
			System.out.print("Enter the Phone No: ");
			Long phnno = sc.nextLong();
			
			//Loading data to callableStatement Object
			cs.setInt(1, id);
			cs.setInt(2, rollno);
			cs.setString(3, name);
			cs.setString(4, branch);
			cs.setInt(5, Hno);
			cs.setString(6, city);
			cs.setInt(7, pincode);
			cs.setString(8, mailId);
			cs.setLong(9, phnno);
			
			//Procedure executed
			cs.execute();
			System.out.println("Student information successfully inserted in database...");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}  
		//try-catch end here
		
	}  //Main method end here
}
