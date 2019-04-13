package cn.edu.swu.mvcapp.dao.impl;

import java.util.List;

import org.apache.catalina.filters.CsrfPreventionFilter;

import cn.edu.swu.mvcapp.dao.CriteriaCustomer;
import cn.edu.swu.mvcapp.dao.CustomerDAO;
import cn.edu.swu.mvcapp.dao.DAO;
import cn.edu.swu.mvcapp.domain.Customer;

public class CustomerDAOjdbcImpl extends DAO<Customer> implements CustomerDAO{

	/**
	 * 	返回满足查询条件的List
	 * 	@param cc: 封装了查询条件
	 * 	@return
	 */
	public List<Customer> getForListWithCriteriaCustomerscustomer(CriteriaCustomer cc) {
		// TODO Auto-generated method stub
		String sql = "select id, name, address, phone from customers where name like ? and address like ? and phone like ?";
		//修改了CriteriaCustomer 的getter 方法：使其返回的字符串中有 "%%".
		//若返回值为null 则返回"%%"，若不为null，则返回"%"+ 字段本身的值 +"%"
		return getForList(sql, cc.getName(),cc.getAddress(),cc.getPhone());
	}
	
	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		String sql = "select id, name, address, phone from customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "insert into customers(name, address, phone) VALUES(?,?,?)";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone());
		
	}

	@Override
	public Customer get(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select id, name, address, phone from customers where id = ?";
		return get(sql,id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from customers where id = ?";
		update(sql, id);
		
	}

	@Override
	public long getCountWithName(String name) {
		String sql="select count(id) from customers where name = ?";
		// TODO Auto-generated method stub
		return getForValue(sql, name);
	}

	
}
