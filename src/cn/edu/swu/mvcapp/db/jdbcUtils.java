package cn.edu.swu.mvcapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/**
 * JDBC操作的工具类
 * @author 幻紫星
 *
 */
public class jdbcUtils {

	/**
	 * 	释放Connection连接
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
		//数据源只能被创建一次。
		
		dataSource = new ComboPooledDataSource("mvcApp");
	}
	
	/**
	 *	返回数据源的一个Connection对象
	 * @return
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
