package com.gaoshuhang.service;

import com.gaoshuhang.domain.ReturnState;

public interface InitService
{
	/**
	 * 用户关注该公众号,将用户存入session
	 * @param openId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState SubscriptionFollowed(String openId);

	/**
	 * 用户取消关注了公众号
	 * @param openId 用户openId
	 */
	public void SubscriptionUnFollowed(String openId);

	/**
	 * 用户点击了我是用户按钮,开始用户类型绑定
	 * @param openId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState clickImUser(String openId);

	/**
	 * 用户点击了我是快递员按钮,开始快递员类型绑定
	 * @param openId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState clickImCourier(String openId);

	/**
	 * 发送text消息
	 * @param openId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState sendMessage(String openId);
}
