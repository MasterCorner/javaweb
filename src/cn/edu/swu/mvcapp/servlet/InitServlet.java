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
		
		// ��ȡ��·���µ� switch.properties �ļ�
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/switch.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
			//��ȡswitch.properties ��type����ֵ
			String type = properties.getProperty("type");
			//������CustomerDAOFactory ��type ˮϴ�Ǹ�
			CustomerDAOFactory.getInstance().setType(type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
