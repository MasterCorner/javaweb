package cn.edu.swu.mvcapp.dao;

import java.util.List;

/**
 * 	��װ�˻�����CRUD�ķ������Թ�����̳�ʹ��
 * 	��ǰDAOֱ���ڷ����л�ȡ���ݿ�����
 * 	@param <T>:��ǰDAO�����ʵ�����������ʲô
 */

public class DAO<T> {
	
	private Class<T> clazz;
	
	/**
	 *	����ĳһ���ֶε�ֵ�����緵��ĳһ����¼��customerName, �򷵻����ݱ����ж�������¼��
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object ... args) {
		return null;
	}
	/**
	 * 	����T����Ӧ��List
	 * @param sql
	 * @param args
	 * @return
	 */
	public List <T> getForList(String sql,Object ... args){
		return null;
	}
	/**
	 *	���ض�Ӧ��T��һ��ʵ����Ķ���
	 *	@param sql
	 *	@param args
	 *	@return
	 */
	public T get(String sql,Object ... args) {
		return null;		
	}
	/**
	 *	�÷�����װ��INSERT��DELETE��UPDATE ����
	 *	@param sql: SQL���
	 *	@param args: ���SQL����ռλ�� 
	 */
	public void update(String sql, Object ... args) {
		
	}
}
