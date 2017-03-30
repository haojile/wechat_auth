package com.gaoshuhang.dao;

import com.gaoshuhang.domain.AccessToken;

/**
 * 一些和微信接口相关的全局配置信息,
 * 可能不是永久性的配置,内存实现还是数据库实现自定
 */
public interface AppConfDao
{
	/**
	 * 设置全局access_token
	 *
	 * @param accessToken AccessToken对象
	 */
	public void setAccessToken(AccessToken accessToken);

	/**
	 * 读取全局access_token
	 *
	 * @return AccessToken对象
	 */
	public AccessToken getAccessToken();
}
