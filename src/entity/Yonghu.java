package entity;

import java.util.List;

import dao.Dao;

/**
 * Yonghu类表示用户，实现了Serializable接口
 */
public class Yonghu implements java.io.Serializable {

	private Integer id;

	/**
	 * 获取用户ID
	 * @return 用户ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置用户ID
	 * @param id 用户ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 默认构造函数
	 */
	public Yonghu() {
		super();
	}

	/**
	 * 带参数的构造函数，用于设置用户ID
	 * @param id 用户ID
	 */
	public Yonghu(Integer id) {
		super();
		this.id = id;
	}

	// Dao对象用于数据库操作
	Dao<Yonghu> dbutil = new Dao<Yonghu>(this);

	private String zhanghao;

	/**
	 * 获取用户账号
	 * @return 用户账号
	 */
	public String getZhanghao() {
		return this.zhanghao;
	}

	/**
	 * 设置用户账号
	 * @param zhanghao 用户账号
	 */
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}

	private String mima;

	/**
	 * 获取用户密码
	 * @return 用户密码
	 */
	public String getMima() {
		return this.mima;
	}

	/**
	 * 设置用户密码
	 * @param mima 用户密码
	 */
	public void setMima(String mima) {
		this.mima = mima;
	}

	private String shoujihao;

	/**
	 * 获取用户手机号
	 * @return 用户手机号
	 */
	public String getShoujihao() {
		return this.shoujihao;
	}

	/**
	 * 设置用户手机号
	 * @param shoujihao 用户手机号
	 */
	public void setShoujihao(String shoujihao) {
		this.shoujihao = shoujihao;
	}

	private String shengri;

	/**
	 * 获取用户生日
	 * @return 用户生日
	 */
	public String getShengri() {
		return this.shengri;
	}

	/**
	 * 设置用户生日
	 * @param shengri 用户生日
	 */
	public void setShengri(String shengri) {
		this.shengri = shengri;
	}

	private String shenfenzhenghousiwei;

	/**
	 * 获取身份证后四位
	 * @return 身份证后四位
	 */
	public String getShenfenzhenghousiwei() {
		return this.shenfenzhenghousiwei;
	}

	/**
	 * 设置身份证后四位
	 * @param shenfenzhenghousiwei 身份证后四位
	 */
	public void setShenfenzhenghousiwei(String shenfenzhenghousiwei) {
		this.shenfenzhenghousiwei = shenfenzhenghousiwei;
	}

	/**
	 * 向数据库添加新的用户记录
	 */
	public void add() {
		dbutil.add(this);
	}

	/**
	 * 从数据库删除用户记录
	 */
	public void delete() {
		dbutil.delBykey(id.toString());
	}

	/**
	 * 更新数据库中的用户记录
	 */
	public void update() {
		dbutil.update(this);
	}

	/**
	 * 根据条件从数据库查询用户记录
	 * @return 用户记录列表
	 */
	public List<Yonghu> query() {
		return dbutil.query(this);
	}

	/**
	 * 根据ID从数据库查询用户记录
	 * @return 用户记录
	 */
	public Yonghu queryById() {
		return dbutil.queryByKey(id.toString()).get(0);
	}
}