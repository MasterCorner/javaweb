package cn.edu.swu.mvcapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.activation.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC�����Ĺ�����
 * @author ������
 *
 */
public class jdbcUtils {

	/**
	 * 	�ͷ�Connection����
	 * @param connection
	 */
	public static void releaseConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	private static ComboPooledDataSource dataSource = null;
	
	static {
		//����Դֻ�ܱ�����һ�Ρ�
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	
	/**
	 *	��������Դ��һ��Connection����
	 * @return
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
