package com.revature.beans;

public class Credentials {

	public Credentials() {
		super();
	}

	public Credentials(int loginId, String username, String password, String email) {
		super();
		this.loginId = loginId;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Credentials(int loginId, Employee employee, String username, String password, String email) {
		super();
		this.loginId = loginId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.employee = employee;
	}

	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	protected int loginId;
	protected String username;
	protected String password;
	protected String email;
	protected Employee employee;

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	
	@Override
	public String toString() {
		return "Credentials [loginId= " + loginId + ", username= " + username + ", password= " + password + ", email= "
				+ email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + loginId;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (loginId != other.loginId)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
