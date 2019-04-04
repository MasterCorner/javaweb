package cn.edu.swu.javaweb.mvc;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	/*
	public void deleteByFlowId(Integer id){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String driverClass = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://47.102.203.124:3306/swu_db?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String user = "root";
			String password = "network";
			
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);
			
			String sql = "DELETE FROM users WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			try {
				if(preparedStatement != null){
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	*/
	
	public List<User> getAll(){
		
		List<User> users = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			String driverClass = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://47.102.203.124:3306/swu_db?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String user = "root";
			String password = "network";
			
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT id, username, password from users";
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String username = resultSet.getString(2);
				String password1 = resultSet.getString(3);
				
				User user1 = new User(id, username, password1);
				users.add(user1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(resultSet != null){
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(preparedStatement != null){
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return users;
	}


}
