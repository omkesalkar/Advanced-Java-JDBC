package PreparedStatementProgramExample;

import java.sql.*;
import java.util.*;

/*SQLPLUS:  create table product70(Pcode varchar2(15), pName varchar2(25), pPrice number(10,2), pQty int, primary key(Pcode));
*/

public class PreparedStatementProgram 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		try( sc; )
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "om", "2003");
			
			PreparedStatement ps1 = conn.prepareStatement("insert into Product70 values(?,?,?,?)");
			PreparedStatement ps2 = conn.prepareStatement("select * from product70");
			PreparedStatement ps3 = conn.prepareStatement("Select * from product70 where pcode=?");
			PreparedStatement ps4 = conn.prepareStatement("Update product70 set pPrice=?, pQty=pQty+? where pcode=?");
			PreparedStatement ps5 = conn.prepareStatement("Delete from product70 where pcode=?");
			
			
			while(true)
			{
				System.out.println("\n********** Operations choice ***********");
				
				System.out.println("\t1.Add Product"
						+"\n\t2.View All Products"
						+"\n\t3.View Product By Code"
						+"\n\t4.Update Product By Code"
						+"\n\t5.Delete Product By Code"
						+"\n\t6.Exit");
				
				System.out.print("Enter your choice: ");
				int choice = sc.nextInt();
				
				switch(choice)
				{
				case 1:   
					//data loaded to Local variables of main()-method
					System.out.println("==========Product Details==========");
					
					System.out.println("Enter the Prod-Code: ");
					String code = sc.next();
					
					System.out.println("Enter the Prod-Name: ");
					sc.nextLine();
					String name = sc.nextLine();
					
					System.out.println("Enter the Prod-Price: ");
					float price = sc.nextFloat();
					
					System.out.println("Enter the Prod-Qty: ");
					int qty = sc.nextInt();
					
					//Loading data to PreparedStatement Object from Local variables
					ps1.setString(1, code);
					ps1.setString(2, name);
					ps1.setFloat(3, price);
					ps1.setInt(4, qty);
					
					int k = ps1.executeUpdate();
					if(k > 0)
					{
						System.out.println("Product added Successfully....");
					}
					break;
					
				case 2:
					ResultSet rs1 = ps2.executeQuery();
					
					System.out.println("==========Product Details==========");
					
					while(rs1.next())
					{
						System.out.println(rs1.getString(1) 
								+"\t"+ rs1.getString(2) 
								+"\t"+ rs1.getFloat(3) 
								+"\t"+ rs1.getInt(4)
								);
					}// end of loop
					break;
					
				case 3:
					System.out.println("Enter Prod-Code to retrieve product details: ");
					String code1 = sc.next();
					
					ps3.setString(1, code1);
					ResultSet rs2 = ps3.executeQuery();
					
					if(rs2.next())
					{
						System.out.println(rs2.getString(1) 
								+"\t"+ rs2.getString(2) 
								+"\t"+ rs2.getFloat(3) 
								+"\t"+ rs2.getInt(4)
								);
					}
					else
					{
						System.out.println("Invalid Product Code...");
					}
					break;
					
				case 4:
					System.out.println("Enter the Prod-Code to Update Product details: ");
					String code2 = sc.next();
					
					ps3.setString(1, code2);  //fetching exisiting data
					ResultSet rs3 = ps3.executeQuery();
					
					if(rs3.next())
					{
						System.out.println("Existing Product Price: "+ rs3.getFloat(3));
						System.out.println("Enter the New Price: ");
						float nPrice = sc.nextFloat();
						
						System.out.println("Existing Product Qty: "+ rs3.getInt(4));
						System.out.println("Enter the New qty: ");
						int nQty = sc.nextInt();
						
						ps4.setFloat(1, nPrice);
						ps4.setInt(2, nQty);
						ps4.setString(3, code2);
						
						int k1 = ps4.executeUpdate();
						if(k1 > 0)
						{
							System.out.println("Product Updated Successfully...");
						}
						else
						{
							System.out.println("Invalid Prod-Code...");
						}
					}
					break;
					
					
				case 5:
					System.out.println("Eter the Prod-Code to delete product details: ");
					String code3 = sc.next();
					
					ps3.setString(1, code3);
					ResultSet rs4 = ps3.executeQuery();
					
					if(rs4.next())
					{
						ps5.setString(1, code3);
						
						int k2 = ps5.executeUpdate();
						if(k2 > 0)
						{
							System.out.println("Product details deleted successfully...");
						}
						else
						{
							System.out.println("Invalid Prod-Code...");
						}
					}
					break;
					
				case 6: 
					System.out.println("Operation Stopped...");
					System.exit(0);
					
				default: 
					System.out.println("Invalid Choice Entered!!");
				}//end of switch
			}//end of loop
		}//end of try block
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
