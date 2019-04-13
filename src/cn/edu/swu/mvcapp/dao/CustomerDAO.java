package cn.edu.swu.mvcapp.dao;

import java.util.List;

import cn.edu.swu.mvcapp.domain.Customer;

public interface CustomerDAO {
	
	public List<Customer> getForListWithCriteriaCustomerscustomer(CriteriaCustomer cc);

	public List<Customer> getAll();
	
	public void save(Customer customer);
	
	public Customer get(Integer id);
	
	public void delete(Integer id);
	
	/**
	 * 	返回和name相等的记录数
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);
}
