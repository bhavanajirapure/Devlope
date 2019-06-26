package com.nt.reg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegError")
public class RegError extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("user already exist & try again");
		out.println("<br><br>");
		out.print("<a href='index.html'>Home</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		out.print("<a href='Reg.html'>New Registration</a>&nbsp;&nbsp;&nbsp;&nbsp;");
	}

}
