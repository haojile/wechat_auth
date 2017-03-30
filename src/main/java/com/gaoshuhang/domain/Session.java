package com.gaoshuhang.domain;

/**
 * Session领域对象,对应数据库字段
 */
public class Session
{
	private Long sessionId;
	private String openId;
	private Integer state;
	private Integer group;
	private String validateStr;

	//对应数据库外键,由于应用IO规模比较小,
	// 方便起见这里不使用懒加载,dao层注意实现
	private User user;
	private Courier courier;

	public Long getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(Long sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	public Integer getState()
	{
		return state;
	}

	public void setState(Integer state)
	{
		this.state = state;
	}

	public Integer getGroup()
	{
		return group;
	}

	public void setGroup(Integer group)
	{
		this.group = group;
	}

	public String getValidateStr()
	{
		return validateStr;
	}

	public void setValidateStr(String validateStr)
	{
		this.validateStr = validateStr;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Courier getCourier()
	{
		return courier;
	}

	public void setCourier(Courier courier)
	{
		this.courier = courier;
	}

	@Override
	public String toString()
	{
		return "Session{" +
				"sessionId=" + sessionId +
				", openId='" + openId + '\'' +
				", state=" + state +
				", group=" + group +
				", validateStr='" + validateStr + '\'' +
				", user=" + user +
				", courier=" + courier +
				'}';
	}
}
