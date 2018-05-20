package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.*;
import com.revature.domain.Employee;

/**
 * Servlet implementation class GetAllEmployeesServlet
 */
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllEmployeesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ManagerDao md = new ManagerDaoImpl();
		response.setContentType("text/plain");
		PrintWriter wd = response.getWriter();
		List <Employee> employeeList = md.getAllEmployees();
		ObjectMapper om = new ObjectMapper();
		wd.println(om.writeValueAsString(employeeList));
		
		/*wd.println("<!DOCTYPE html>");
		wd.println("<html>");
		wd.println("<head>");
		wd.println("<title>Employees</title>");
		wd.println("<style>table, th, td {border: 2px solid black; border-collapse: collapse} table {width: 50%; margin-left: auto; margin-right:auto}</style>");
		wd.println("</head><body>"); 
		wd.println(om.writeValueAsString(EmployeeList));
		wd.println("<table>");
		wd.println("<tr><th>ID</th><th>Employee Name</th><th>Passwords</th></tr>");
		for (Employee e: EmployeeList)
			wd.println("<tr><td>" + e.getId() + "</td><td>" + e.getUsername() + "</td><td>" + e.getPassword() +  "</td></tr>");
		wd.println("</table></body></html>"); */
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
