package entity;

import java.util.List;

import dao.Dao;

import java.util.List;

/**
 * Changci类表示电影场次，实现了Serializable接口
 */
public class Changci implements java.io.Serializable {

	private Integer id;

	/**
	 * 获取场次ID
	 * @return 场次ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置场次ID
	 * @param id 场次ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 默认构造函数
	 */
	public Changci() {
		super();
	}

	/**
	 * 带参数的构造函数，用于设置场次ID
	 * @param id 场次ID
	 */
	public Changci(Integer id) {
		super();
		this.id = id;
	}

	// Dao对象用于数据库操作
	Dao<Changci> dbutil = new Dao<Changci>(this);

	private String changci;

	/**
	 * 获取场次名称
	 * @return 场次名称
	 */
	public String getChangci() {
		return this.changci;
	}

	/**
	 * 设置场次名称
	 * @param changci 场次名称
	 */
	public void setChangci(String changci) {
		this.changci = changci;
	}

	private String dianying;

	/**
	 * 获取电影名称
	 * @return 电影名称
	 */
	public String getDianying() {
		return this.dianying;
	}

	/**
	 * 设置电影名称
	 * @param dianying 电影名称
	 */
	public void setDianying(String dianying) {
		this.dianying = dianying;
	}

	private String riqi;

	/**
	 * 获取日期
	 * @return 日期
	 */
	public String getRiqi() {
		return this.riqi;
	}

	/**
	 * 设置日期
	 * @param riqi 日期
	 */
	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}

	private String shijian;

	/**
	 * 获取时间
	 * @return 时间
	 */
	public String getShijian() {
		return this.shijian;
	}

	/**
	 * 设置时间
	 * @param shijian 时间
	 */
	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	private String jiage;

	/**
	 * 获取价格
	 * @return 价格
	 */
	public String getJiage() {
		return this.jiage;
	}

	/**
	 * 设置价格
	 * @param jiage 价格
	 */
	public void setJiage(String jiage) {
		this.jiage = jiage;
	}

	private String haibao;

	/**
	 * 获取海报路径
	 * @return 海报路径
	 */
	public String getHaibao() {
		return this.haibao;
	}

	/**
	 * 设置海报路径
	 * @param haibao 海报路径
	 */
	public void setHaibao(String haibao) {
		this.haibao = haibao;
	}

	/**
	 * 向数据库添加新的场次记录
	 */
	public void add() {
		dbutil.add(this);
	}

	/**
	 * 从数据库删除场次记录
	 */
	public void delete() {
		dbutil.delBykey(id.toString());
	}

	/**
	 * 更新数据库中的场次记录
	 */
	public void update() {
		dbutil.update(this);
	}

	/**
	 * 根据条件从数据库查询场次记录
	 * @return 场次记录列表
	 */
	public List<Changci> query() {
		return dbutil.query(this);
	}

	/**
	 * 根据ID从数据库查询场次记录
	 * @return 场次记录
	 */
	public Changci queryById() {
		return dbutil.queryByKey(id.toString()).get(0);
	}
}
