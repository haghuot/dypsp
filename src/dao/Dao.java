package dao;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 泛型DAO(Data Access Object)类，用于操作数据库实体对象
 * @param <T> 实体类的泛型
 */
public class Dao<T> {

	// 数据库表名
	String clazzname;
	// 实体类的Class对象
	Class clazz;

	/**
	 * 构造方法，初始化实体类信息
	 * @param entity 实体类对象
	 */
	public Dao(T entity) {
		clazz = entity.getClass();
		clazzname = clazz.getSimpleName().toLowerCase();
	}

	/**
	 * 根据主键查询记录
	 * @param keyvalue 主键值
	 * @return 包含查询结果的实体类列表
	 */
	public List<T> queryByKey(String keyvalue) {
		String sql = "select * from " + clazzname + " where " + getKey() + "=?";
		List<T> list = (List<T>) DBUnitHelper.executeQuery(sql, clazz, keyvalue);
		return list;
	}

	/**
	 * 根据实体类对象查询记录
	 * @param entity 实体类对象
	 * @return 包含查询结果的实体类列表
	 */
	public List<T> query(T entity) {
		StringBuilder sbf = new StringBuilder("select * from " + clazzname + " where 1=1 ");
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getName().equals(getKey()) && !field.getName().equals("dbutil")) {
				if (getValue(entity, field.getName(), clazz) != null &&
						!getValue(entity, field.getName(), clazz).equals("")) {
					sbf.append(" and " + field.getName() + " like '%" +
							getValue(entity, field.getName(), clazz) + "%'");
				}
			}
		}
		System.out.println(sbf.toString());
		List<T> list = (List<T>) DBUnitHelper.executeQuery(sbf.toString(), clazz);
		return list;
	}

	/**
	 * 查询所有记录
	 * @return 包含所有记录的实体类列表
	 */
	public List<T> getAll() {
		String sql = "select * from " + clazzname;
		List<T> list = (List<T>) DBUnitHelper.executeQuery(sql, clazz);
		return list;
	}

	/**
	 * 执行SQL语句
	 * @param sql SQL语句
	 */
	public void executeSql(String sql) {
		System.out.println(sql);
		DBUnitHelper.executeUpdate(sql);
	}

	/**
	 * 根据主键修改记录
	 * @param entity 实体类对象
	 */
	public void update(T entity) {
		Integer value1 = (Integer) getValue(entity, getKey(), clazz);
		StringBuilder sbf = new StringBuilder();
		sbf.append("update " + clazzname + " set ");
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getName().equals(getKey()) && !field.getName().equals("dbutil")) {
				sbf.append(field.getName().toLowerCase()).append("='").
						append(getValue(entity, field.getName(), clazz)).append("'").append(",");
			}
		}
		sbf = new StringBuilder(sbf.substring(0, sbf.length() - 1));
		sbf.append(" where " + getKey() + "='" + value1 + "'");
		executeSql(sbf.toString());
	}

	/**
	 * 向数据库添加记录
	 * @param t 实体类对象
	 */
	public void add(T t) {
		StringBuilder sbf = new StringBuilder();
		sbf.append("insert into " + clazzname).append("(");
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getName().equals(getKey()) && !field.getName().equals("dbutil")) {
				sbf.append(field.getName().toLowerCase()).append(",");
			}
		}
		sbf = new StringBuilder(sbf.substring(0, sbf.length() - 1));
		sbf.append(") values(");
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getName().equals(getKey()) && !field.getName().equals("dbutil")) {
				sbf.append("'").append(getValue(t, field.getName(), clazz)).append("'").append(",");
			}
		}
		sbf = new StringBuilder(sbf.substring(0, sbf.length() - 1));
		sbf.append(")");
		executeSql(sbf.toString());
	}

	/**
	 * 根据主键值删除记录
	 * @param keyvalue 主键值
	 * @return 删除记录的数量
	 */
	public Integer delBykey(String keyvalue) {
		String sql = "delete from " + clazzname + " where " + getKey() + "=?";
		return DBUnitHelper.executeUpdate(sql, keyvalue);
	}

	/**
	 * 获取实体类的属性值
	 * @param entity 实体类对象
	 * @param fieldName 属性名
	 * @param clazz 实体类的Class对象
	 * @return 属性值
	 */
	public Object getValue(Object entity, String fieldName, Class clazz) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, clazz);
			Method wM = pd.getReadMethod();
			return wM.invoke(entity) == null ? "" : wM.invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置实体类的属性值
	 * @param fieldName 属性名
	 * @param clazz 实体类的Class对象
	 * @param o 实体类对象
	 * @param fieldValue 属性值
	 */
	public void setValue(String fieldName, Class clazz, Object o, Object fieldValue) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
			Method wM = pd.getWriteMethod();
			wM.invoke(o, fieldValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取实体类的主键名
	 * @return 主键名
	 */
	public String getKey() {
		try {
			for (Field field : clazz.getDeclaredFields()) {
				return field.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
