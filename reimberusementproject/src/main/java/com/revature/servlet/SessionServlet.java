package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.*;


/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ObjectMapper om = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		HttpSession session = request.getSession(false);
		
		System.out.println(session);
		  if (session != null ) { 
			  try { 
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
				  
				  String employeeJSON = om.writeValueAsString(e);
				  response.getWriter().write(employeeJSON);
				  System.out.println(employeeJSON);
				  request.getRequestDispatcher(employeeJSON);
				  
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"session\":null}");
			}
		} else {
			response.getWriter().write("{\"session\":null}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
