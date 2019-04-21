package cn.edu.swu.mvcapp.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.edu.swu.mvcapp.dao.factory.CustomerDAOFactory;

public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException{
		CustomerDAOFactory.getInstance().setType("jdbc");
		
		// 读取类路径下的 switch.properties 文件
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/switch.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
			//获取switch.properties 的type属性值
			String type = properties.getProperty("type");
			//赋给了CustomerDAOFactory 的type 水洗那个
			CustomerDAOFactory.getInstance().setType(type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
