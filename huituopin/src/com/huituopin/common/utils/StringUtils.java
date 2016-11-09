package com.huituopin.common.utils;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * String工具类
 * 
 * @author wuyingbing
 * @version 1.0
 * @since 2014-10-21 下午4:16:44
 * @category com.mapuni.system.util
 * @copyright
 */
public class StringUtils
{

	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	private static String string = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+=-αβγδεζηθικλμνξοπρστυφχψω";
	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * length=32
	 * 
	 * @return
	 */
	public static String getUUID_32()
	{
		String uuid = UUID.randomUUID().toString();
		char[] ar = uuid.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ar.length; i++)
		{
			if (ar[i] != '-')
			{
				sb.append(ar[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 按照指定参数生成指定长度（length）的随机字符串,可做主键
	 * 
	 * @param length
	 * @return
	 */
	public static String randomString(int length)
	{
		if (length < 1)
		{
			return null;
		}
		if (randGen == null)
		{
			randGen = new Random();
			numbersAndLetters = string.toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; ++i)
		{
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/** 对字符串进行MD5加密 */
	public static String encodeByMD5(String originString)
	{
		if (originString != null)
		{
			try
			{
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串
	 * 
	 * @param 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b)
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
