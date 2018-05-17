package com.revature.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ProfileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2625346607120644446L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			req.getRequestDispatcher("profile.html").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
	
}
