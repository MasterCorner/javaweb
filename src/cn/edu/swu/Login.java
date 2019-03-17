package cn.edu.swu;

import javax.servlet.http.*;

public class Login extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse resp) {
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
	}


	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		doPost(req, resp);
	}
}


