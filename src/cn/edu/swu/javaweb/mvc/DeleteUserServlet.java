package cn.edu.swu.javaweb.mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String user
		
		UserDao UserDao = new UserDao();
		UserDao.deleteByFlowId(Integer.parseInt(id));
		
		request.getRequestDispatcher("/success.jsp").forward(request, response); 
	}
	
}
