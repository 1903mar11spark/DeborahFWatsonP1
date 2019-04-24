package com.revature.dao;

import com.revature.beans.Credentials;
import com.revature.beans.Employee;

public interface CredentialsDAO {
	
	public Credentials createLogin(Employee employee);

}
