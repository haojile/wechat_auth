package com.gaoshuhang.service;

import com.gaoshuhang.domain.Express;
import com.gaoshuhang.domain.ReturnState;
import com.gaoshuhang.domain.User;

import java.awt.image.BufferedImage;

public interface UserService
{
	/**
	 * 用户点击了取消分组按钮
	 * @param userOpenId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState userUngroup(String userOpenId);

	/**
	 * 用户点击了绑定按钮
	 * @param userOpenId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState userBind(String userOpenId);

	/**
	 * 用户点击了取消绑定按钮
	 * @param userOpenId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState userUnbind(String userOpenId);

	/**
	 * 注意：该方法暂不支持,后续再加上这个功能
	 *
	 * 用户点击了查询快递按钮
	 * @param userOpenId 用户openId
	 * @return 状态机结果,Object字段为快递(包含所有快递单号)对象
	 */
	@Deprecated
	public ReturnState userQueryExpress(String userOpenId);

	/**
	 * 用户点击了取件按钮
	 * @param userOpenId 用户openId
	 * @return 状态机结果,Object字段为取件认证二维码BufferedImage对象
	 */
	public ReturnState userFetchExpress(String userOpenId);

	/**
	 * 发送text消息
	 * @param userOpenId 用户openId
	 * @return 状态机结果
	 */
	public ReturnState sendMessage(String userOpenId);

	/**
	 * 根据用户ID查询用户
	 * @param userOpenId 用户openId
	 * @return 用户对象
	 */
	public User getUser(String userOpenId);
}
