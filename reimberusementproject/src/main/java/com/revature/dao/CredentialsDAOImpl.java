package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.beans.Credentials;
import com.revature.beans.Employee;
import com.revature.createLogin.CreateLogin;
import com.revature.util.ConnectionUtil;



public class CredentialsDAOImpl implements CredentialsDAO {
	@Override
	public Credentials createLogin(Employee employee) { 
		CreateLogin login = new CreateLogin();
		String password = login.createPassword();
		String username = login.createUsername(employee.getFirstname(), employee.getLastname());
		String email = login.createEmail(employee.getFirstname(), employee.getLastname());
		Credentials creds = new Credentials(0,employee, username, password, email);
		if(employee != null) {
			try(Connection con = ConnectionUtil.getConnectionFromFile()){
				String sql="INSERT INTO LOGIN (EMP_ID, USERNAME, PASSWORD, EMAIL) VALUES (?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, employee.getEmpId());
				pstmt.setString(2, username);
				pstmt.setString(3, password);
				pstmt.setString(4, email);
						
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return creds;
	}

}
