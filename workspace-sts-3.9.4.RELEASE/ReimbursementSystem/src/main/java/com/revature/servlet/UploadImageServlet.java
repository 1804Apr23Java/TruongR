package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * Servlet implementation class UploadImageServlet
 */
@MultipartConfig(maxFileSize = 10 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024, fileSizeThreshold = 5 * 1024 * 1024)
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImageServlet() {
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

		try {
			Part filePart = request.getPart("fileToUpload");
			PrintWriter pw = response.getWriter();
			
			pw.println("<!DOCTYPE html><html><head></head><body>");
			pw.println(filePart.getSubmittedFileName());

			final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

			s3.putObject("rtruong2-receipts", filePart.getSubmittedFileName(), filePart.getInputStream(),
					new ObjectMetadata());
			pw.println("<img src=\"" + " https://s3.amazonaws.com/rtruong2-receipts/" + filePart.getSubmittedFileName()
					+ "\" width=\"50%\"></body>");

		} catch (IllegalStateException e) {
			response.getWriter().println("ERROR: Image size too large");
		}

	}

}
