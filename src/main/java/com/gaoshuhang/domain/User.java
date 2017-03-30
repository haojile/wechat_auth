package com.gaoshuhang.domain;

import java.io.Serializable;

/**
 * 用户领域对象,对应数据库字段
 */
public class User implements Serializable
{
	private Long userId;
	private String userOpenId;
	private String phoneNum;
	private Integer deleted;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getUserOpenId()
	{
		return userOpenId;
	}

	public void setUserOpenId(String userOpenId)
	{
		this.userOpenId = userOpenId;
	}

	public String getPhoneNum()
	{
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	public Integer getDeleted()
	{
		return deleted;
	}

	public void setDeleted(Integer deleted)
	{
		this.deleted = deleted;
	}

	@Override
	public String toString()
	{
		return "User{" +
				"userId=" + userId +
				", userOpenId='" + userOpenId + '\'' +
				", phoneNum='" + phoneNum + '\'' +
				", deleted=" + deleted +
				'}';
	}
}
