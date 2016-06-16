package com.lj.app.core.common.util;

import java.math.BigDecimal;
import java.util.List;

/**
 * 证随机码以及页面随机码生成使用
 */
public class CodeUtil {

	public static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
	public static final String NUMBER_CHARS = "0123456789";

	public static String generateRandomLowercase(int n) {
		return generateRandomChars(LOWERCASE_CHARS, n);
	}

	public static String generateRandomUppercase(int n) {
		return generateRandomChars(UPPERCASE_CHARS, n);
	}

	public static String generateRandomNumber(int n) {
		return generateRandomChars(NUMBER_CHARS, n);
	}

	public static int randomInt(int n) {
		BigDecimal random = new BigDecimal(Math.random() * n);
		random = random.setScale(0, BigDecimal.ROUND_HALF_UP);
		return random.intValue();
	}

	public static List randomOrder(List list) {
		Object temp = null;
		int idx = 0;
		for (int i = 0; i < list.size(); i++) {
			idx = CodeUtil.randomInt(list.size() - 1);
			temp = list.get(idx);
			list.remove(idx);
			list.add(temp);
		}
		return list;
	}

	public static boolean inCharSet(char c, String charSet) {
		if (charSet == null)
			return false;
		if (charSet.indexOf(c) > -1)
			return true;
		else
			return false;
	}

	public static boolean containsChar(String str, String charSet) {
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (inCharSet(c, charSet))
				return true;
		}
		return false;
	}

	public static boolean containsLowercase(String str) {
		return containsChar(str, LOWERCASE_CHARS);
	}

	public static boolean containsUppercase(String str) {
		return containsChar(str, UPPERCASE_CHARS);
	}

	public static boolean containsNumber(String str) {
		return containsChar(str, NUMBER_CHARS);
	}

	/**
	 * 根据给定的字符串种子，产生随机n位字符串
	 * 
	 * @param seed
	 *            种子
	 * @param n
	 *            产生的字符串长度
	 * @return
	 */
	public static String generateRandomChars(String seed, int n) {
		StringBuffer radomChars = new StringBuffer();
		BigDecimal random = null;
		for (int i = 0; i < n; i++) {
			random = new BigDecimal(Math.random() * (seed.length() - 1));
			random = random.setScale(0, BigDecimal.ROUND_HALF_UP);
			radomChars.append(seed.charAt(random.intValue()));
		}
		return radomChars.toString();
	}

	/**
	 * 对指定字符串随机重新排序
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String randomOrder(String oldStr) {
		StringBuffer newStr = new StringBuffer();
		String theChar = "";

		StringBuffer sb = new StringBuffer(oldStr);

		for (int j = 0; j < oldStr.length(); j++) {
			theChar = generateRandomChars(sb.toString(), 1);
			sb.deleteCharAt(sb.indexOf(theChar));
			newStr.append(theChar);
		}

		return newStr.toString();
	}
}
