package com.gaoshuhang.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class NetworkIOUtil
{
	/**
	 * 从POST请求中以字符流形式读取请求体
	 *
	 * @param request httprequest对象,必须是post请求
	 * @return 请求体字符串
	 */
	public static String readFromPostRequest(HttpServletRequest request)
	{
		StringBuilder data = new StringBuilder();
		try
		{
			BufferedReader reader = request.getReader();
			String lineString;
			while ((lineString = reader.readLine()) != null)
			{
				data.append(lineString);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return data.toString();
	}

	/**
	 * 在http响应中写入字符流数据
	 *
	 * @param httpServletResponse http响应对象
	 * @param data 字符数据
	 */
	public void writeToHttpResponse(HttpServletResponse httpServletResponse, String data)
	{
		try
		{
			httpServletResponse.getWriter().write(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
