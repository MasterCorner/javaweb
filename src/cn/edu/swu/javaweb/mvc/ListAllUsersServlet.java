package cn.edu.swu.javaweb.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAllUsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao userDao = new UserDao();
		List<User> users = userDao.getAll();
		
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("/users.jsp").forward(request, response);
	}

}
