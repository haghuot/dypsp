package entity;
import java.util.List;
import dao.Dao;

/**
 * Guanliyuan类表示管理员，实现了Serializable接口
 */
public class Guanliyuan implements java.io.Serializable {

	private Integer id;

	/**
	 * 获取管理员ID
	 * @return 管理员ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置管理员ID
	 * @param id 管理员ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 默认构造函数
	 */
	public Guanliyuan() {
		super();
	}

	/**
	 * 带参数的构造函数，用于设置管理员ID
	 * @param id 管理员ID
	 */
	public Guanliyuan(Integer id) {
		super();
		this.id = id;
	}

	// Dao对象用于数据库操作
	Dao<Guanliyuan> dbutil = new Dao<Guanliyuan>(this);

	private String yonghuming;

	/**
	 * 获取管理员用户名
	 * @return 管理员用户名
	 */
	public String getYonghuming() {
		return this.yonghuming;
	}

	/**
	 * 设置管理员用户名
	 * @param yonghuming 管理员用户名
	 */
	public void setYonghuming(String yonghuming) {
		this.yonghuming = yonghuming;
	}

	private String mima;

	/**
	 * 获取管理员密码
	 * @return 管理员密码
	 */
	public String getMima() {
		return this.mima;
	}

	/**
	 * 设置管理员密码
	 * @param mima 管理员密码
	 */
	public void setMima(String mima) {
		this.mima = mima;
	}

	/**
	 * 向数据库添加新的管理员记录
	 */
	public void add() {
		dbutil.add(this);
	}

	/**
	 * 从数据库删除管理员记录
	 */
	public void delete() {
		dbutil.delBykey(id.toString());
	}

	/**
	 * 更新数据库中的管理员记录
	 */
	public void update() {
		dbutil.update(this);
	}

	/**
	 * 根据条件从数据库查询管理员记录
	 * @return 管理员记录列表
	 */
	public List<Guanliyuan> query() {
		return dbutil.query(this);
	}

	/**
	 * 根据ID从数据库查询管理员记录
	 * @return 管理员记录
	 */
	public Guanliyuan queryById() {
		return dbutil.queryByKey(id.toString()).get(0);
	}
}