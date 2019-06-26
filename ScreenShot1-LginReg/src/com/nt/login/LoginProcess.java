package com.nt.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.db.MyCon;




@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
static Scanner sc=new Scanner(System.in);
	
	static Connection con=MyCon.getCon();
	
	//b.m1=>check weather user id already exist or not
	public static boolean loginCheck(String v1,String v2) {
		boolean res=false;
		try {
			//prepare query & execute
			String query="select * from reg where userid=? and pwd=?";
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, v1);
			ps.setString(2, v2);
			
			ResultSet rs=ps.executeQuery();
			res=rs.next();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// read html from data
		String v1 = request.getParameter("uid");
		String v2 = request.getParameter("pwd");
		
		//check user already exist or not
		boolean res=loginCheck(v1,v2);
		if (res==true) {
			request.setAttribute("uidkey", v1);
			RequestDispatcher rd= request.getRequestDispatcher("LoginSuccess");
			rd.forward(request, response);
			
		}
		else {
			RequestDispatcher rd= request.getRequestDispatcher("LoginError");
			rd.forward(request, response);
				
			}
	}
}


