package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.revature.beans.*;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeService emp = new EmployeeServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("html/login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Credentials creds = new Credentials(request.getParameter("username"), request.getParameter("password"));

		Employee e = emp.getEmployee(creds);
		System.out.println(e); 
		if (e != null) {

			//set user information as session attributes (not request attributes!)
			session.setAttribute("empId", e.getEmpId());
			session.setAttribute("firstname", e.getFirstname());
			session.setAttribute("lastname", e.getLastname());
			session.setAttribute("deptmentId", e.getDepartment().getDeptId());
			session.setAttribute("departmentName", e.getDepartment().getDeptName());
			session.setAttribute("managerId", e.getDepartment().getManager().getEmpId());//Employee
			session.setAttribute("managerFirstname", e.getDepartment().getManager().getFirstname());
			session.setAttribute("managerLastname", e.getDepartment().getManager().getLastname());
			session.setAttribute("managerDOB", e.getDepartment().getManager().getBirthdate());
			session.setAttribute("managerDOH", e.getDepartment().getManager().getHiredate());
			session.setAttribute("birthdate", e.getBirthdate()); 
			session.setAttribute("hiredate", e.getHiredate());
			session.setAttribute("problem", null);
			//redirect user to profile page if authenticated
			if(e.getDepartment().getManager().getEmpId() == e.getEmpId()) {
				response.sendRedirect("managerprofile");
			}else {
				response.sendRedirect("profile");
			}
		} else {
			session.setAttribute("problem", "invalid credentials");
			//otherwise redirect to login page
			response.sendRedirect("login");
		}

	}
}
