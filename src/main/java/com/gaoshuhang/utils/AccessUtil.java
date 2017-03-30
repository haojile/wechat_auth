package com.gaoshuhang.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaoshuhang.domain.AccessToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AccessUtil
{

	/**
	 * 微信接入校验函数
	 * @param token 在接入时填写的token
	 * @param signature 微信服务器发送的签名
	 * @param timestamp 微信服务器发送的时间戳
	 * @param nonce 微信服务器发送的随机数
	 * @return 接入校验是否成功
	 */
	public static boolean checkSignature(String token, String signature, String timestamp, String nonce)
	{
		String[] arr = new String[]{token, timestamp, nonce};
		Arrays.sort(arr);
		String str = arr[0] + arr[1] + arr[2];
		MessageDigest sha1 = null;
		try
		{
			sha1 = MessageDigest.getInstance("SHA-1");
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("SHA-1 Algorithm not found.");
		}
		byte[] resultBytes = sha1.digest(str.getBytes());
		String result = byteToStr(resultBytes).toLowerCase();
		return result.equals(signature);
	}

	private static String byteToStr(byte[] digest)
	{
		String strDigest = "";

		for(byte b : digest)
		{
			strDigest += byteToHexStr(b);
		}
		return strDigest;
	}

	private static String byteToHexStr(byte b)
	{
		char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];

		return new String(tempArr);
	}

	/**
	 * 获取应用access token
	 *
	 * @param appid  公众号接入appid
	 * @param secret 公众号接入口令
	 * @return AccessToken对象
	 * @throws Exception 网络IO出错
	 */
	public static AccessToken getAccessToken(String appid, String secret) throws Exception
	{
		//组织请求参数
		String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
				appid + "&secret=" + secret;
		URL url = new URL(urlStr);
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
		httpsURLConnection.setRequestMethod("GET");
		httpsURLConnection.setDoInput(true);

		//接收json字符串
		InputStream inputStream = httpsURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String json = bufferedReader.readLine();

		//映射到AccessToken对象
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, AccessToken.class);
	}
}
