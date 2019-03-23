package cn.edu.swu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet implements Servlet {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	private ServletConfig servletConfig;
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.servletConfig = servletConfig;
		System.out.println("123");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userip = request.getRemoteAddr();
		
		ServletContext servletContext = servletConfig.getServletContext();
		String initUser = servletContext.getInitParameter("user");
		String initPassword = servletContext.getInitParameter("pass");
		
		PrintWriter out = response.getWriter();
		
		System.out.println("test");
		File file =new File("webuser.txt");
		System.out.println("path: " +file.getCanonicalPath());//获取文件路径		
		System.out.println("path: " +file.getName());//获取文件名字
		FileWriter fw=null;
		fw=new FileWriter(file.getAbsoluteFile(),true);//true表示续写而不是覆盖
		BufferedWriter bw=new BufferedWriter(fw);
		bw =new BufferedWriter(fw);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bw.write("systime:"+df.format(new Date())+"\r\n");
		bw.write("username:"+username+"\r\n");//win用\r\n，linux用\n，Mac用\r
		bw.write("password:"+password+"\r\n");
		bw.write("user_ip:"+userip+"\r\n\r\n");
		bw.close();
		
		
		if(initUser.equals(username)&&initPassword.equals(password)) {
			out.print("Hello: "+username);
		}else {
			out.print("Sorry: "+username);
		}
	}

}
