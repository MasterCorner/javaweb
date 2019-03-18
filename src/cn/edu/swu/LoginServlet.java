package cn.edu.swu;

import java.io.IOException;
import java.io.PrintWriter;

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
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		ServletContext servletContext = servletConfig.getServletContext();
		String initUser = servletContext.getInitParameter("user");
		String initPassword = servletContext.getInitParameter("pass");
		
		PrintWriter out = response.getWriter();
		
		if(initUser.equals(username)&&initPassword.equals(password)) {
			out.print("Hello: "+username);
		}else {
			out.print("Sorry: "+username);
		}
	}

}
