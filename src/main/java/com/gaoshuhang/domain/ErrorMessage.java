package com.gaoshuhang.domain;

import java.io.Serializable;

/**
 * 对应微信接口错误消息json字符串
 */
public class ErrorMessage implements Serializable
{
	private String errcode;
	private String errmsg;

	public String getErrcode()
	{
		return errcode;
	}

	public void setErrcode(String errcode)
	{
		this.errcode = errcode;
	}

	public String getErrmsg()
	{
		return errmsg;
	}

	public void setErrmsg(String errmsg)
	{
		this.errmsg = errmsg;
	}

	@Override
	public String toString()
	{
		return "ErrorMessage{" +
				"errcode='" + errcode + '\'' +
				", errmsg='" + errmsg + '\'' +
				'}';
	}
}
