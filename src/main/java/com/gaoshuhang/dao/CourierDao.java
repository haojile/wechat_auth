package com.gaoshuhang.dao;

import com.gaoshuhang.domain.Courier;

import java.util.List;

/**
 * 和快递员相关的数据访问接口
 */
public interface CourierDao
{
	/**
	 * 增加快递员
	 *
	 * @param courier 快递员对象,该对象中userId字段应忽略,deleted对象应忽略并置1
	 */
	public void insertCourier(Courier courier);

	/**
	 * 删除快递员对象
	 *
	 * @param courierOpenId 快递员openId
	 */
	public void deleteCourier(String courierOpenId);

	/**
	 * 根据openId查找快递员,更新快递员对象信息
	 *
	 * @param courierOpenId 快递员openId
	 * @param courier       包含新信息的快递员对象,该对象中userId和openId字段应忽略
	 */
	public void updateCourier(String courierOpenId, Courier courier);

	/**
	 * 根据openId查找快递员
	 *
	 * @param courierOpenId 快递员openId
	 * @return 快递员对象
	 */
	public Courier queryCourierById(String courierOpenId);

	/**
	 * 根据电话号码查询快递员
	 *
	 * @param phoneNum 电话号码
	 * @return 快递员对象
	 */
	public Courier queryCourierByPhoneNum(String phoneNum);

	/**
	 * 根据名字查询快递员
	 *
	 * @param name 快递员名字
	 * @return 同名快递员
	 */
	public List<Courier> queryCourierByName(String name);

	/**
	 * 查询所有快递员,该方法在用户量大时有性能问题,仅用于测试
	 *
	 * @return 快递员列表
	 */
	@Deprecated
	public List<Courier> queryCourierList();

	/**
	 * 分页查询所有快递员
	 *
	 * @param startPage 起始页
	 * @param pageSize  页大小
	 * @return 快递员列表
	 */
	public List<Courier> queryCourierListByPage(int startPage, int pageSize);
}
