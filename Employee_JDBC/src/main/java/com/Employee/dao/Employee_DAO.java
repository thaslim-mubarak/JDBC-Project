package com.Employee.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.security.auth.callback.ConfirmationCallback;

import com.Employee.dto.Employee;

public class Employee_DAO {

	private static Connection con = null;

	static {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employee?user=postgres&password=root");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Problem with driver loading");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with getting the Connection");
		}
		finally {
			try {
				CallableStatement call = con.prepareCall("select count_of_employees()");
				ResultSet rs = call.executeQuery();
				rs.next();
				
				int count = rs.getInt(1);
				if(count != 0) {
					System.out.println("Existing employee list:\n");
					allEmployees();
				}
			} catch (SQLException e) {
				System.out.println("Problem with function call");
				e.printStackTrace();
			}
		}
	}

	static Scanner sc = new Scanner(System.in);

	private Employee createEmployee() {

		System.out.println("Enter the employee's ID:");
		int id = sc.nextInt();
		System.out.println("Enter the employee's first Name:");
		String f_name = sc.next();
		System.out.println("Enter the employee's last Name:");
		String l_name = sc.next();
		System.out.println("Enter the employee's designation:");
		String des  = sc.next();
		System.out.println("Enter the employee's salary:");
		double sal = sc.nextDouble();

		return new Employee(id, f_name, l_name, des, sal);
	}

	public void addEmployee() {
		System.out.println("How many employee records you want to be added?");
		int count = sc.nextInt();

		PreparedStatement stmt = null;

		for (int i = 0; i < count; i++) {
			Employee emp = createEmployee();

			try {
				//preparing statement and adding to the batch
				stmt = con.prepareStatement("insert into emp values(?, ?, ?, ?, ?)");
				stmt.setInt(1, emp.getId());
				stmt.setString(2, emp.getFirst_name());
				stmt.setString(3, emp.getLast_name());
				stmt.setString(4, emp.getDesignation());
				stmt.setDouble(5, emp.getSal());
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Problem with creating the statement");
			}
		}
	}
	
	private static void allEmployees() {
		
		
		String query = "select * from emp";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with connection");
		}
		
		try {
			while (rs.next()) {
				System.out.println("ID: "+rs.getInt(1));
				System.out.println("First name: "+rs.getString(2));
				System.out.println("Last name: "+rs.getString(3));
				System.out.println("Designation: "+rs.getString(4));
				System.out.println("Salary: "+rs.getDouble(5));
				System.out.println();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem while iterating");
		}
	}

	public boolean updateDesignation() {

		System.out.println("Enter the employee id to be updated:");
		int id = sc.nextInt();
		System.out.println("Enter the designation to be updated:");
		String des = sc.next();
		String query = "update emp set designation="+"'"+des+"'"+"where id="+id;

		Statement stmt = null;
		int confirmation = 0;

		try {
			con.createStatement();
			stmt = con.createStatement();
			confirmation = stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with getting connection");
		}

		return (confirmation == 1)?true:false;
	}

	public boolean updateSalary() {

		System.out.println("Enter the employee id to be updated:");
		int id = sc.nextInt();
		System.out.println("Enter the salary to be updated:");
		double sal = sc.nextDouble();
		String query = "update emp set salary="+sal+" where id="+id;

		Statement stmt = null;
		int confirmation = 0;

		try {
			con.createStatement();
			stmt = con.createStatement();
			confirmation = stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with getting connection");
		}

		return (confirmation == 1)?true:false;
	}

	public void findEmpFname() {

		System.out.println("Enter the employee's first name:");
		String f_name = sc.next();
		String query = "select * from emp where first_name="+"'"+f_name+"'";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with connection");
		}

		try {
			while (rs.next()) {
				System.out.println("ID: "+rs.getInt(1));
				System.out.println("First name: "+rs.getString(2));
				System.out.println("Last name: "+rs.getString(3));
				System.out.println("Designation: "+rs.getString(4));
				System.out.println("Salary: "+rs.getDouble(5));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem while iterating");
		}


	}

	public void findEmpSal() {

		System.out.println("Enter the start range:");
		double start = sc.nextDouble();
		System.out.println("Enter the end range:");
		double end = sc.nextDouble();
		String query = "select * from emp where salary between "+start+" and "+end;

		Statement stmt = null;

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			try {
				while (rs.next()) {
					System.out.println("ID: "+rs.getInt(1));
					System.out.println("First name: "+rs.getString(2));
					System.out.println("Last name: "+rs.getString(3));
					System.out.println("Designation: "+rs.getString(4));
					System.out.println("Salary: "+rs.getDouble(5));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Problem while iterating");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with connection");
		}

	}

	public boolean deleteRecord() {

		System.out.println("Enter id of the employee to be removed:");
		int id = sc.nextInt();

		String query = "delete from emp where id="+id;

		Statement stmt = null;
		boolean res = false;
		try {
			stmt = con.createStatement();
			res = stmt.execute(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with statement");
		}

		return (res)?true:false;
	}

	private void vanish() {

		String query = "delete from emp";

		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			System.out.println("All the records were deleted");
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem with statement");
		}
	}
	
	

}
