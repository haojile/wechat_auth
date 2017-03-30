package com.gaoshuhang.service;

import com.gaoshuhang.domain.Courier;
import com.gaoshuhang.domain.ReturnState;

import java.awt.image.BufferedImage;

public interface CourierService
{
	/**
	 * 快递员点击了取消分组按钮
	 * @param courierOpenId 快递员openId
	 * @return 状态机结果
	 */
	public ReturnState courierUngroup(String courierOpenId);

	/**
	 * 快递员点击了绑定按钮
	 * @param courierOpenId 快递员openId
	 * @return 状态机结果
	 */
	public ReturnState courierBind(String courierOpenId);

	/**
	 * 快递员点击了解绑按钮
	 * @param courierOpenId  快递员openId
	 * @return 状态机结果
	 */
	public ReturnState courierUnbind(String courierOpenId);

	/**
	 * 快递员点击了扫码认证按钮
	 * @param courierOpenId 快递员openId
	 * @param qrCode 二维码
	 * @return 状态机结果
	 */
	public ReturnState courierScan(String courierOpenId, BufferedImage qrCode);

	/**
	 * 发送text消息
	 * @param courierOpenId 快递员openId
	 * @return 状态机结果
	 */
	public ReturnState sendMessage(String courierOpenId);

	/**
	 * 根据 快递员openId获得快递员对象
	 * @param courierOpenId  快递员openId
	 * @return 快递员对象
	 */
	public Courier getCourier(String courierOpenId);
}
