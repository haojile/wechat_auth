package com.gaoshuhang.domain;

/**
 * 快递员领域对象,对应数据库字段
 */
public class Courier
{
	private Long userId;
	private String openId;
	private String phoneNum;
	private String cardId;
	private Integer deleted;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	public String getPhoneNum()
	{
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	public String getCardId()
	{
		return cardId;
	}

	public void setCardId(String cardId)
	{
		this.cardId = cardId;
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
		return "Courier{" +
				"userId=" + userId +
				", openId='" + openId + '\'' +
				", phoneNum='" + phoneNum + '\'' +
				", cardId='" + cardId + '\'' +
				", deleted=" + deleted +
				'}';
	}
}
