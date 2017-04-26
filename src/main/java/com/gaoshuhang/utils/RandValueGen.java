package com.gaoshuhang.utils;

import java.util.Random;

public class RandValueGen
{
	public static String getRandString(int size)
	{
		return randGen("0123456789", size);
	}

	private static String randGen(String optionStr, int size)
	{
		char[] option = optionStr.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++)
		{
			Random random = new Random();
			int randInt = random.nextInt(26);
			sb.append(option[randInt]);
		}
		return sb.toString();
	}
}
