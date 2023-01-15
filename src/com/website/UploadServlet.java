package com.website;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	PrintWriter out=response.getWriter();*/
		
	/*	Part part=request.getPart("file");
		String name=part.getSubmittedFileName();
		
		String path=getServletContext().getRealPath("/"+"Files"+File.separator+name);*/
		//out.println(name);
		/*InputStream is=part.getInputStream();
		boolean succs=uploadFile(is,path);
		if(succs){
			out.println("uploaded"+path);
		}
		else{
			out.println("error");
		}
	
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
