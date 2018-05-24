package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.beans.Manager;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		

		//System.out.println("IN LOGIN DOGET");
		
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//System.out.println("IN LOGIN DOPOST");
		
		EmployeeDao ed = new EmployeeDaoImpl();

		response.setContentType("text/html");
		// PrintWriter pw = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// authenticate user
		Employee emp = ed.login(username, password); 
		if (emp != null) {
			System.out.println("emp valid");
			HttpSession session = request.getSession();
			if (emp instanceof Manager) {
				session.setAttribute("managerId", ((Manager) emp).getManagerId());	
			}
			session.setAttribute("username", emp.getUsername());
			session.setAttribute("employeeId", emp.getEmployeeId());
			response.sendRedirect("portal.html");
		} else {
			response.sendRedirect("login.html");
		}
	}

}
