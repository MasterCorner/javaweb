package cn.edu.swu.mvcapp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;

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

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		String forwardPath  = "/error.jsp";
		
		//TODO Auto-generated method stub
		//1.请求调用参数id
		String idStr = request.getParameter("id");
		//int id = -1;
		
		//2.调用 CustomerDAO 的 customerDAO.get(id) 获取和 id 对应的 Customer 对象 customer
		try {
			Customer customer = customerDAO.get(Integer.parseInt(idStr));
			if (customer != null) {
				forwardPath = "/updatecustomer.jsp";
				//3.将 customer 放入 request 中
				request.setAttribute("customer", customer);
			} 
		} catch (NumberFormatException e) {}
		
		//4.响应 updatecustomer.jsp 页面 ： 转发。
		request.getRequestDispatcher(forwardPath).forward(request, response);
		//System.out.println("edit");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		// TODO Auto-generated method stub
		//System.out.println("update");
		//1.获取表单参数：id，name，address，phone
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String oldName = request.getParameter("oldName");
		
		//2.检验name是否已经被占用
		
		
		//2.1比较 name 和 oldName 是否相同，若相同说明 name 可用。
		//2.1若不相同，则调用CustomerDAO 的getCountWithName(String name) 获取 name 在数据库中是否存在
		if (!oldName.equalsIgnoreCase(name)) {
			long count  = customerDAO.getCountWithName(name);
			//2.2若返回值大于0，则响应updatecustomer.jsp页面：通过转发的方式来响应newcustomer.jsp
			if (count > 0) {
				//2.2.1要求updatecustomer.jsp页面显示一个错误消息：用户名name已经被占用，请重新选择！
				//在request中放入一个属性message: 用户名name已经被占用，请重新选择！
				//在页面上通过request.getAttribute("message")的方式来显示
				request.setAttribute("message", "用户名" + name + "已经被占用，请重新选择！");
				
				//2.2.2 updatecustomer.jsp的表单值可以回显
				//address, phone 显示提交表单的新的值，而 name 显示 oldName ,而不是新提交的 name

				//2.2.3结束方法：return
				request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
				return;
			}
		}

		//3.把表单参数封装为一个Customer对象customer
		Customer customer = new Customer(name,address,phone);
		customer.setId(Integer.parseInt(id));
				
		//4.调用CustomerDAO的update(Customer customer)执行更新操作
		customerDAO.update(customer);
		 
		//5.重定向到query.do
		response.sendRedirect("query.do");
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取表单参数：name,address,phone
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		 
		//2.检验name是否已经被占用
		
		//2.1调用CustomerDAO的getCountWithName(String name)获取name的数据库中是否存在
		long count = customerDAO.getCountWithName(name);
		
		//2.2若返回值大于0，则响应newcustomer.jsp页面：
		//通过转发的方式来响应newcustomer.jsp
		if(count > 0) {
			
			//2.2.1要求newcustomer.jsp页面显示一个错误消息：用户名name已经被占用，请重新选择！
			//在request中放入一个属性message: 用户名name已经被占用，请重新选择！
			//在页面上通过request.getAttribute("message")的方式来显示
			request.setAttribute("message", "用户名"+ name + "已经被占用，请重新选择！");
			
			//2.2.2 newcustomer.jsp的表单值可以回显
			//通过value="<%= request.getParameter("name") == null ? "" : request.getParameter("name") %>"
			//来进行回显
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}

		
		//2.2.3结束方法：return
		
		//3.把表单参数封装为一个Customer对象customer
		Customer customer = new Customer(name,address,phone);
		
		//4.调用CustomerDAO的save(Customer customer)执行保存操作
		customerDAO.save(customer);
		
		//5.重定向到addsuccess.jsp页面；使用重定向可以避免出现表单的重复提交问题。
		//System.out.println(request.getParameter("name"));
		response.sendRedirect("addsuccess.jsp");
		//request.getRequestDispatcher("/addsuccess.jsp").forward(request, response);		
		// TODO Auto-generated method stub
		//System.out.println("add");
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

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = request.getParameter("id");
		int id = 0;
		
		//try ... catch 的作用；防止idStr 不能转为int类型
		//若不能转则 id = 0,无法进行任何操作。
		try {
			id = Integer.parseInt(idStr);
			customerDAO.delete(id);
		} catch (Exception e) {}
			// TODO: handle exception
		
			response.sendRedirect("query.do");
		
	}

}
