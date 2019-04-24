package com.revature.service;

import java.util.List;

import com.revature.beans.Credentials;
import com.revature.beans.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDAO emp = new EmployeeDAOImpl();

	@Override
	public boolean createEmployee(Employee employee) {
		return emp.createEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee empoloyee) {//manager
		return emp.deleteEmployee(empoloyee);
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		return emp.getEmployeeById(id);
	}

	@Override
	public Employee getEmployee(Credentials creds) {
		return emp.getEmployee(creds);
	}

	@Override
	public List<Employee> getAllEmployee(Employee employee) {	//manager	
		return emp.getAllEmployee(employee);
	}

	@Override
	public boolean updateEmployee(Credentials creds) {
		return emp.updateEmployee(creds);
	}


}
