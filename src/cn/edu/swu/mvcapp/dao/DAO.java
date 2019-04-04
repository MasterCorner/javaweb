package cn.edu.swu.mvcapp.dao;

import java.util.List;

/**
 * 	封装了基本的CRUD的方法，以供子类继承使用
 * 	当前DAO直接在方法中获取数据库连接
 * 	@param <T>:当前DAO处理的实体类的类型是什么
 */

public class DAO<T> {
	
	private Class<T> clazz;
	
	/**
	 *	返回某一个字段的值，例如返回某一条记录的customerName, 或返回数据表中有多少条记录等
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object ... args) {
		return null;
	}
	/**
	 * 	返回T所对应的List
	 * @param sql
	 * @param args
	 * @return
	 */
	public List <T> getForList(String sql,Object ... args){
		return null;
	}
	/**
	 *	返回对应的T的一个实例类的对象
	 *	@param sql
	 *	@param args
	 *	@return
	 */
	public T get(String sql,Object ... args) {
		return null;		
	}
	/**
	 *	该方法封装了INSERT、DELETE、UPDATE 操作
	 *	@param sql: SQL语句
	 *	@param args: 填充SQL语句的占位符 
	 */
	public void update(String sql, Object ... args) {
		
	}
}
