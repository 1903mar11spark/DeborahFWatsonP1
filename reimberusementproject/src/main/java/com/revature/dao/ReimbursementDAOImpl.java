package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public boolean createReimbursement(Reimbursement reimbursement) {
		boolean success = false;
		if (reimbursement != null) {
			try (Connection con = ConnectionUtil.getConnectionFromFile()) {
				String sql = "INSERT INTO REIMBURSEMENT (EMP_ID,MANAGER_ID,TYPE,AMOUNT,STATUS,RECEIPT) VALUES (?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, reimbursement.getEmployee().getEmpId());
				pstmt.setInt(2, reimbursement.getManager().getEmpId());
				pstmt.setString(3, reimbursement.getType());
				pstmt.setDouble(4, reimbursement.getAmount());
				pstmt.setString(5, reimbursement.getStatus());
				pstmt.setString(6, reimbursement.getReceipt());
				if (pstmt.executeUpdate() > 0) {
					success = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	@Override
	public List<Reimbursement> getAllReimbursement(Employee manager) {// EMPLOYEE IS MANAGER!!
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			String sql = "SELECT R.REIMBURSEMENT_ID, R.EMP_ID,R.TYPE,R.AMOUNT,R.STATUS, "
					+ "R.RECEIPT,E.FIRST_NAME,E.LAST_NAME, E.DATE_OF_BIRTH, E.DATE_OF_HIRE "
					+ "FROM REIMBURSEMENT R INNER JOIN EMPLOYEE E ON R.EMP_ID=E.EMP_ID WHERE R.MANAGER_ID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, manager.getEmpId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				int empId = rs.getInt("EMP_ID");
				String type = rs.getString("TYPE");
				Double amount = rs.getDouble("AMOUNT");
				String status = rs.getString("STATUS");
				String receipt = rs.getString("RECEIPT");
				String firstname = rs.getString("FIRST_NAME");
				String lastname = rs.getString("LAST_NAME");
				LocalDate birthdate = rs.getDate("DATE_OF_BIRTH").toLocalDate();
				LocalDate hiredate = rs.getDate("DATE_OF_HIRE").toLocalDate();
				LocalDate timeSubmitted = rs.getDate("TIME_SUBMITTED").toLocalDate();
				LocalDate timeReviewed = rs.getDate("TIME_REVIEWED").toLocalDate();
				Employee employee = new Employee(empId, firstname, lastname,
						new Department(manager.getDepartment().getDeptId(), manager.getDepartment().getDeptName(),
								manager),
						birthdate, hiredate);
				Reimbursement reimbursement = new Reimbursement(reimbursementId, employee, manager, type, amount,
						status, receipt, timeSubmitted, timeReviewed);
				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimbursement, String status) {
		boolean success = false;
		if (reimbursement != null) {
			try (Connection con = ConnectionUtil.getConnectionFromFile()) {
				String sql = "UPDATE REIMBURSEMENT SET STATUS = ? WHERE EMP_ID=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, status);
				pstmt.setInt(2, reimbursement.getEmployee().getEmpId());
				if (pstmt.executeUpdate() > 0) {
					success = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

}
