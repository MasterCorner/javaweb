package com.atguigu.javaweb.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAllStudentsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setAttribute("students", Arrays.asList("AA","BB","CC"));
		StudentDao studentDao = new StudentDao();
		List<Student> students = studentDao.getAll();
		
		request.setAttribute("students", students);
		
		request.getRequestDispatcher("/students.jsp").forward(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
