package com.website;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class RegisterServlet
 */
@MultipartConfig


@WebServlet("/registerationServlet")
public class registerationServlet extends HttpServlet {
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		String ufname=request.getParameter("first_name");
		String ulname=request.getParameter("last_name");
		String uinstitute=request.getParameter("institute");
		String uemail=request.getParameter("email");
		String areacode=request.getParameter("area_code");
		String umobile=request.getParameter("mobile");
		String ubranch=request.getParameter("branch");
		String topics=request.getParameter("topic");
		/*get file data*/
		
		Part part=request.getPart("file");
		String fileName=part.getSubmittedFileName();
		
		String path=getServletContext().getRealPath("/"+"Files"+File.separator+fileName);
		
		RequestDispatcher dispatcher=null;
		Connection con=null;
		
		
		InputStream is=part.getInputStream();
		boolean success=uploadFile(is,path);
		
		
	
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp?useSSL=false","root","swetha");
			PreparedStatement pst= con.prepareStatement("insert into web(ufname,ulname,uemail,uinstitute,areacode,ubranch,umobile,topics,fileName) values(?,?,?,?,?,?,?,?,?)");
			
			
			pst.setString(1, ufname);
			pst.setString(2, ulname);
			pst.setString(3, uemail);
			pst.setString(4, uinstitute);
			pst.setString(5, areacode);
			pst.setString(6, ubranch);
			pst.setString(7, umobile);
			pst.setString(8, topics);
			pst.setString(9, fileName);
			int rowCount=pst.executeUpdate();
			dispatcher=request.getRequestDispatcher("register.jsp");
			
			
			if(rowCount>0 && success==true){
				request.setAttribute("status", "success");
			}else{
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		/*PrintWriter out=response.getWriter();
		out.println(uname);
		out.println(upwd);
		out.println(uemail);
		out.println(umobile);
		
		*/
	}
	
	public boolean uploadFile(InputStream is,String path){
		boolean test=false;
		try{
			byte[] byt= new byte[is.available()];
			is.read();
			FileOutputStream fops=new FileOutputStream(path);
			fops.write(byt);
			fops.flush();
			fops.close();
			
			test=true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return test;
	}


}
