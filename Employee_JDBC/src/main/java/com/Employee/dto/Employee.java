package com.Employee.dto;

public class Employee {

	//states
	
	private int id;
	private String first_name;
	private String last_name;
	private String designation;
	private double sal;
	
	//default constructor
	public Employee(){
		
	}
	
	//Parameterized Constructor
	public Employee(int id, String first_name, String last_name, String designation, double sal) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.designation = designation;
		this.sal = sal;
	}
	
	
	
	//Getters and Setters
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	
}
