package cn.edu.swu.mvcapp.dao.impl;

import java.util.List;

import cn.edu.swu.mvcapp.dao.CriteriaCustomer;
import cn.edu.swu.mvcapp.dao.CustomerDAO;
import cn.edu.swu.mvcapp.domain.Customer;

public class CustomerDAOXMLImpl implements CustomerDAO {

	@Override
	public List<Customer> getForListWithCriteriaCustomerscustomer(CriteriaCustomer cc) {
		// TODO Auto-generated method stub
		System.out.println("getForListWithCriteriaCustomerscustomer");
		return null;
	}

	@Override
	public List<Customer> getAll() {
		System.out.println("getAll");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Customer customer) {
		System.out.println("save");
		// TODO Auto-generated method stub
	}

	@Override
	public Customer get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("get");
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("delete");
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("update");
	}

	@Override
	public long getCountWithName(String name) {
		// TODO Auto-generated method stub
		System.out.println("getCountWithName");
		return 0;
	}

}
