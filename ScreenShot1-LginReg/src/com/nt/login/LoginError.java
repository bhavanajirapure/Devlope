package com.nt.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginError")
public class LoginError extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("Invalid user <br><br>");
		out.print("<a href='index.html'>Home</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		out.print("<a href='Login.html'>Login</a>&nbsp;&nbsp;&nbsp;&nbsp;");
	}

}
