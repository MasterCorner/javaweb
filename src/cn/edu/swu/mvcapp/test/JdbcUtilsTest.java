package cn.edu.swu.mvcapp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import cn.edu.swu.mvcapp.db.jdbcUtils;

public class JdbcUtilsTest {

	@Test
	public void testGetConnection() throws SQLException {
		Connection connection = jdbcUtils.getConnection();
		System.out.println(connection);
	}

}
