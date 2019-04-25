package com.revature.service;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;

public interface ReimbursementService {
	
	ReimbursementDAO reimbursement= new ReimbursementDAOImpl();
	public boolean createReimbursement(Reimbursement reimbursement);
	public List<Reimbursement> getAllReimbursement(Employee manager);
	public boolean updateReimbursement(Reimbursement reimbursement, String status);

}
