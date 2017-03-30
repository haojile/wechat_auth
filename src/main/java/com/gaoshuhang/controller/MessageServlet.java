package com.gaoshuhang.controller;

import com.gaoshuhang.utils.AccessUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 和微信交互的主控制器
 */
@WebServlet(name = "MessageServlet", urlPatterns = "/")
public class MessageServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

	}

	/**
	 * 响应微信公众平台接入请求
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		String token = "gaoshuhang";

		if(AccessUtil.checkSignature(token, signature, timestamp, nonce))
		{
			resp.getWriter().write(echostr);
		}
	}
}
