package com.revature.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class CalculatorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -756208604967516279L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//PrintWriter pw = resp.getWriter();
		//pw.write("<p>hello from the <h3>CalculatorServlet</h3></p>");
		
		
		//forward the request to our Calculator.html page from calculator
		
		RequestDispatcher rd = req.getRequestDispatcher("Calculator.html");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//obtain our request parameters
		Double n1 = Double.parseDouble(req.getParameter("n1"));
		Double n2 = Double.parseDouble(req.getParameter("n2"));
		String operation = req.getParameter("operation");
		Double answer = 0.0;
		
		//calculate answer
		
		switch(operation) {
		case "add":
			answer = n1 + n2;
			break;
		case "subtract":
			answer = n1 - n2;
			break;
		case "multiply":
			answer = n1 * n2;
			break;
		case "divide":
			answer = n1 / n2;
			break;
		}
		
		//include answer in response
		//PrintWriter pw = resp.getWriter();
		//pw.write("the answer is: " + answer);
		
		//or forward request to an answerservlet
		
		req.setAttribute("answer", answer);
		RequestDispatcher rd = req.getRequestDispatcher("answer");
		rd.forward(req,  resp);
	}
	
}
