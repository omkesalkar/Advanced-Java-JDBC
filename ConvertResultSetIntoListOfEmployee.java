npackage Jan08_ExamQuestions;

import java.util.*;
import java.sql.*;

/*Write a Java program to perform below operations:
Note:
-create table emp_info
-Table's properties: id, name, salary, and department.
-Insert 5 records from SQL command line.
Query:- create table emp_info(id int, name varchar2(20), salary int, department varchar2(15));

Establish a JDBC connection to Oracle.
Retrieve the employee details from the employees table.
->Convert the ResultSet into a list of Employee objects.
->Filter employees whose salary is greater than 50,000.
->Sort the employees by salary in descending order.
->Limit the results to the top 5 employees based on salary.
->Print the list of top 5 employees with their details.
*/

class Employee 
{
    int id;
    String name;
    int salary;
    String department;

    public Employee(int id, String name, int salary, String department) 
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() 
    {
        return "EmpId: "+id+", EmpName: " +name+ ", EmpSalary: " +salary+ ", EmpDepartment: " +department+ " ";
    }
}

public class ConvertResultSetIntoListOfEmployee 
{
	public static void main(String[] args) 
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "username", "password");
		
			Statement stm1 = conn.createStatement();
			
			ResultSet rs = stm1.executeQuery("Select * from emp_info");
			
			//Convert the ResultSet into a list of Employee objects.
			List<Employee> employees = new ArrayList<>();
           		 while(rs.next()) 
            		{		
                		int id = rs.getInt(1);
                		String name = rs.getString(2);
                		int salary = rs.getInt(3);
                		String department = rs.getString(4);
                		employees.add(new Employee(id, name, salary, department));
            		}
            
//          		//Filter employees whose salary is greater than 50,000.
            		employees.stream().filter(emp -> emp.salary>50000).forEach(System.out::println);
			
//         		 //Sort the employees by salary in descending order.
			employees.stream().map(emp -> emp.salary).sorted((s1,s2) -> s2.compareTo(s1)).forEach(System.out::println);
			
//			//Limit the results to the top 5 employees based on salary.
			employees.stream().map(emp -> emp.salary).limit(5).sorted((s1,s2) -> s2.compareTo(s1)).forEach(System.out::println);
			
//			//Print the list of top 5 employees with their details.
			employees.stream().map(emp -> emp.toString()).limit(5).forEach(System.out::println);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
