package com.revature.service;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;

public class ReimbursementServiceImpl implements ReimbursementService{
	
	ReimbursementDAO reim = new ReimbursementDAOImpl();
	
	public boolean createReimbursement(Reimbursement reimbursement) {
		return reim.createReimbursement(reimbursement);
	}
	public List<Reimbursement> getAllReimbursement(Employee manager){
		return reim.getAllReimbursement(manager);
	}
	
	public boolean updateReimbursement(Reimbursement reimbursement, String status) {
		return reim.updateReimbursement(reimbursement, status);
	}

}
