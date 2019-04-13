package cn.edu.swu.mvcapp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.swu.mvcapp.dao.CriteriaCustomer;
import cn.edu.swu.mvcapp.dao.CustomerDAO;
import cn.edu.swu.mvcapp.dao.impl.CustomerDAOjdbcImpl;
import cn.edu.swu.mvcapp.domain.Customer;

/**
 * Servlet implementation class customerServlet
 */
@WebServlet("/customerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDAO customerDAO = new CustomerDAOjdbcImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String method = request.getParameter("method");
//
//		switch (method) {
//		case "add":
//			add(request, response);
//			break;
//		case "query":
//			query(request, response);
//			break;
//		case "delete":
//			delete(request, response);
//			break;
//		case "update":
//			update(request, response);
//			break;
//		}
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.获取ServletPath: /edit.do 或 /addCustomer.do
		String servletPath = req.getServletPath();
		
		//2.去除 / 和 .do ，得到类似于edit或addCustomer这样的字符串
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);

		try {
			//3.利用反射获取methodName对应的方法
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			//4.利用反射调用对应的方法
			method.invoke(this, req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			resp.sendRedirect("error.jsp");
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("edit");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("update");
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("add");
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取模糊查询的请求参数
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		//把请求参数封装为一个CriteriaCustomer对象
		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
		
		//1.调用CustomerDAO 的getForListWithCriteriaCustomer()得到Customer的集合
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomerscustomer(cc);
		
		//2.把Customer的集合放入request中
		request.setAttribute("customers", customers);
		
		//3.转发页面到customer.jsp（不能使用重定向）
		request.getRequestDispatcher("/customer.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("delete");
	}

}
