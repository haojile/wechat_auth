package com.gaoshuhang.dao;

import com.gaoshuhang.domain.Session;

/**
 * 模拟session接口
 */
public interface SimulatedSession
{
	/**
	 * 通过openId获得session
	 *
	 * @param openId 用户openId
	 * @return 存在返回session对象, 不存在返回null,
	 * 注意session对象的User和Courier不要延迟加载
	 */
	public Session getSession(String openId);

	/**
	 * openId存在就更新session,不存在就创建session
	 *
	 * @param openId  用户openId
	 * @param session session对象
	 */
	public void setSession(String openId, Session session);
}
