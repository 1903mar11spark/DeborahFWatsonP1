package com.revature.test;

import com.revature.beans.Credentials;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;

public class Test {
	
	static EmployeeDAO emp = new EmployeeDAOImpl();
	
	public static void main(String[] args) {
		String username = "sally.willson";
		String password = "4567";
		
		Credentials creds = new Credentials(username, password);
		
		//System.out.println(emp.getEmployee(creds));
		System.out.println(emp.getEmployeeById(1));
		
	}

}
