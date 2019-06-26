package com.nt.reg;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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

@WebServlet("/RegProcess")
public class RegProcess extends HttpServlet {static Scanner sc = new Scanner(System.in);

static Connection con = MyCon.getCon();

// b.m1=>check weather user id already exist or not
public static boolean checkUserId(String uid) {
	boolean res = false;
	try {
		// prepare query & execute
		String query = "select * from reg where userid=?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, uid);
	

		ResultSet rs = ps.executeQuery();
		res = rs.next();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;

}

// b.m2=>insert record
public static int saveUser(String v1, String v2, String v3, Date v4, String v5, String v6, String v7, String v8) {
	int res = 0;
	try {
		// prepare query & execute
		String query = "insert into reg values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, v1);
		ps.setString(2, v2);
		ps.setString(3, v3);
		ps.setDate(4, v4);
		ps.setString(5, v5);
		ps.setString(6, v6);
		ps.setString(7, v7);
		ps.setString(8, v8);
		res = ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out = resp.getWriter();
	String v6 = null;

	// read html from data
	String v1 = req.getParameter("n1");
	String v2 = req.getParameter("n2");
	String v3 = req.getParameter("n3");
	String str = req.getParameter("n4");
	Date v4 = Date.valueOf(str);
	String v5 = req.getParameter("n5");

	String st[] = req.getParameterValues("n6");
	v6=st[0];
	for (int i = 1; i < st.length; i++) {
		v6 =v6+ ", "+ st[i];
	}
	String v7 = req.getParameter("n7");
	String v8 = req.getParameter("n8");

	// check user already exist or not
	boolean res1 = checkUserId(v2);
	if (res1 == true) {
		RequestDispatcher rd= req.getRequestDispatcher("RegError");
		rd.forward(req, resp);
		
	} else {
		// step 2 insert record
		int res2 = saveUser(v1, v2, v3, v4, v5, v6, v7, v8);
		if (res2 > 0) {
			req.setAttribute("namekey", v1);
			RequestDispatcher rd= req.getRequestDispatcher("RegSucess");
			rd.forward(req, resp);
		}
	}
}

}
