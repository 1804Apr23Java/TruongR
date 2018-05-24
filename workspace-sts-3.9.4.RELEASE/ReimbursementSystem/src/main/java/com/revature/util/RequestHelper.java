package com.revature.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

	public static String process(HttpServletRequest request) throws IOException {
		if (request.getSession(false) == null) {
			return "login.html";
		}
		switch (request.getParameter("destination")) {
		case "login":
			return "login";
		default:
			return "error";
		}
	}

}