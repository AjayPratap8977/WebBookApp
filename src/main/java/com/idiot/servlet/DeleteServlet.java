




package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")

public class DeleteServlet extends HttpServlet {
private static final String query=" delete from BOOKDATA where id=?";	

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//get PrintWriter
	PrintWriter pw=res.getWriter();
	//set content type
	res.setContentType("text/html"); 

	// get the id of record
	int id=Integer.parseInt(req.getParameter("id"));
	
	//Load jdbc driver
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
	
		e.printStackTrace();
	}
	// Generate the Connection
	try( Connection con =DriverManager.getConnection("jdbc:mysql:///book","root","Ajay@143");
			
	
		PreparedStatement ps=con.prepareStatement(query);){
	ps.setInt(1, id);
		
		int count=ps.executeUpdate();
		if(count==1) {
			pw.println("<h2>Record is Deleted SuccessFully<h2>");
		}else {
			pw.println("<h2>Record is NOT Deleted SuccessFully<h2>");
		}
		
	}catch(SQLException se){
		se.printStackTrace();
		pw.println("<h1>"+se.getMessage()+"</h1>");
	}
	catch(Exception e) {
		e.printStackTrace();
		pw.println("<h1>"+e.getMessage()+"</h1>");
	}
	pw.println("<a href='Home.html'>Home Page</a>");
	pw.println("<br>");
	pw.println("<a href='bookList'>Book List</a>");
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
		
	}
}

