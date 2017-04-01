package com.gaoshuhang.dao.impl;

import com.gaoshuhang.dao.UserDao;
import com.gaoshuhang.domain.User;

import java.util.List;

public class UserDaoImpl implements UserDao
{
	@Override
	public void insertUser(User user)
	{

	}

	@Override
	public void deleteUser(String userOpenId)
	{

	}

	@Override
	public void updateUser(String userOpenId, User user)
	{

	}

	@Override
	public User queryUserById(String userOpenId)
	{
		return null;
	}

	@Override
	public User queryUserByPhoneNum(String phoneNum)
	{
		return null;
	}

	@Override
	public List<User> queryUserList()
	{
		return null;
	}

	@Override
	public List<User> queryUserListByPage(int startPage, int pageSize)
	{
		return null;
	}
}
