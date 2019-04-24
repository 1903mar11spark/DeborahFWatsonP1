package com.revature.beans;

import java.time.LocalDate;



public class Reimbursement {
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int reimbursementId, Employee employee,Employee manager, String type, double amount,String status, String receipt,LocalDate dateSubmitted, LocalDate dateReviewed) {
		super();
		this.reimbursementId = reimbursementId;
		this.employee = employee;
		this.manager=manager;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.receipt = receipt;
		this.dateSubmitted = dateSubmitted;
		this.dataReviewed=dateReviewed;
	}



	protected int reimbursementId;
	protected Employee employee;
	protected Employee manager;
	protected String type;
	protected double amount;
	protected String status;
	protected String receipt;
	protected LocalDate dateSubmitted;
	protected LocalDate dataReviewed;
	
	




	public int getReimbursementId() {
		return reimbursementId;
	}



	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}



	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}


	public String getReceipt() {
		return receipt;
	}



	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}



	public LocalDate getDateSubmitted() {
		return dateSubmitted;
	}



	public void setDateSubmitted(LocalDate dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}



	public LocalDate getDataReviewed() {
		return dataReviewed;
	}



	public void setDataReviewed(LocalDate dataReviewed) {
		this.dataReviewed = dataReviewed;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employee=" + employee + ", manager=" + manager
				+ ", type=" + type + ", amount=" + amount + ", status=" + status + ", receipt=" + receipt
				+ ", dateSubmitted=" + dateSubmitted + ", dataReviewed=" + dataReviewed + "]";
	}
	
	

}
