package com.Employee.dco;

import java.util.Scanner;

import com.Employee.dao.Employee_DAO;
import com.Employee.dto.Employee;

public class Employee_Controller {
	
//	static Employee_DAO dao = new Employee_DAO();

	public static void main(String[] args) {
		System.out.println("WELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM !\nHow can I help you ... ?\nEnter your choice:");
		
		Employee_DAO dao = new Employee_DAO();
		
		boolean loop = true;
		while(loop) {
			System.out.println("1. Add Employee(s)\n2. Update an employee's designation(using id)\n3. Update an employee's salary(using id)\n4. Fetch the employee details(using range of salary)\n5. Fetch the employee details(using first name)\n6. Remove an employee(using id)\n7. Exit");
			int choice = new Scanner(System.in).nextInt();
			switch (choice) {
				case 1:{
					dao.addEmployee();
					break;
				}
				case 2:{
					dao.updateDesignation();
					break;
				}
				case 3:{
					dao.updateSalary();
					break;
				}
				case 4:{
					dao.findEmpSal();
					break;
				}
				case 5:{
					dao.findEmpFname();
					break;
				}
				case 6:{
					dao.deleteRecord();
					break;
				}
				case 7:{
					loop = false;
					break;
				}
				default: {
					System.out.println("Enter the correct input");
				}
				
			}
		}
		
	}

}
