package com.revature.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		
		try {Connection con = ConnectionUtil.getConnectionFromFile();
			System.out.println(con);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}

}
