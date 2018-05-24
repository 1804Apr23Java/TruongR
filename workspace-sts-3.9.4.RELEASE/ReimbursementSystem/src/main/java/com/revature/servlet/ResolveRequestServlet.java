package com.revature.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.exception.RequestAlreadyResolvedException;

/**
 * Servlet implementation class ResolveRequestServlet
 */
public class ResolveRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolveRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		boolean isApproved = request.getParameter("decision").equals("approve");
		int managerId = (Integer) request.getSession().getAttribute("managerId");
		
		ManagerDao md = new ManagerDaoImpl();
		try {
		md.resolveRequest(requestId, isApproved, managerId);
		} catch (RequestAlreadyResolvedException e) {
			System.out.println("Oops, someone resolved this request already.");
		}
		
		response.sendRedirect("./managerPortal.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
