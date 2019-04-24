package com.revature.beans;


public class Department {
	
	public Department() {
		super();
	}
	
	public Department(int deptId, String deptName,Employee manager ) {
		super();
		this.deptId =deptId;
		this.deptName = deptName;
		this.manager=manager;
	}
	
	public Department(int deptId,String deptName ) {
		super();
		this.deptId=deptId;
		this.deptName = deptName;
	}
	
	protected int deptId;
	protected String deptName;
	protected Employee manager;//manager
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", employee=" + manager + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptId;
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
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
		Department other = (Department) obj;
		if (deptId != other.deptId)
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		return true;
	}
	

}
