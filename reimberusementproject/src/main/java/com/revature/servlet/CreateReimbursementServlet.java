package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

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
 * Servlet implementation class CreateReimbursementServlet
 */
public class CreateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ReimbursementService createReimbursement = new ReimbursementServiceImpl();
	Reimbursement reimbursement = new Reimbursement();
	ObjectMapper om = new ObjectMapper();
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateReimbursementServlet() {
		super();
		om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("html/createReimbursement.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null ) { 
			try {
				//geting employee
				int empId = Integer.parseInt(session.getAttribute("empId").toString());
				String firstname = session.getAttribute("firstname").toString(); 
				String lastname = session.getAttribute("lastname").toString();
				int deptId = Integer.parseInt(session.getAttribute("deptmentId").toString()); 
				String deptName = session.getAttribute("departmentName").toString(); 
				LocalDate dob = LocalDate.parse(session.getAttribute("birthdate").toString()); 
				LocalDate doh = LocalDate.parse(session.getAttribute("hiredate").toString());


				//getting manager of employee
				int managerId = Integer.parseInt(session.getAttribute("managerId").toString());
				String managerFirstname = session.getAttribute("managerFirstname").toString(); 
				String managerLastname = session.getAttribute("managerLastname").toString();
				LocalDate managerDOB = LocalDate.parse(session.getAttribute("managerDOB").toString()); 
				LocalDate managerDOH = LocalDate.parse(session.getAttribute("managerDOH").toString());

				//getting reimbursement data
				String type = request.getParameter("type");
				Double amount = Double.parseDouble(request.getParameter("amount"));
				//LocalDate dateSubmitted = null;
				String status="submitted";
				String recipt = "url";
				//LocalDate reviewed = null;

				Employee manager = new Employee(managerId, managerFirstname, managerLastname,new Department(deptId, deptName,null), managerDOB, managerDOH);

				Employee e = new Employee(empId, firstname, lastname, new Department(deptId, deptName, manager), dob, doh);

				reimbursement = new Reimbursement(0, e, manager, type, amount, status, recipt, LocalDate.of(1994, Month.FEBRUARY, 28), LocalDate.of(1994, Month.FEBRUARY, 12));

				boolean success = createReimbursement.createReimbursement(reimbursement);
				System.out.println(success);
				response.sendRedirect("profile");
				
					
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("out of the Request loop mi amiga");
				response.sendRedirect("createreimbursement");
			}
		}else {
			response.sendRedirect("login");
		}
	}
}
