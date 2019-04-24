package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Credentials;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	public boolean createEmployee(Employee employee) {// ALSO CREATE LOGIN!!
		boolean success = false;
		if (employee != null) {
			try (Connection con = ConnectionUtil.getConnectionFromFile()) {
				String sql = "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,DATE_OF_BIRTH,DATE_OF_HIRE) VALUES (?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, employee.getFirstname());
				pstmt.setString(2, employee.getLastname());
				pstmt.setDate(3, Date.valueOf(employee.getBirthdate()));
				pstmt.setDate(4, Date.valueOf(employee.getHiredate()));
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

	public boolean deleteEmployee(Employee employee) {
		boolean success = false;
		if (employee != null) {
			try (Connection con = ConnectionUtil.getConnectionFromFile()) {
				String sql = "DELETE FROM EMPLOYEE WHERE USER_ID = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, employee.getEmpId());
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

	public Employee getEmployee(Credentials creds) {
		Employee employee = new Employee();
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			String sql = "SELECT E.EMP_ID, E.FIRST_NAME,E.LAST_NAME,E.DEPARTMENT_ID, D.DEPARTMENT_NAME, D.DEPT_MANAGER_ID,"
					+ "E.DATE_OF_BIRTH,E.DATE_OF_HIRE "
					+ "FROM EMPLOYEE E INNER JOIN DEPARTMENT D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID "
					+ "LEFT JOIN LOGIN L ON E.LOGIN_ID = L.LOGIN_ID WHERE USERNAME=? AND PASSWORD=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, creds.getUsername());
			pstmt.setString(2, creds.getPassword());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String firstname = rs.getString("FIRST_NAME");
				String lastname = rs.getString("LAST_NAME");
				int deptId = rs.getInt("DEPARTMENT_ID");
				String deptName = rs.getString("DEPARTMENT_NAME");
				int deptManagerId = rs.getInt("DEPT_MANAGER_ID");
				LocalDate birthdate = rs.getDate("DATE_OF_BIRTH").toLocalDate();
				LocalDate hiredate = rs.getDate("DATE_OF_HIRE").toLocalDate();
				Employee manager = getEmployeeById(deptManagerId);
				employee = new Employee(empId, firstname, lastname,creds, new Department(deptId,deptName,manager), birthdate, hiredate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employee;
	}
	public Employee getEmployeeById(int id) {
		Employee employee = new Employee();
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			String sql = "SELECT E.EMP_ID, E.FIRST_NAME, E.LAST_NAME, L.USERNAME,L.PASSWORD, E.DEPARTMENT_ID, D.DEPARTMENT_NAME, E.DATE_OF_BIRTH, E.DATE_OF_HIRE" + 
			" FROM EMPLOYEE E INNER JOIN DEPARTMENT D ON E.DEPARTMENT_ID=D.DEPARTMENT_ID LEFT JOIN LOGIN L ON E.LOGIN_ID = L.LOGIN_ID WHERE E.EMP_ID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String firstname = rs.getString("FIRST_NAME");
				String lastname = rs.getString("LAST_NAME");
				String username = rs.getString("USERNAME");
				String password = rs.getString("password");
				int deptId = rs.getInt("DEPARTMENT_ID");
				String deptName = rs.getString("DEPARTMENT_NAME");
				LocalDate birthdate = rs.getDate("DATE_OF_BIRTH").toLocalDate();
				LocalDate hiredate = rs.getDate("DATE_OF_HIRE").toLocalDate();
				Credentials creds = new Credentials(username, password);
				employee = new Employee(empId, firstname, lastname,creds, new Department(deptId, deptName), birthdate, hiredate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> getAllEmployee(Employee manager) {//manager only
		List<Employee> employees = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			String sql = "SELECT E.EMP_ID, E.FIRST_NAME,E.LAST_NAME, D.DEPARTMENT_ID, D.DEPARTMENT_NAME,D.DEPT_MANAGER_ID, E.DATE_OF_BIRTH,E.DATE_OF_HIRE FROM EMPLOYEE E " 
					+ "INNER JOIN DEPARTMENT D ON D.DEPARTMENT_ID=E.DEPARTMENT_ID WHERE D.DEPARTMENT_ID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, manager.getEmpId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String firstname = rs.getString("FIRST_NAME");
				String lastname = rs.getString("LAST_NAME");
				int deptId = rs.getInt("DEPARTMENT_ID");
				String deptName = rs.getString("DEPARTMENT_NAME");
				LocalDate birthdate = rs.getDate("DATE_OF_BIRTH").toLocalDate();
				LocalDate hiredate = rs.getDate("DATE_OF_HIRE").toLocalDate();
				employees.add(new Employee(empId, firstname, lastname, new Department(deptId, deptName, manager), birthdate,
						hiredate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public boolean updateEmployee(Credentials creds) {// after login as been updated
		boolean success = false;
		if (creds != null) {
			try (Connection con = ConnectionUtil.getConnectionFromFile()) {
				String sql = "UPDATE EMPLOYEE SET LOGIN_ID = ? WHERE EMP_ID=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, creds.getLoginId());
				pstmt.setInt(2, creds.getEmployee().getEmpId());
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
