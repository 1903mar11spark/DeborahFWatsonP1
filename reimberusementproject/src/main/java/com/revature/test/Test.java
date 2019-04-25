package com.revature.test;

import java.time.LocalDate;
import java.time.Month;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;

public class Test {
	
	static EmployeeDAO emp = new EmployeeDAOImpl();
	static ReimbursementDAO reim = new ReimbursementDAOImpl();
	
	public static void main(String[] args) {
		//String username = "sally.willson";
		//String password = "4567";
		
		//Credentials creds = new Credentials(username, password);
		
		//System.out.println(emp.getEmployee(creds));
		//System.out.println(emp.getEmployeeById(1));
		Employee manager = emp.getEmployeeById(1);
		Employee e = emp.getEmployeeById(3);

		Reimbursement creim = new Reimbursement(0, e, manager, "travel", 7548.20, "submitted", "url", LocalDate.of(1994, Month.FEBRUARY, 28), LocalDate.of(1994, Month.FEBRUARY, 12));
		
		boolean success = reim.createReimbursement(creim);
		System.out.println(success);
		
	}

}
