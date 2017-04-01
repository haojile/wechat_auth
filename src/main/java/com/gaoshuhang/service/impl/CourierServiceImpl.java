package com.gaoshuhang.service.impl;

import com.gaoshuhang.domain.Courier;
import com.gaoshuhang.domain.ReturnState;
import com.gaoshuhang.service.CourierService;

import java.awt.image.BufferedImage;

public class CourierServiceImpl implements CourierService
{
	@Override
	public ReturnState courierUngroup(String courierOpenId)
	{
		return null;
	}

	@Override
	public ReturnState courierBind(String courierOpenId)
	{
		return null;
	}

	@Override
	public ReturnState courierUnbind(String courierOpenId)
	{
		return null;
	}

	@Override
	public ReturnState courierScan(String courierOpenId, BufferedImage qrCode)
	{
		return null;
	}

	@Override
	public ReturnState sendMessage(String courierOpenId)
	{
		return null;
	}

	@Override
	public Courier getCourier(String courierOpenId)
	{
		return null;
	}
}
