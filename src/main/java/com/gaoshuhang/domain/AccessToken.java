package com.gaoshuhang.domain;

import java.io.Serializable;

/**
 * AccessToken领域对象,是微信公众号的全局调用凭据,对应微信接口access_token的json字符串
 */
public class AccessToken implements Serializable
{
	private String access_token;
	private long expires_in;

	public String getAccess_token()
	{
		return access_token;
	}

	public void setAccess_token(String access_token)
	{
		this.access_token = access_token;
	}

	public long getExpires_in()
	{
		return expires_in;
	}

	public void setExpires_in(long expires_in)
	{
		this.expires_in = expires_in;
	}

	@Override
	public String toString()
	{
		return "AccessToken{" +
				"access_token='" + access_token + '\'' +
				", expires_in=" + expires_in +
				'}';
	}
}
