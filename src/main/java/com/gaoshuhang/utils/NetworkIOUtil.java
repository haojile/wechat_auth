package com.gaoshuhang.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class NetworkIOUtil
{
	/**
	 * 从POST请求中以字符流形式读取请求体
	 * @param request httprequest对象,必须是post请求
	 * @return 请求体字符串
	 */
	private static String readFromPostRequest(HttpServletRequest request)
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
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return data.toString();
	}
}
