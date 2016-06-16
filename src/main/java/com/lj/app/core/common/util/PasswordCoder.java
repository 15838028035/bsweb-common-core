package com.lj.app.core.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 随机码以及页面随机码生成使用
 */
public class PasswordCoder {

	public static final String module = PasswordCoder.class.getName();

	private final int PASSWORD_SHORTEST_LEN = 4;
	private int minLen = 6;
	private int maxLen = 6;
	private boolean hasLowercase = true;
	private boolean hasUppercase = false;
	private boolean hasNumber = true;
	private boolean hasSpecial = false;
	private String specialChars = null;

	public boolean validateCode(String code) {

		if (code == null)
			return false;

		if (code.length() < minLen || code.length() > maxLen)
			return false;

		if (hasLowercase && !CodeUtil.containsLowercase(code))
			return false;
		if (hasUppercase && !CodeUtil.containsUppercase(code))
			return false;
		if (hasNumber && !CodeUtil.containsNumber(code))
			return false;
		if (hasSpecial && !CodeUtil.containsChar(code, specialChars))
			return false;

		return true;
	}

	public String generateCode() {

		StringBuffer sb = new StringBuffer();

		int codeLen = calculateCodeLen();

		// 1.确定合法字符
		List codeChars = createCodeChars();

		// 2.字符集重新排序
		codeChars = CodeUtil.randomOrder(codeChars);

		// 3.按顺序确定字符集数量
		codeChars = calculateCodeCharQuantity(codeChars, codeLen);

		printList(codeChars);

		// 4.生成编码
		CodeChar cc = null;
		Iterator it = codeChars.iterator();
		while (it.hasNext()) {
			cc = (CodeChar) it.next();
			if (cc.name.equals("lowercase"))
				sb.append(CodeUtil.generateRandomLowercase(cc.quantity));
			if (cc.name.equals("uppercase"))
				sb.append(CodeUtil.generateRandomUppercase(cc.quantity));
			if (cc.name.equals("number"))
				sb.append(CodeUtil.generateRandomNumber(cc.quantity));
			if (cc.name.equals("specialChars"))
				sb.append(CodeUtil.generateRandomChars(specialChars,
						cc.quantity));
		}
		String code = sb.toString();

		// 对编码重新排序
		code = CodeUtil.randomOrder(code);

		return code;
	}

	class CodeChar {
		String name;
		int quantity;

		public CodeChar(String name, int n) {
			this.name = name;
			this.quantity = n;
		}
	}

	private void printList(List list) {
		Iterator it = list.iterator();
		CodeChar cc = null;
		while (it.hasNext()) {
			cc = (CodeChar) it.next();
			System.out.println("cc=" + cc);
		}

	}

	private int calculateCodeLen() {
		int codeLen = PASSWORD_SHORTEST_LEN;

		if (minLen >= PASSWORD_SHORTEST_LEN && maxLen >= PASSWORD_SHORTEST_LEN) {
			BigDecimal difference = new BigDecimal(Math.random()
					* (maxLen - minLen));
			difference = difference.setScale(0, BigDecimal.ROUND_HALF_UP);
			codeLen = minLen + difference.intValue();
		}

		return codeLen;
	}

	private List createCodeChars() {
		List codeChars = new ArrayList();
		if (hasLowercase)
			codeChars.add(new CodeChar("lowercase", 1));
		if (hasUppercase)
			codeChars.add(new CodeChar("uppercase", 1));
		if (hasNumber)
			codeChars.add(new CodeChar("number", 1));
		if (hasSpecial)
			codeChars.add(new CodeChar("specialChars", 1));
		return codeChars;
	}

	private List calculateCodeCharQuantity(List codeChars, int codeLen) {
		CodeChar cc = null;
		int usedLen = 0;
		int quantity = 0;
		for (int i = 0; i < codeChars.size(); i++) {

			if (i == codeChars.size() - 1) {
				cc = (CodeChar) codeChars.get(i);
				cc.quantity = codeLen - usedLen;
				break;
			}
			quantity = CodeUtil.randomInt(codeLen - usedLen
					- (codeChars.size() - (i + 1)));
			if (quantity == 0)
				quantity = 1;
			usedLen += quantity;
			cc = (CodeChar) codeChars.get(i);
			cc.quantity = quantity;
		}
		return codeChars;
	}

	public static void main(String[] args) {
		PasswordCoder pc = new PasswordCoder();
		pc.setHasLowercase(false);
		pc.setHasUppercase(false);
		// pc.setHasNumber(true);
		pc.setHasSpecial(false);
		// pc.setSpecialChars("!@#$%^");
		pc.setMaxLen(4);
		pc.setMinLen(4);

		// String code = "se2edEfd";

		// System.out.println("validate:"+pc.validateCode(code));

		System.out.println(pc.generateCode());

	}

	public boolean isHasLowercase() {
		return hasLowercase;
	}

	public void setHasLowercase(boolean hasLowercase) {
		this.hasLowercase = hasLowercase;
	}

	public boolean isHasNumber() {
		return hasNumber;
	}

	public void setHasNumber(boolean hasNumber) {
		this.hasNumber = hasNumber;
	}

	public boolean isHasSpecial() {
		return hasSpecial;
	}

	public void setHasSpecial(boolean hasSpecial) {
		this.hasSpecial = hasSpecial;
	}

	public boolean isHasUppercase() {
		return hasUppercase;
	}

	public void setHasUppercase(boolean hasUppercase) {
		this.hasUppercase = hasUppercase;
	}

	public int getMaxLen() {
		return maxLen;
	}

	public void setMaxLen(int maxLen) {
		this.maxLen = maxLen;
	}

	public int getMinLen() {
		return minLen;
	}

	public void setMinLen(int minLen) {
		this.minLen = minLen;
	}

	public String getSpecialChars() {
		return specialChars;
	}

	public void setSpecialChars(String specialChars) {
		this.specialChars = specialChars;
	}
}
