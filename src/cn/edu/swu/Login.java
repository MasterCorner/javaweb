package cn.edu.swu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println(req.getContextPath());

		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		
		try{
			System.out.println(new String(user.getBytes("ISO-8859-1"), "UTF-8"));
		} catch(Exception e) {
			System.out.println("encoding error");
		}
		System.out.println(pass);
		System.out.println("test");
		File file =new File("user.txt");
		Writer out =new FileWriter(file);
		out.write(user);
		out.write(pass);
		out.close();

	}


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doPost(req, resp);
	}
}


