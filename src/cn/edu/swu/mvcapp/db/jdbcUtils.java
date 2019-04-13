package cn.edu.swu.mvcapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

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
	
	//private static ComboPooledDataSource dataSource = null;
	private static DataSource dataSource = null;
	
	static {
		//����Դֻ�ܱ�����һ�Ρ�
		
		dataSource = new ComboPooledDataSource("mvcApp");
	}
	
	/**
	 *	��������Դ��һ��Connection����
	 * @return
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
