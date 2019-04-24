package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;



public interface ReimbursementDAO {
	
	public boolean createReimbursement(Reimbursement reimbursement);
	public List<Reimbursement> getAllReimbursement(Employee manager);
	public boolean updateReimbursement(Reimbursement reimbursement, String status);

}
