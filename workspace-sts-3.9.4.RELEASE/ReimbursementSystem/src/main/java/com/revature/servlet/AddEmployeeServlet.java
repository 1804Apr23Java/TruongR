package com.revature.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.revature.dao.*;

/**
 * Servlet implementation class AddEmployeeServlet
 */
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerDao md = new ManagerDaoImpl();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		md.addEmployee(username, password, "", "", "", "", "", "", "", "");
		
		response.sendRedirect("index.html");
		
	}

}
