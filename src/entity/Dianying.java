package entity;

import java.util.List;

import dao.Dao;

/**
 * Dianying类表示电影，实现了Serializable接口
 */
public class Dianying implements java.io.Serializable {

	private Integer id;

	/**
	 * 获取电影ID
	 * @return 电影ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置电影ID
	 * @param id 电影ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 默认构造函数
	 */
	public Dianying() {
		super();
	}

	/**
	 * 带参数的构造函数，用于设置电影ID
	 * @param id 电影ID
	 */
	public Dianying(Integer id) {
		super();
		this.id = id;
	}

	// Dao对象用于数据库操作
	Dao<Dianying> dbutil = new Dao<Dianying>(this);

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

	private String yingpianleixing;

	/**
	 * 获取影片类型
	 * @return 影片类型
	 */
	public String getYingpianleixing() {
		return this.yingpianleixing;
	}

	/**
	 * 设置影片类型
	 * @param yingpianleixing 影片类型
	 */
	public void setYingpianleixing(String yingpianleixing) {
		this.yingpianleixing = yingpianleixing;
	}

	private String shizhang;

	/**
	 * 获取时长
	 * @return 时长
	 */
	public String getShizhang() {
		return this.shizhang;
	}

	/**
	 * 设置时长
	 * @param shizhang 时长
	 */
	public void setShizhang(String shizhang) {
		this.shizhang = shizhang;
	}

	private String daoyan;

	/**
	 * 获取导演
	 * @return 导演
	 */
	public String getDaoyan() {
		return this.daoyan;
	}

	/**
	 * 设置导演
	 * @param daoyan 导演
	 */
	public void setDaoyan(String daoyan) {
		this.daoyan = daoyan;
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
	 * 向数据库添加新的电影记录
	 */
	public void add() {
		dbutil.add(this);
	}

	/**
	 * 从数据库删除电影记录
	 */
	public void delete() {
		dbutil.delBykey(id.toString());
	}

	/**
	 * 更新数据库中的电影记录
	 */
	public void update() {
		dbutil.update(this);
	}

	/**
	 * 根据条件从数据库查询电影记录
	 * @return 电影记录列表
	 */
	public List<Dianying> query() {
		return dbutil.query(this);
	}

	/**
	 * 根据ID从数据库查询电影记录
	 * @return 电影记录
	 */
	public Dianying queryById() {
		return dbutil.queryByKey(id.toString()).get(0);
	}
}