package com.gaoshuhang.domain;

/**
 * 状态机接口返回结果类,
 * 是状态机的输出,
 * 前端控制器需要根据这个类响应用户
 */
public class ReturnState
{
	//用户openId
	private String openId;
	//用户分类
	private String category;
	//前一个状态
	private int previousState;
	//当前状态
	private int currentState;
	//返回给用户的提示消息,无提示消息返回null
	private String returnMessage;
	//返回其他类型资源,如BufferImage,
	// 需要前端根据业务逻辑强制转换后使用,无返回null
	private Object returnBundle;

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public int getPreviousState()
	{
		return previousState;
	}

	public void setPreviousState(int previousState)
	{
		this.previousState = previousState;
	}

	public int getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(int currentState)
	{
		this.currentState = currentState;
	}

	public String getReturnMessage()
	{
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage)
	{
		this.returnMessage = returnMessage;
	}

	public Object getReturnBundle()
	{
		return returnBundle;
	}

	public void setReturnBundle(Object returnBundle)
	{
		this.returnBundle = returnBundle;
	}
}
