package com.gaoshuhang.service.impl;

import com.gaoshuhang.dao.CourierDao;
import com.gaoshuhang.dao.SimulatedSession;
import com.gaoshuhang.dao.impl.CourierDaoImpl;
import com.gaoshuhang.dao.impl.SimulatedSessionImpl;
import com.gaoshuhang.domain.Courier;
import com.gaoshuhang.domain.ReturnState;
import com.gaoshuhang.domain.Session;
import com.gaoshuhang.global.UserGroup;
import com.gaoshuhang.global.UserState;
import com.gaoshuhang.service.CourierService;

import java.awt.image.BufferedImage;

public class CourierServiceImpl implements CourierService
{

	private CourierDao courierDao = new CourierDaoImpl();
	private SimulatedSession simulatedSession = new SimulatedSessionImpl();

	@Override
	public ReturnState courierUngroup(String courierOpenId)
	{
		Session session = simulatedSession.getSession(courierOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(courierOpenId);

		//正在处于已分组但未绑定状态，跳回未分组状态
		if(currentState == UserState.COURIER_UNBIND)
		{
			returnState.setCategory("ungrouped");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.INIT);
			returnState.setReturnMessage("退出分组成功，请退出并重新进入公众号选择分组");
			returnState.setReturnBundle(null);

			//更新session
			session.setGroup(UserGroup.UNGROUPED);
			session.setState(UserState.INIT);
			simulatedSession.setSession(courierOpenId, session);
		}
		//正在绑定状态中，处于交互式信息输入状态，不执行任何状态变化
		else if(currentState >= UserState.COURIER_BIND_INIT && currentState <= UserState.COURIER_BINDING_3)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定流程中，不能取消分组");
			returnState.setReturnBundle(null);
		}
		//正在等待审核，不执行任何状态变化
		else if(currentState == UserState.COURIER_BINDING_4)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在审核中，不能取消分组");
			returnState.setReturnBundle(null);
		}
		//处于已经成为快递员的状态，取消分组绑定
		else if(currentState == UserState.COURIER_BINDED)
		{
			returnState.setCategory("ungrouped");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.INIT);
			returnState.setReturnMessage("账户已经退出分组并解绑");
			returnState.setReturnBundle(null);

			//更新session
			session.setGroup(UserGroup.UNGROUPED);
			session.setState(UserState.INIT);
			session.setCourier(null);
			simulatedSession.setSession(courierOpenId, session);

			//删除用户
			courierDao.deleteCourier(courierOpenId);
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	@Override
	public ReturnState courierBind(String courierOpenId)
	{
		Session session = simulatedSession.getSession(courierOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(courierOpenId);

		//开始绑定流程
		if(currentState == UserState.COURIER_UNBIND)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.COURIER_BIND_INIT);
			returnState.setReturnMessage("请输入手机号");
			returnState.setReturnBundle(null);

			//更新session
			session.setState(UserState.COURIER_BIND_INIT);
			simulatedSession.setSession(courierOpenId, session);
		}
		//等待用户输入手机号，不响应操作
		else if(currentState == UserState.COURIER_BIND_INIT)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定中，请先输入手机号");
			returnState.setReturnBundle(null);
		}
		//等待用户输入验证码，不响应操作
		else if(currentState == UserState.COURIER_BINDING_1)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定中，请先输入短信验证码");
			returnState.setReturnBundle(null);
		}
		//等待用户输入姓名，不响应操作
		else if(currentState == UserState.COURIER_BINDING_2)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定中，请先输入姓名");
			returnState.setReturnBundle(null);
		}
		//等待用户输入身份证号，不响应操作
		else if(currentState == UserState.COURIER_BINDING_3)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在绑定中，请先输入身份证号");
			returnState.setReturnBundle(null);
		}
		//等待快递员审核，不响应操作
		else if(currentState == UserState.COURIER_BINDING_4)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("正在等待审核，无法进行绑定操作");
			returnState.setReturnBundle(null);
		}
		//快递员已绑定，不响应操作
		else if(currentState == UserState.COURIER_BINDED)
		{
			returnState.setCategory("courier");
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
	public ReturnState courierUnbind(String courierOpenId)
	{
		Session session = simulatedSession.getSession(courierOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(courierOpenId);

		//未绑定账户，不响应操作
		if(currentState == UserState.COURIER_UNBIND)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，账户未绑定");
			returnState.setReturnBundle(null);
		}
		//等待用户输入手机号，不响应操作
		else if(currentState == UserState.COURIER_BIND_INIT)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，正在绑定流程中，请先输入手机号");
			returnState.setReturnBundle(null);
		}
		//等待用户输入验证码，不响应操作
		else if(currentState == UserState.COURIER_BINDING_1)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，正在绑定流程中，请先输入验证码");
			returnState.setReturnBundle(null);
		}
		//等待用户输入姓名，不响应操作
		else if(currentState == UserState.COURIER_BINDING_2)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，正在绑定流程中，请先输入姓名");
			returnState.setReturnBundle(null);
		}
		//等待用户输入身份证号，不响应操作
		else if(currentState == UserState.COURIER_BINDING_3)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，正在绑定流程中，请先输入身份证号");
			returnState.setReturnBundle(null);
		}
		//等待快递员审核，不响应操作
		else if(currentState == UserState.COURIER_BINDING_4)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行解绑操作，正在绑定流程中，请等待审核");
			returnState.setReturnBundle(null);
		}
		//已绑定状态，响应取消绑定操作，跳回未绑定状态
		else if(currentState == UserState.COURIER_BINDED)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.COURIER_UNBIND);
			returnState.setReturnMessage("解绑成功");
			returnState.setReturnBundle(null);

			//更新session
			session.setState(UserState.COURIER_UNBIND);
			session.setCourier(null);

			//删除数据库
			courierDao.deleteCourier(courierOpenId);
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	@Override
	public ReturnState courierScan(String courierOpenId, BufferedImage qrCode)
	{
		Session session = simulatedSession.getSession(courierOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(courierOpenId);

		//未绑定账户，不响应操作
		if(currentState == UserState.COURIER_UNBIND)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行扫码认证操作，账户未绑定");
			returnState.setReturnBundle(null);
		}
		//正在绑定状态中，处于交互式信息输入状态，不执行任何状态变化
		else if(currentState >= UserState.COURIER_BIND_INIT && currentState <= UserState.COURIER_BINDING_3)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行扫码认证操作，正在绑定流程中");
			returnState.setReturnBundle(null);
		}
		//正在等待审核，不执行任何状态变化
		else if(currentState == UserState.COURIER_BINDING_4)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("无法进行扫码认证操作，正在审核中");
			returnState.setReturnBundle(null);
		}
		//已绑定状态，响应扫码认证操作
		else if(currentState == UserState.COURIER_BINDED)
		{
			//TODO parse qrcode and authenticate
		}
		//理论上无法产生这种状态转移
		else
		{
			throw new RuntimeException("该状态转移操作不存在");
		}

		return returnState;
	}

	@Override
	public ReturnState sendMessage(String courierOpenId, String message)
	{
		Session session = simulatedSession.getSession(courierOpenId);
		int currentState = session.getState();

		ReturnState returnState = new ReturnState();
		returnState.setOpenId(courierOpenId);

		//未绑定账户，不响应操作
		if(currentState == UserState.COURIER_UNBIND)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(session.getState());
			returnState.setReturnMessage("未识别信息，请先绑定账户");
			returnState.setReturnBundle(null);
		}
		//等待用户输入手机号
		else if(currentState == UserState.COURIER_BIND_INIT)
		{
			//输入的信息为手机号
			if(message.matches("[0-9]{11}$"))
			{
				returnState.setCategory("courier");
				returnState.setPreviousState(session.getState());
				returnState.setCurrentState(UserState.COURIER_BINDING_1);
				returnState.setReturnMessage("请输入收到的短信验证码");
				returnState.setReturnBundle(null);

				//更新session
				session.setState(UserState.COURIER_BINDING_1);
				session.getDataMap().put("phone_num", message);

				//todo 生成随机短信验证码存入session

				//todo 向手机发送短信验证码

			}
			//输入的错误信息
			else
			{
				returnState.setCategory("courier");
				returnState.setPreviousState(session.getState());
				returnState.setCurrentState(UserState.COURIER_BINDING_1);
				returnState.setReturnMessage("输入手机号错误，请重新输入");
				returnState.setReturnBundle(null);
			}
		}
		//等待用户输入手机验证码
		else if(currentState == UserState.COURIER_BINDING_1)
		{
			//输入信息为验证码

			//输入错误信息
		}
		//等待用户输入姓名
		else if(currentState == UserState.COURIER_BINDING_2)
		{
			//输入信息为姓名
			//输入错误信息
		}
		//等待用户输入身份证号
		else if(currentState == UserState.COURIER_BINDING_3)
		{
			//输入信息为身份证号
			//输入错误信息
		}
		//等待审核
		else if(currentState == UserState.COURIER_BINDING_4)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.COURIER_BINDING_1);
			returnState.setReturnMessage("请耐心等待审核结果");
			returnState.setReturnBundle(null);
		}
		//正常已绑定状态
		else if(currentState == UserState.COURIER_BINDED)
		{
			returnState.setCategory("courier");
			returnState.setPreviousState(session.getState());
			returnState.setCurrentState(UserState.COURIER_BINDING_1);
			//todo 正常情况下回复信息
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
	public Courier getCourier(String courierOpenId)
	{
		return courierDao.queryCourierById(courierOpenId);
	}
}
