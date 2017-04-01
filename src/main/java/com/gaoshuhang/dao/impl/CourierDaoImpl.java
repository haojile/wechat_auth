package com.gaoshuhang.dao.impl;

import com.gaoshuhang.dao.CourierDao;
import com.gaoshuhang.domain.Courier;

import java.util.List;

public class CourierDaoImpl implements CourierDao
{
	@Override
	public void insertCourier(Courier courier)
	{

	}

	@Override
	public void deleteCourier(String courierOpenId)
	{

	}

	@Override
	public void updateCourier(String courierOpenId, Courier courier)
	{

	}

	@Override
	public Courier queryCourierById(String courierOpenId)
	{
		return null;
	}

	@Override
	public Courier queryCourierByPhoneNum(String phoneNum)
	{
		return null;
	}

	@Override
	public List<Courier> queryCourierByName(String name)
	{
		return null;
	}

	@Override
	public List<Courier> queryCourierList()
	{
		return null;
	}

	@Override
	public List<Courier> queryCourierListByPage(int startPage, int pageSize)
	{
		return null;
	}
}
