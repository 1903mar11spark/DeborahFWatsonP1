package com.revature.beans;

import java.time.LocalDate;



public class Employee {
	public Employee() {
		super();
	}
	
	public Employee(int empId, String firstname, String lastname, LocalDate bithdate, LocalDate hiredate) {
		super();
		this.empId = empId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = bithdate;
		this.hiredate = hiredate;
	}

	
	public Employee(int empId, String firstname, String lastname, Department department, LocalDate bithdate, LocalDate hiredate) {
		super();
		this.empId = empId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.department = department;
		this.birthdate = bithdate;
		this.hiredate = hiredate;
	}
	
	public Employee(int empId, String firstname, String lastname,Credentials creds, Department department, LocalDate birthdate, LocalDate hiredate) {
		super();
		this.empId = empId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.creds=creds;
		this.department = department;
		this.birthdate = birthdate;
		this.hiredate = hiredate;
	}
	protected int empId;
	protected String firstname;
	protected String lastname;
	protected Credentials creds;
	protected Department department;
	protected LocalDate birthdate;
	protected LocalDate hiredate;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Credentials getCreds() {
		return creds;
	}
	public void setLogin(Credentials creds) {
		this.creds=creds;
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public LocalDate getHiredate() {
		return hiredate;
	}
	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}

	
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstname=" + firstname + ", lastname=" + lastname 
				+ ", Department=" + department + ", bithdate=" + birthdate + ", hiredate=" + hiredate + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((creds == null) ? 0 : creds.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + empId;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
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
		Employee other = (Employee) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (creds == null) {
			if (other.creds != null)
				return false;
		} else if (!creds.equals(other.creds))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (empId != other.empId)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (hiredate == null) {
			if (other.hiredate != null)
				return false;
		} else if (!hiredate.equals(other.hiredate))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	
	

}

