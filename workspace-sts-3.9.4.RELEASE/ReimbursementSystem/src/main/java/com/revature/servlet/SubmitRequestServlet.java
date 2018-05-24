package com.revature.servlet;

import java.io.IOException;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

/**
 * Servlet implementation class SubmitRequestServlet
 */
@MultipartConfig(maxFileSize = 10 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024, fileSizeThreshold = 5 * 1024 * 1024)
public class SubmitRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitRequestServlet() {
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

		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("employeeId") == null)
			response.sendRedirect("login.html");
		
		double amount = Double.parseDouble(request.getParameter("amount"));
		int employeeId = (Integer) request.getSession().getAttribute("employeeId");
		EmployeeDao ed = new EmployeeDaoImpl();

		try {
			Part filePart = request.getPart("imagefile");
			if (filePart.getSize() == 0) {
				System.out.println("no file?");
				ed.submitRequest(employeeId, amount);
				response.sendRedirect("portal.html");
			} else {

				String filename = filePart.getSubmittedFileName();
				String ext = filename.lastIndexOf(".") == -1 ? "" : filename.substring(filename.lastIndexOf("."));
				String username = (String) session.getAttribute("username");
				String mill = String.valueOf(Instant.now().toEpochMilli());

				String newFileName = username + "-" + mill + ext;

				final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

				s3.putObject("rtruong2-receipts", newFileName, filePart.getInputStream(), new ObjectMetadata());

				ed.submitRequest(employeeId, amount, "s3.amazonaws.com/rtruong2-receipts/" + newFileName);
				response.sendRedirect("portal.html");
				
			}

		} catch (IllegalStateException e) {
			response.sendRedirect("portal.html");

		}

	}

}
