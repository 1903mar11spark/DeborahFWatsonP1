package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class ViewReimbursementServlet
 */
public class ViewReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReimbursementService reim = new ReimbursementServiceImpl();
	Reimbursement reimbursement = new Reimbursement();
	ObjectMapper om = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReimbursementServlet() {
        super();
    	om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("html/veiwReimbursement.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			int empId = Integer.parseInt(session.getAttribute("empId").toString());
			  String firstname = session.getAttribute("firstname").toString(); 
			  String lastname = session.getAttribute("lastname").toString();
			  int deptId = Integer.parseInt(session.getAttribute("deptmentId").toString()); 
			  String deptName = session.getAttribute("departmentName").toString(); 
			  LocalDate dob = LocalDate.parse(session.getAttribute("birthdate").toString()); 
			  LocalDate doh = LocalDate.parse(session.getAttribute("hiredate").toString());
			  
			  int managerId = Integer.parseInt(session.getAttribute("managerId").toString());
			  String managerFirstname = session.getAttribute("managerFirstname").toString(); 
			  String managerLastname = session.getAttribute("managerLastname").toString();
			  LocalDate managerDOB = LocalDate.parse(session.getAttribute("managerDOB").toString()); 
			  LocalDate managerDOH = LocalDate.parse(session.getAttribute("managerDOH").toString());
			  
			  Employee manager = new Employee(managerId, managerFirstname, managerLastname,new Department(deptId, deptName,null), managerDOB, managerDOH);
			  
			  Employee e = new Employee(empId, firstname, lastname, new Department(deptId, deptName, manager), dob, doh);
			  
			  
			 List<Reimbursement> reimList = reim.getAllReimbursement(manager);
			 String reimString = om.writeValueAsString(reimList);
			 response.getWriter().write(reimString);
			  request.getRequestDispatcher(reimString);
			 
			  
			  
		}
	}

}
