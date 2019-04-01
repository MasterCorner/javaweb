package cn.edu.swu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet3 extends HttpServlet{

	protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String userip = req.getRemoteAddr();
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = resp.getWriter();
		//写入文件模块
		File file =new File("webuser.txt");
		FileWriter fw=null;
		fw=new FileWriter(file.getAbsoluteFile(),true);//true表示续写而不是覆盖
		BufferedWriter bw=new BufferedWriter(fw);
		bw =new BufferedWriter(fw);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bw.write("systime:"+df.format(new Date())+"\r\n");
		bw.write("username:"+username+"\r\n");//win用\r\n，linux用\n，Mac用\r
		bw.write("password:"+password+"\r\n");
		bw.write("user_ip:"+userip+"\r\n\r\n");
		bw.close();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//12.13
			String url = "jdbc:mysql://47.102.203.124:3306/swu_db?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			//String url = "jdbc:mysql://localhost:3306/swu_db?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
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
					//out.print("Hello:你好 "+username);
					req.getRequestDispatcher("/success.jsp").forward(req, resp);
				}else {
					//out.print("Sorry: "+username);
					req.getRequestDispatcher("/fail.jsp").forward(req, resp);
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
