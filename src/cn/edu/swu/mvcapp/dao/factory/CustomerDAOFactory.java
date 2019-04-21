package cn.edu.swu.mvcapp.dao.factory;

import java.util.HashMap;
import java.util.Map;

import cn.edu.swu.mvcapp.dao.CustomerDAO;
import cn.edu.swu.mvcapp.dao.impl.CustomerDAOXMLImpl;
import cn.edu.swu.mvcapp.dao.impl.CustomerDAOjdbcImpl;

public class CustomerDAOFactory {
	
	private Map<String,CustomerDAO> daos = new HashMap<String,CustomerDAO>();
	
	private static CustomerDAOFactory instance = new CustomerDAOFactory();
	
	public static CustomerDAOFactory getInstance() {
		return instance;
	}
	
	private String type = null;
	
	public void setType(String type) {
		this.type = type;
	}
	
	private CustomerDAOFactory() {
		daos.put("jdbc",new CustomerDAOjdbcImpl());
		daos.put("xml", new CustomerDAOXMLImpl());
	}
	
	public CustomerDAO getCustomerDAO() {
		return daos.get(type);
	}
}
