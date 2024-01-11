package entity;

import java.util.List;

import dao.Dao;

/**
 * Changcidingdan类表示电影场次订单，实现了Serializable接口
 */
public class Changcidingdan implements java.io.Serializable {

	private Integer id;

	/**
	 * 获取订单ID
	 * @return 订单ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置订单ID
	 * @param id 订单ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 默认构造函数
	 */
	public Changcidingdan() {
		super();
	}

	/**
	 * 带参数的构造函数，用于设置订单ID
	 * @param id 订单ID
	 */
	public Changcidingdan(Integer id) {
		super();
		this.id = id;
	}

	// Dao对象用于数据库操作
	Dao<Changcidingdan> dbutil = new Dao<Changcidingdan>(this);

	private String dingdanhao;

	/**
	 * 获取订单号
	 * @return 订单号
	 */
	public String getDingdanhao() {
		return this.dingdanhao;
	}

	/**
	 * 设置订单号
	 * @param dingdanhao 订单号
	 */
	public void setDingdanhao(String dingdanhao) {
		this.dingdanhao = dingdanhao;
	}

	private String changci;

	/**
	 * 获取场次
	 * @return 场次
	 */
	public String getChangci() {
		return this.changci;
	}

	/**
	 * 设置场次
	 * @param changci 场次
	 */
	public void setChangci(String changci) {
		this.changci = changci;
	}

	private String dianying;

	/**
	 * 获取电影
	 * @return 电影
	 */
	public String getDianying() {
		return this.dianying;
	}

	/**
	 * 设置电影
	 * @param dianying 电影
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

	private String dingdanshijian;

	/**
	 * 获取订单时间
	 * @return 订单时间
	 */
	public String getDingdanshijian() {
		return this.dingdanshijian;
	}

	/**
	 * 设置订单时间
	 * @param dingdanshijian 订单时间
	 */
	public void setDingdanshijian(String dingdanshijian) {
		this.dingdanshijian = dingdanshijian;
	}

	private String dingdanzongjia;

	/**
	 * 获取订单总价
	 * @return 订单总价
	 */
	public String getDingdanzongjia() {
		return this.dingdanzongjia;
	}

	/**
	 * 设置订单总价
	 * @param dingdanzongjia 订单总价
	 */
	public void setDingdanzongjia(String dingdanzongjia) {
		this.dingdanzongjia = dingdanzongjia;
	}

	private String zuowei;

	/**
	 * 获取座位
	 * @return 座位
	 */
	public String getZuowei() {
		return this.zuowei;
	}

	/**
	 * 设置座位
	 * @param zuowei 座位
	 */
	public void setZuowei(String zuowei) {
		this.zuowei = zuowei;
	}

	private String yonghu;

	/**
	 * 获取用户
	 * @return 用户
	 */
	public String getYonghu() {
		return this.yonghu;
	}

	/**
	 * 设置用户
	 * @param yonghu 用户
	 */
	public void setYonghu(String yonghu) {
		this.yonghu = yonghu;
	}

	/**
	 * 向数据库添加新的场次订单记录
	 */
	public void add() {
		dbutil.add(this);
	}

	/**
	 * 从数据库删除场次订单记录
	 */
	public void delete() {
		dbutil.delBykey(id.toString());
	}

	/**
	 * 更新数据库中的场次订单记录
	 */
	public void update() {
		dbutil.update(this);
	}

	/**
	 * 根据条件从数据库查询场次订单记录
	 * @return 场次订单记录列表
	 */
	public List<Changcidingdan> query() {
		return dbutil.query(this);
	}

	/**
	 * 根据ID从数据库查询场次订单记录
	 * @return 场次订单记录
	 */
	public Changcidingdan queryById() {
		return dbutil.queryByKey(id.toString()).get(0);
	}
}