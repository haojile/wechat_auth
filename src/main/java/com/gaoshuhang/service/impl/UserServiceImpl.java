package com.gaoshuhang.service.impl;

import com.gaoshuhang.dao.CourierDao;
import com.gaoshuhang.dao.QrCodeDao;
import com.gaoshuhang.dao.SimulatedSession;
import com.gaoshuhang.dao.UserDao;
import com.gaoshuhang.dao.impl.CourierDaoImpl;
import com.gaoshuhang.dao.impl.QrCodeDaoImpl;
import com.gaoshuhang.dao.impl.SimulatedSessionImpl;
import com.gaoshuhang.dao.impl.UserDaoImpl;
import com.gaoshuhang.domain.Courier;
import com.gaoshuhang.domain.ReturnState;
import com.gaoshuhang.domain.Session;
import com.gaoshuhang.domain.User;
import com.gaoshuhang.global.UserGroup;
import com.gaoshuhang.global.UserState;
import com.gaoshuhang.service.UserService;
import com.gaoshuhang.utils.RandValueGen;

import java.awt.image.BufferedImage;

public class UserServiceImpl implements UserService
{

	private UserDao userDao = new UserDaoImpl();
	private SimulatedSession simulatedSession = new SimulatedSessionImpl();
	private QrCodeDao qrCodeDao = new QrCodeDaoImpl();

	@Override
	public ReturnState userUngroup(String userOpenId)
	{
		Session session = simulatedSession.getSession(userOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(userOpenId);

		//正在处于已分组但未绑定状态，跳回未分组状态
		if(currentState == UserState.USER_UNBIND)
		{
			returnState.setCategory("ungrouped");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.INIT);
			returnState.setReturnMessage("退出分组成功，请退出并重新进入公众号选择分组");
			returnState.setReturnBundle(null);

			//更新session
			session.setGroup(UserGroup.UNGROUPED);
			session.setState(UserState.INIT);
			simulatedSession.setSession(userOpenId, session);
		}
		//正在绑定状态中，处于交互式信息输入状态，不执行任何状态变化
		else if(currentState >= UserState.USER_BIND_INIT && currentState <= UserState.USER_BINDING)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定流程中，不能取消分组");
			returnState.setReturnBundle(null);
		}
		//处于已经成为用户的状态，取消分组绑定
		else if(currentState == UserState.USER_BINDED)
		{
			returnState.setCategory("ungrouped");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.INIT);
			returnState.setReturnMessage("账户已经退出分组并解绑");
			returnState.setReturnBundle(null);

			//更新session
			session.setGroup(UserGroup.UNGROUPED);
			session.setState(UserState.INIT);
			session.setUser(null);
			simulatedSession.setSession(userOpenId, session);

			//删除用户
			userDao.deleteUser(userOpenId);
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	@Override
	public ReturnState userBind(String userOpenId)
	{
		Session session = simulatedSession.getSession(userOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(userOpenId);

		//开始绑定流程
		if(currentState == UserState.USER_UNBIND)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.USER_BIND_INIT);
			returnState.setReturnMessage("请输入手机号");
			returnState.setReturnBundle(null);

			//更新session
			session.setState(UserState.USER_BIND_INIT);
			simulatedSession.setSession(userOpenId, session);
		}
		//等待用户输入手机号，不响应操作
		else if(currentState == UserState.USER_BIND_INIT)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定中，请先输入手机号");
			returnState.setReturnBundle(null);
		}
		//等待用户输入验证码，不响应操作
		else if(currentState == UserState.USER_BINDING)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定中，请先输入手机验证码");
			returnState.setReturnBundle(null);
		}
		//用户已绑定，不响应操作
		else if(currentState == UserState.USER_BINDED)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("账户已绑定，无法进行该操作");
			returnState.setReturnBundle(null);
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	@Override
	public ReturnState userUnbind(String userOpenId)
	{
		Session session = simulatedSession.getSession(userOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(userOpenId);

		//未绑定账户，不响应操作
		if(currentState == UserState.USER_UNBIND)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，账户未绑定");
			returnState.setReturnBundle(null);
		}
		//等待用户输入手机号，不响应操作
		else if(currentState == UserState.USER_BIND_INIT)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，正在绑定流程中，请先输入手机号");
			returnState.setReturnBundle(null);
		}
		//等待用户输入验证码，不响应操作
		else if(currentState == UserState.USER_BINDING)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，正在绑定流程中，请先输入手机验证码");
			returnState.setReturnBundle(null);
		}
		//已绑定状态，响应取消绑定操作，跳回未绑定状态
		else if(currentState == UserState.USER_BINDED)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.USER_UNBIND);
			returnState.setReturnMessage("解绑成功");
			returnState.setReturnBundle(null);

			//更新session
			session.setState(UserState.USER_UNBIND);
			session.setUser(null);

			//删除数据库
			userDao.deleteUser(userOpenId);
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	//todo 暂未实现
	@Override
	public ReturnState userQueryExpress(String userOpenId)
	{
		return null;
	}

	@Override
	public ReturnState userFetchExpress(String userOpenId)
	{
		Session session = simulatedSession.getSession(userOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(userOpenId);

		//未绑定成功
		if(currentState >= UserState.USER_UNBIND && currentState <= UserState.USER_BINDING)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行该操作，请先正确绑定账号");
			returnState.setReturnBundle(null);
		}
		//已正确绑定
		else if(currentState == UserState.USER_BINDED)
		{
			String str = userOpenId + "@" + RandValueGen.getRandString(4);
			session.getDataMap().put("validate_tempstr", str);

			BufferedImage bufferedImage = qrCodeDao.createQrCode(str);

			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，账户未绑定");
			returnState.setReturnBundle(bufferedImage);
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	@Override
	public ReturnState sendMessage(String userOpenId, String message)
	{
		Session session = simulatedSession.getSession(userOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(userOpenId);

		//未绑定账户，不响应操作
		if(currentState == UserState.USER_UNBIND)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("未识别信息，请先绑定账户");
			returnState.setReturnBundle(null);
		}
		//等待用户输入手机号
		else if(currentState == UserState.USER_BIND_INIT)
		{
			//输入的信息为手机号
			if(message.matches("[0-9]{11}$"))
			{
				returnState.setCategory("user");
				returnState.setPreviousState(session.getState());
				returnState.setCurrentState(UserState.USER_BINDING);
				returnState.setReturnMessage("请输入收到的短信验证码");
				returnState.setReturnBundle(null);

				//更新session
				session.setState(UserState.USER_BINDING);
				session.getDataMap().put("phone_num", message);

				String str = RandValueGen.getRandString(4);
				session.getDataMap().put("sms_vercode", str);
				//todo 向手机发送短信验证码

			}
			//输入的错误信息
			else
			{
				returnState.setCategory("user");
				returnState.setPreviousState(session.getState());
				returnState.setCurrentState(UserState.USER_BIND_INIT);
				returnState.setReturnMessage("输入手机号错误，请重新输入");
				returnState.setReturnBundle(null);
			}
		}
		//等待用户输入手机验证码
		else if(currentState == UserState.USER_BINDING)
		{
			//输入信息为验证码
			if(message.matches("^[0-9]{4}$"))
			{
				//验证码正确
				if(message.equals(session.getDataMap().get("sms_vercode")))
				{
					returnState.setCategory("user");
					returnState.setPreviousState(session.getState());
					returnState.setCurrentState(UserState.USER_BINDED);
					returnState.setReturnMessage("绑定成功");
					returnState.setReturnBundle(null);

					session.setState(UserState.USER_BINDED);
				}
				//验证码错误
				else
				{
					returnState.setCategory("user");
					returnState.setPreviousState(session.getState());
					returnState.setCurrentState(UserState.USER_BINDING);
					returnState.setReturnMessage("验证码错误，请重新输入");
					returnState.setReturnBundle(null);
				}
			}
			//输入错误信息
			else
			{
				returnState.setCategory("user");
				returnState.setPreviousState(session.getState());
				returnState.setCurrentState(UserState.USER_BINDING);
				returnState.setReturnMessage("验证码格式错误");
				returnState.setReturnBundle(null);
			}
		}
		//正常已绑定状态
		else if(currentState == UserState.USER_BINDED)
		{
			returnState.setCategory("user");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.USER_BINDED);
			//todo 正常情况下回复的信息
			returnState.setReturnMessage("？？？");
			returnState.setReturnBundle(null);
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	@Override
	public User getUser(String userOpenId)
	{
		return userDao.queryUserById(userOpenId);
	}
}
