package com.revature.dao;

import java.util.List;

import com.revature.beans.Credentials;
import com.revature.beans.Employee;



public interface EmployeeDAO {
	
	public boolean createEmployee(Employee employee);
	public boolean deleteEmployee(Employee empoloyee);
	public Employee getEmployee(Credentials creds);
	public Employee getEmployeeById(int id);
	public List<Employee> getAllEmployee(Employee manager);//manager login
	public boolean updateEmployee(Credentials creds);//insert login

}
