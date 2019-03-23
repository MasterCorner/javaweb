package cn.edu.swu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

public class LoginServlet3 extends HttpServlet{

	protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		PrintWriter out = resp.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//12.13
			String url = "jdbc:mysql://47.102.203.124:3306/swu_db?characterEncoding=utf-8&serverTimezone=UTC";
			//String url = "jdbc:mysql://localhost:3306/swu_db?characterEncoding=utf-8&serverTimezone=UTC";
			String user = "root";
			String pass = "network";
			connection = DriverManager.getConnection(url,user,pass);
			
			String sql = "SELECT count(id) FROM users WHERE username = ? " + "AND password = ? ";
			//String sql = "SELECT * FROM users";
			System.out.println(sql);
			
			statement = connection.prepareStatement(sql);
			statement.setString(1,username);
			statement.setString(2,password);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				int count = resultSet.getInt(1);
				
				if(count>0) {
					out.print("Hello: "+username);
				}else {
					out.print("Sorry: "+username);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if(statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
	}
}
