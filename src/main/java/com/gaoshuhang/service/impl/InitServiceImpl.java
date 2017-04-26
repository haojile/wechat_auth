package com.gaoshuhang.service.impl;

import com.gaoshuhang.dao.CourierDao;
import com.gaoshuhang.dao.SimulatedSession;
import com.gaoshuhang.dao.UserDao;
import com.gaoshuhang.dao.impl.CourierDaoImpl;
import com.gaoshuhang.dao.impl.SimulatedSessionImpl;
import com.gaoshuhang.dao.impl.UserDaoImpl;
import com.gaoshuhang.domain.ReturnState;
import com.gaoshuhang.domain.Session;
import com.gaoshuhang.global.UserGroup;
import com.gaoshuhang.global.UserState;
import com.gaoshuhang.service.InitService;

import java.util.HashMap;

public class InitServiceImpl implements InitService
{
	private CourierDao courierDao = new CourierDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private SimulatedSession simulatedSession = new SimulatedSessionImpl();

	@Override
	public ReturnState SubscriptionFollowed(String openId)
	{
		Session session = new Session();
		session.setState(UserState.INIT);
		session.setDataMap(new HashMap<>());
		session.setOpenId(openId);
		session.setGroup(UserGroup.UNGROUPED);
		simulatedSession.setSession(openId, session);

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(openId);
		returnState.setCategory("ungrouped");
		returnState.setReturnMessage("欢迎使用微信认证系统，请在按钮中选择您的角色");
		returnState.setPreviousState(UserState.INIT);
		returnState.setCurrentState(UserState.INIT);
		returnState.setReturnBundle(null);

		return returnState;
	}

	@Override
	public void SubscriptionUnFollowed(String openId)
	{
		simulatedSession.removeSession(openId);
		courierDao.deleteCourier(openId);
		userDao.deleteUser(openId);
	}

	@Override
	public ReturnState clickImUser(String openId)
	{
		Session session = simulatedSession.getSession(openId);

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(openId);

		returnState.setCategory("user");
		returnState.setReturnMessage("欢迎使用微信认证系统，请按流程进行用户绑定");
		returnState.setPreviousState(UserState.INIT);
		returnState.setCurrentState(UserState.USER_UNBIND);
		returnState.setReturnBundle(null);

		session.setGroup(UserGroup.USER);

		return returnState;
	}

	@Override
	public ReturnState clickImCourier(String openId)
	{
		Session session = simulatedSession.getSession(openId);

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(openId);

		returnState.setCategory("courier");
		returnState.setReturnMessage("欢迎使用微信认证系统，请按流程进行快递员绑定");
		returnState.setPreviousState(UserState.INIT);
		returnState.setCurrentState(UserState.COURIER_UNBIND);
		returnState.setReturnBundle(null);

		session.setGroup(UserGroup.COURIER);

		return returnState;
	}

	@Override
	public ReturnState sendMessage(String openId, String message)
	{
		Session session = simulatedSession.getSession(openId);

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(openId);

		returnState.setCategory("ungrouped");
		returnState.setReturnMessage("欢迎使用微信认证系统，请在按钮中选择您的角色");
		returnState.setPreviousState(UserState.INIT);
		returnState.setCurrentState(UserState.INIT);
		returnState.setReturnBundle(null);

		return returnState;
	}
}
