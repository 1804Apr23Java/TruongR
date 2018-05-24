package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.util.EmployeeField;

/**
 * Servlet implementation class UpdateProfileServlet
 */
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfileServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// need to ensure that employeeId is in cookies
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("employeeId") == null)
			response.sendRedirect("login.html");
		
		int employeeId = (Integer) session.getAttribute("employeeId");
		
		EmployeeField field = EmployeeField.valueOf(request.getParameter("field"));
		String updateValue = request.getParameter("updateValue");

		EmployeeDao ed = new EmployeeDaoImpl();

		Employee emp = ed.updateEmployee(employeeId, field, updateValue);
		
		response.sendRedirect("portal.html");

	}

}
