package dao;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * DBUnitHelper类用于数据库连接、数据库创建和表创建等操作
 */
public class DBUnitHelper {

	// 数据库名
	static String dbname = "aproject";
	// 数据库用户名
	static String username = "root";
	// 数据库密码
	static String password = "13875557458abc";

	/**
	 * 获取数据库连接
	 * @return 数据库连接
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + dbname + "?characterEncoding=utf-8", username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 创建数据库
	 * @return 执行结果信息
	 */
	public static String createDB() {
		Connection connect = null;
		try {
			DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + "?characterEncoding=utf-8", username, password);
			connect.createStatement().execute("CREATE DATABASE  if NOT EXISTS " + dbname + ";");
			return "执行成功";
		} catch (Exception e) {
			return "数据库用户名或密码错误";
		} finally {
			try {
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据实体类生成数据库表
	 */
	public static void createTables() {
		File javafile = new File("src//entity");
		File[] files = javafile.listFiles();
		for (int i = 0; i < files.length; i++) {
			String clazzpath = "entity." + files[i].getName().replace(".java", "");
			String tablename = files[i].getName().replace(".java", "").toLowerCase();
			String droptable = "DROP TABLE IF EXISTS `" + tablename + "`;";
			try {
				getConn().createStatement().executeUpdate(droptable);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			StringBuilder sbf = new StringBuilder();
			sbf.append("CREATE TABLE if not exists `" + tablename + "` (");
			List<String> list = getAllColumns(clazzpath);
			for (int j = 0; j < list.size(); j++) {
				if (j == 0) {
					sbf.append("  `" + list.get(j) + "` int(11) NOT NULL auto_increment,");
				} else {
					sbf.append("  `" + list.get(j) + "` varchar(200) default NULL,");
				}
			}
			sbf.append("  PRIMARY KEY  (`" + list.get(0) + "`)");
			sbf.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			try {
				getConn().createStatement().executeUpdate(sbf.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取实体类的所有字段名
	 * @param classPath 实体类路径
	 * @return 实体类字段名列表
	 */
	public static List<String> getAllColumns(String classPath) {
		List<String> list = new ArrayList<>();
		try {
			Class clazz = Class.forName(classPath);
			for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
				if (!field.getGenericType().getTypeName().equals("byte[]")) {
					list.add(field.getName());
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行更新操作
	 * @param sql SQL语句
	 * @param objects SQL语句参数
	 * @return 更新的记录数
	 */
	public static Integer executeUpdate(String sql, Object... objects) {
		Connection conn = getConn();
		QueryRunner qr = new QueryRunner();
		Integer rtn = 0;
		try {
			if (objects == null) {
				rtn = qr.update(conn, sql);
			} else {
				rtn = qr.update(conn, sql, objects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rtn;
	}

	/**
	 * 执行更新操作
	 * @param sql SQL语句
	 * @return 更新的记录数
	 */
	public static Integer executeUpdate(String sql) {
		return executeUpdate(sql, null);
	}

	/**
	 * 执行查询操作
	 * @param sql SQL语句
	 * @param cls 结果类的Class对象
	 * @param objects SQL语句参数
	 * @param <T> 泛型
	 * @return 查询结果列表
	 */
	public static <T> List<T> executeQuery(String sql, Class<T> cls, Object... objects) {
		Connection conn = getConn();
		List<T> list = null;
		try {
			QueryRunner rq = new QueryRunner();
			if (objects == null) {
				list = rq.query(conn, sql, new BeanListHandler<T>(cls));
			} else {
				list = rq.query(conn, sql, new BeanListHandler<T>(cls), objects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 执行查询操作
	 * @param sql SQL语句
	 * @param cls 结果类的Class对象
	 * @param <T> 泛型
	 * @return 查询结果列表
	 */
	public static <T> List<T> executeQuery(String sql, Class<T> cls) {
		return executeQuery(sql, cls, null);
	}
}