import java.util.*;
import java.sql.*;

public class Employee_Info 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"," username "," password ");  //connect to oracle database
			Statement stm = con.createStatement();
			//ResultSet rs = stm.executeQuery("Select * from employee");
			
			while(true)
			{
				System.out.println("\n\t-------------------------- Select Operation --------------------------\n");
				System.out.println("\t1. Insert data into Employee Table");
				System.out.println("\t2. Update employee salary, empMailId and empPhNo with the help of employeeId");
				System.out.println("\t3. Find those employee whose name starts with 'A'");
				System.out.println("\t4. Count how many employee record present in table");
				System.out.println("\t5. Delete employee who is getting maximum salary");
				System.out.println("\t6. Show all employee");
				System.out.println("\t7. Exit\n");
				
				System.out.print("Enter your choice (1/2/3/4/5/6/7): ");
				int choice = sc.nextInt();
				
				switch(choice)
				{
				case 1: 
					System.out.print("\nEnter the employee ID: ");
					int eId = sc.nextInt();
					
					System.out.print("Enter the employee Name: ");
					sc.nextLine();
					String eName = sc.nextLine();
					
					System.out.print("Enter the employee Salary: ");
					double eSalary = sc.nextDouble();
					
					System.out.print("Enter the employee Address: ");
					sc.nextLine();
					String eAddress = sc.nextLine();
					
					System.out.print("Enter the employee Email-ID: ");
					String eMailId = sc.nextLine();
					
					System.out.print("Enter the employee phone no.: ");
					long ePhNo = sc.nextLong();
					
					 int insertEmp = stm.executeUpdate("INSERT INTO employee (empId, empName, empSalary, empAddress, empMailId, empPhNo) VALUES (" +
                             eId + ", '" + eName + "', " + eSalary + ", '" + eAddress + "', '" + eMailId + "', " + ePhNo + ")");
					
					 if (insertEmp > 0) {
                         System.out.println("Employee details inserted successfully.");
                     } else {
                         System.out.println("Failed to insert employee data.");
                     }
					break;
					
					
				case 2: 
					System.out.println("\nEnter the Employee-Id to perform update operation: ");
					int uid = sc.nextInt();
					
					ResultSet rs2 = stm.executeQuery("select * from employee where empId= '"+ uid +"'");
					if(rs2.next())
					{
						System.out.println("Existing Salary: "+ rs2.getDouble(3));
						System.out.print("Enter the Updated Salary: ");
						double uSalary = sc.nextDouble();
						
						System.out.println("Existing Mail ID: "+ rs2.getString(5));
						System.out.print("Enter the Updated Email ID: ");
						sc.nextLine();
						String uEmail = sc.nextLine();
						
						System.out.println("Existing Phn no. : "+ rs2.getString(6));
						System.out.print("Enter the Updated phone no.: ");
						long uPhnNo = sc.nextLong();
						
						int updateEmp = stm.executeUpdate("update employee set empSalary='"+ uSalary +"',empEmailId='"+ uEmail +"',empPhNo='"+ uPhnNo +"'");
						
						if (updateEmp > 0) 
						{
                            System.out.println("Employee details updated successfully.");
                        } 
						else 
						{
                            System.out.println("No employee found with the given ID.");
                        }
					}
					break;
					
					
				case 3: 
					ResultSet rs3 = stm.executeQuery("select * from employee where empName LIKE 'A%'");
					 boolean found = false;

                     while (rs3.next()) 
                     {
                         found = true;
                         System.out.println("Employee ID: " + rs3.getInt(1) +
                                 ", Name: " + rs3.getString(2) +
                                 ", Salary: " + rs3.getDouble(3));
                     }
                     
                     if (!found) 
                     {
                         System.out.println("No employees found whose name starts with 'A'.");
                     }
					break;
					
				case 4:
					ResultSet rs4 = stm.executeQuery("select COUNT(*) from employee");
					
					if(rs4.next())
					{
						System.out.println("Total number of employees: "+ rs4.getInt(1));
					}
					break;
					
				case 5:
					int deleteEmp = stm.executeUpdate("delete from employee where empSalary = (select MAX(empSalary) from employee)");
					
					if (deleteEmp > 0) 
					{
                        System.out.println("Employee with the highest salary deleted successfully.");
                    } else 
                    {
                    	System.out.println("No employee found to delete.");
                    }
					break;
					
				case 6:
					ResultSet rs6 = stm.executeQuery("Select * from employee");
					
					System.out.println("\n--------------------------- Employee Details -----------------------------");
					while(rs6.next())
					{
						System.out.println(rs6.getInt(1) + "\t"
								+rs6.getString(2) +"\t"
								+rs6.getDouble(3) +"\t"
								+rs6.getString(4) +"\t"
								+rs6.getString(5) +"\t"
								+rs6.getLong(6));
					}
					
					break;
					
				case 7:
					System.out.println("Exiting... ");
					Thread.sleep(1000);
					System.out.println("Exited Successfully!!");
					System.exit(0);
					break;
					
				default:
                    System.out.println("Invalid choice. Please try again.");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
