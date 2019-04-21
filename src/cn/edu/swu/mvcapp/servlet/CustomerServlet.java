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
		
		//1.��ȡServletPath: /edit.do �� /addCustomer.do
		String servletPath = req.getServletPath();
		
		//2.ȥ�� / �� .do ���õ�������edit��addCustomer�������ַ���
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);

		try {
			//3.���÷����ȡmethodName��Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			//4.���÷�����ö�Ӧ�ķ���
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
		//1.������ò���id
		String idStr = request.getParameter("id");
		//int id = -1;
		
		//2.���� CustomerDAO �� customerDAO.get(id) ��ȡ�� id ��Ӧ�� Customer ���� customer
		try {
			Customer customer = customerDAO.get(Integer.parseInt(idStr));
			if (customer != null) {
				forwardPath = "/updatecustomer.jsp";
				//3.�� customer ���� request ��
				request.setAttribute("customer", customer);
			} 
		} catch (NumberFormatException e) {}
		
		//4.��Ӧ updatecustomer.jsp ҳ�� �� ת����
		request.getRequestDispatcher(forwardPath).forward(request, response);
		//System.out.println("edit");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		// TODO Auto-generated method stub
		//System.out.println("update");
		//1.��ȡ��������id��name��address��phone
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String oldName = request.getParameter("oldName");
		
		//2.����name�Ƿ��Ѿ���ռ��
		
		
		//2.1�Ƚ� name �� oldName �Ƿ���ͬ������ͬ˵�� name ���á�
		//2.1������ͬ�������CustomerDAO ��getCountWithName(String name) ��ȡ name �����ݿ����Ƿ����
		if (!oldName.equalsIgnoreCase(name)) {
			long count  = customerDAO.getCountWithName(name);
			//2.2������ֵ����0������Ӧupdatecustomer.jspҳ�棺ͨ��ת���ķ�ʽ����Ӧnewcustomer.jsp
			if (count > 0) {
				//2.2.1Ҫ��updatecustomer.jspҳ����ʾһ��������Ϣ���û���name�Ѿ���ռ�ã�������ѡ��
				//��request�з���һ������message: �û���name�Ѿ���ռ�ã�������ѡ��
				//��ҳ����ͨ��request.getAttribute("message")�ķ�ʽ����ʾ
				request.setAttribute("message", "�û���" + name + "�Ѿ���ռ�ã�������ѡ��");
				
				//2.2.2 updatecustomer.jsp�ı�ֵ���Ի���
				//address, phone ��ʾ�ύ�����µ�ֵ���� name ��ʾ oldName ,���������ύ�� name

				//2.2.3����������return
				request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
				return;
			}
		}

		//3.�ѱ�������װΪһ��Customer����customer
		Customer customer = new Customer(name,address,phone);
		customer.setId(Integer.parseInt(id));
				
		//4.����CustomerDAO��update(Customer customer)ִ�и��²���
		customerDAO.update(customer);
		 
		//5.�ض���query.do
		response.sendRedirect("query.do");
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ��������name,address,phone
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		 
		//2.����name�Ƿ��Ѿ���ռ��
		
		//2.1����CustomerDAO��getCountWithName(String name)��ȡname�����ݿ����Ƿ����
		long count = customerDAO.getCountWithName(name);
		
		//2.2������ֵ����0������Ӧnewcustomer.jspҳ�棺
		//ͨ��ת���ķ�ʽ����Ӧnewcustomer.jsp
		if(count > 0) {
			
			//2.2.1Ҫ��newcustomer.jspҳ����ʾһ��������Ϣ���û���name�Ѿ���ռ�ã�������ѡ��
			//��request�з���һ������message: �û���name�Ѿ���ռ�ã�������ѡ��
			//��ҳ����ͨ��request.getAttribute("message")�ķ�ʽ����ʾ
			request.setAttribute("message", "�û���"+ name + "�Ѿ���ռ�ã�������ѡ��");
			
			//2.2.2 newcustomer.jsp�ı�ֵ���Ի���
			//ͨ��value="<%= request.getParameter("name") == null ? "" : request.getParameter("name") %>"
			//�����л���
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}

		
		//2.2.3����������return
		
		//3.�ѱ�������װΪһ��Customer����customer
		Customer customer = new Customer(name,address,phone);
		
		//4.����CustomerDAO��save(Customer customer)ִ�б������
		customerDAO.save(customer);
		
		//5.�ض���addsuccess.jspҳ�棻ʹ���ض�����Ա�����ֱ����ظ��ύ���⡣
		//System.out.println(request.getParameter("name"));
		response.sendRedirect("addsuccess.jsp");
		//request.getRequestDispatcher("/addsuccess.jsp").forward(request, response);		
		// TODO Auto-generated method stub
		//System.out.println("add");
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡģ����ѯ���������
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		//�����������װΪһ��CriteriaCustomer����
		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
		
		//1.����CustomerDAO ��getForListWithCriteriaCustomer()�õ�Customer�ļ���
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomerscustomer(cc);
		
		//2.��Customer�ļ��Ϸ���request��
		request.setAttribute("customers", customers);
		
		//3.ת��ҳ�浽customer.jsp������ʹ���ض���
		request.getRequestDispatcher("/customer.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = request.getParameter("id");
		int id = 0;
		
		//try ... catch �����ã���ֹidStr ����תΪint����
		//������ת�� id = 0,�޷������κβ�����
		try {
			id = Integer.parseInt(idStr);
			customerDAO.delete(id);
		} catch (Exception e) {}
			// TODO: handle exception
		
			response.sendRedirect("query.do");
		
	}

}
