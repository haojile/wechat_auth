package com.gaoshuhang.dao;

import com.gaoshuhang.domain.User;

import java.util.List;

/**
 * 和普通用户相关的数据访问接口
 */
public interface UserDao
{
	/**
	 * 增加用户
	 *
	 * @param user 用户对象,该对象中userId字段应忽略,deleted对象应忽略并置1
	 */
	public void insertUser(User user);

	/**
	 * 删除用户
	 *
	 * @param userOpenId 用户openId
	 */
	public void deleteUser(String userOpenId);

	/**
	 * 根据用户openId更新用户信息
	 *
	 * @param userOpenId 用户openId
	 * @param user       包含信息的用户对象,该对象中userId和openId字段应忽略
	 */
	public void updateUser(String userOpenId, User user);

	/**
	 * 根据用户Id查新用户
	 *
	 * @param userOpenId 用户openId
	 * @return 用户对象
	 */
	public User queryUserById(String userOpenId);

	/**
	 * 根据电话号码查询用户
	 *
	 * @param phoneNum 用户对象,不存在返回null
	 * @return 用户对象
	 */
	public User queryUserByPhoneNum(String phoneNum);

	/**
	 * 查询所有用户,用户量大时该方法有性能问题,仅用于测试
	 *
	 * @return 用户列表
	 */
	@Deprecated
	public List<User> queryUserList();

	/**
	 * 分页查询用户
	 *
	 * @param startPage 起始页码
	 * @param pageSize  页大小
	 * @return 用户列表
	 */
	public List<User> queryUserListByPage(int startPage, int pageSize);
}
