package com.revature.service;

import java.util.List;

import com.revature.beans.*;

public interface EmployeeService {
	
	public boolean createEmployee(Employee employee);
	public boolean deleteEmployee(Employee empoloyee);
	public Employee getEmployee(Credentials creds);
	public Employee getEmployeeById(int id);
	public List<Employee> getAllEmployee(Employee employee);//manager login
	public boolean updateEmployee(Credentials creds);//insert login

}
