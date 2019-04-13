package cn.edu.swu.mvcapp.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import cn.edu.swu.mvcapp.dao.CriteriaCustomer;
import cn.edu.swu.mvcapp.dao.CustomerDAO;
import cn.edu.swu.mvcapp.dao.impl.CustomerDAOjdbcImpl;
import cn.edu.swu.mvcapp.domain.Customer;

public class CustomerDAOjdbcImplTest {
	
	private CustomerDAO customerDAO = new CustomerDAOjdbcImpl();

	@Test
	public void testGetForListWithCriteriaCustomerscustomer() {
		CriteriaCustomer cc =new CriteriaCustomer("k", null, null);
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomerscustomer(cc);
		System.out.println(customers);
	}
	
	@Test
	public void testGetAll() {
		fail("Not yet implemented");
		//List<Customer> customers = customerDAO.getAll();
		//System.out.println(customers);
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
		
//		Customer customer = new Customer();
//		customer.setAddress("BeiJing");
//		customer.setName("Mike");
//		customer.setPhone("13101375012");
//		
//		customerDAO.save(customer);
		
	}

	@Test
	public void testGetInteger() {
		Customer cust = customerDAO.get(4);
		System.out.println(cust);
		//fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		//customerDAO.delete(4);
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountWithName() {
		long count = customerDAO.getCountWithName("Mike");
		System.out.println(count);
		//fail("Not yet implemented");
	}

}
