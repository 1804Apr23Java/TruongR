package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Request;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;

/**
 * Servlet implementation class GetAllPendingRequestsServlet
 */
public class GetAllPendingRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllPendingRequestsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("managerId") == null)
			response.sendRedirect("login.html");
		
		ManagerDao md = new ManagerDaoImpl();
		response.setContentType("text/plain");
		PrintWriter wd = response.getWriter();
		List <Request> pendingRequests = md.getAllPendingRequests();
		ObjectMapper om = new ObjectMapper();
		wd.println(om.writeValueAsString(pendingRequests));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
