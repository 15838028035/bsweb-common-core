package com.lj.app.core.common.util;

/**
 * 内容摘要:处理字符串类型共用类
 */

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lj.app.core.common.properties.PropertiesUtil;

/**
 * 
 *         内容摘要:处理数字类型共用类.
 */
public class StringUtil {

    public static Date strToDate(String s_DateStr, String s_FormatStr) throws Exception {
        if (null == s_DateStr) {
            return null;
        }
        SimpleDateFormat vDf = new SimpleDateFormat(s_FormatStr);
        return vDf.parse(s_DateStr);
    }

    /**
     * 
     * 方法描述：分钟转换为小时 ；创建人：靖永安 ； 创建日期：2012-5-9
     * 
     * @param minute
     * @return
     */
    public static String minute2Hour(String minute) {
        BigDecimal min = new BigDecimal(minute);
        return StringUtil.getRound(min, BigDecimal.valueOf(60), 2);
    }

    /**
     * 把汉字转成16进制
     * 
     * @param src
     * @return
     */
    public static String unescape(String src) {
        if (src == null)
            return null;

        // src.replaceAll("(", "%u0178j");
        // src.replaceAll(")", "%CA%BA");
        try {
            StringBuffer tmp = new StringBuffer();
            tmp.ensureCapacity(src.length());
            int lastPos = 0, pos = 0;
            char ch;
            while (lastPos < src.length()) {
                pos = src.indexOf("%", lastPos);
                if (pos == lastPos) {
                    if (src.charAt(pos + 1) == 'u') {
                        ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                        tmp.append(ch);
                        lastPos = pos + 6;
                    } else {
                        ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                        tmp.append(ch);
                        lastPos = pos + 3;
                    }
                } else {
                    if (pos == -1) {
                        tmp.append(src.substring(lastPos));
                        lastPos = src.length();
                    } else {
                        tmp.append(src.substring(lastPos, pos));
                        lastPos = pos;
                    }
                }
            }
            return tmp.toString();
        }

        catch (Exception e) {
            return src;
        }
    }

    /**
     * 
     * 方法描述：四舍五入计算，scale为保留小数位数 ；创建人：靖永安 ； 创建日期：2012-5-7
     * 
     * @param dividend
     * @param divisor
     * @param scale
     * @return
     */
    public static String getRound(BigDecimal dividend, BigDecimal divisor, int scale) {
        return dividend.divide(divisor, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.subStringFrontZero("000010020200"));
    }

    public static java.sql.Date strTosqlDate(String s_DateStr, String s_FormatStr) {
        if (null == s_DateStr || "".equals(s_DateStr)) {
            return null;
        }

        SimpleDateFormat vDf = new SimpleDateFormat(s_FormatStr);
        Date rl;
        try {
            rl = vDf.parse(s_DateStr);
        } catch (ParseException e) {
            return null;
        }
        return new java.sql.Date(rl.getTime());

    }

    /**
     * 把数组中数据转换为字符串用逗号分开.
     * 
     * @param orderId
     *            String[]
     * @return String
     */
    public static String transArray(String[] orderId) {
        String orderIds = "";
        int size = 0;
        if (null != orderId && orderId.length > 0) {
            size = orderId.length;
            System.err.println("orderId=" + orderId.length);
            for (int i = 0; i < orderId.length; i++) {
                orderIds += orderId[i];
                if (i + 1 != size) {
                    orderIds += ',';
                }
            }
        }
        System.err.println("orderIds==" + orderIds);
        return orderIds;
    }

    /**
     * 字符串补0.
     * 
     * @param SourceStr
     *            SourceStr
     * @param StrLen
     *            StrLen
     * @return String String
     */
    public static String Zero_StrEx(String SourceStr, int StrLen) {// 字符串补0
        String s = SourceStr.trim();
        if (s.length() > StrLen) {
            return SourceStr;
        } else {
            int l = StrLen - s.length();
            for (int i = 0; i < l; i++) {
                s = "0" + s;
            }
            return s;
        }
    }

    /**
     * 
     * 方法描述：字符串转换成long ；创建人：靖永安 ； 创建日期：2012-8-9
     * 
     * @param str
     * @return
     */
    public static long str2Long(String str) {
        if (str == null || "".equals(str)) {
            return 0l;
        }
        try {
            return Long.valueOf(str).longValue();
        } catch (Exception e) {
            return 0l;
        }
    }

    /**
     * 
     * 方法描述：截取字符串前面的0 ；创建人：靖永安 ； 创建日期：2012-8-9
     * 
     * @param str
     * @return
     */
    public static String subStringFrontZero(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        char b[] = str.trim().toCharArray();
        if (b != null && b.length > 0) {
            for (int i = 0; i < b.length; i++) {
                if (b[i] == '0') {
                    str = str.substring(1, str.length());
                } else {
                    break;
                }
            }
        }
        return str;
    }

    /**
     * 产生随机数字.
     * 
     * @return String String
     */
    public static String dateRandom() {
        Date dt = new Date();
        long time = dt.getTime();
        String timeStr = String.valueOf(time);
        Random random = new Random();
        random.setSeed(999L);
        String randomStr = String.valueOf(Math.abs(random.nextInt()));
        return timeStr + randomStr;
    }

    /**
     * javascript字符转义.
     * 
     * @param input
     *            input
     * @return String String
     */
    public static String javaScriptEscape(String input) {
        if (input == null) {
            return input;
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char prevChar = '\u0000';
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '"') {
                filtered.append("\\\"");
            } else if (c == '\'') {
                filtered.append("\\'");
            } else if (c == '\\') {
                filtered.append("\\\\");
            } else if (c == '\t') {
                filtered.append("\\t");
            } else if (c == '\n') {
                if (prevChar != '\r') {
                    filtered.append("\\n");
                }
            } else if (c == '\r') {
                filtered.append("\\n");
            } else if (c == '\f') {
                filtered.append("\\f");
            } else if (c == '/') {
                filtered.append("\\/");
            } else {
                filtered.append(c);
            }
            prevChar = c;
        }
        return filtered.toString();
    }

    /**
     * 获取字符串长度.
     * 
     * @param str
     *            str
     * @return int int
     */
    public static int getStrLength(String str) {
        // String str = "中国chinese";
        int length = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).matches("[\\u4e00-\\u9fa5]+")) {
                length = length + 2;
            } else {
                length = length + 1;
            }
        }
        return length;
    }

    /**
     * 根据字符长度，每行大小获去有多少行.
     * 
     * @param length
     *            字符长度
     * @param size
     *            每行大小
     * @return int 行数
     */
    public static int getRow(int length, int size) {
        int shang = 0;
        int yu = 0;
        shang = length / size;
        yu = length % size;
        if (yu > 0) {
            return shang + 1;
        } else {
            return shang;
        }
    }

    /**
     * 中文占两位，英文占一位
     * 
     * @param setLen
     * @param orString
     * @return
     */
    public static String getLengthString(int setLen, String orString) {
        int strlen = 0;
        String resultStr = orString;
        for (int i = 0; i < orString.length(); i++) {
            if (orString.substring(i, i + 1).matches("[\u4e00-\u9fa5]"))
                strlen = strlen + 2;
            else
                strlen++;

            if (strlen > setLen) {
                strlen = i;
                resultStr = orString.substring(0, strlen) + "...";
                break;
            }
        }
        return resultStr;
    }

    /**
     * 判断字符串非空
     */
    public static boolean isNotBlank(String str) {
        if (str != null && !str.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断字符串非空
     */
    public static boolean isBlank(String str) {
        if (str != null && !str.trim().equals("")) {
            return false;
        } else {
        	 return true;
        }
    }

    /**
     * 
     * 方法描述：判断是否是int类型 ；创建人：靖永安 ； 创建日期：2012-8-7
     * 
     * @param str
     * @return
     */
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static  boolean verifyEmail(String email) {
		if(email==null||"".equals(email)){
			return true;
		}
		boolean tag = true;
		final String pattern1 = "^.+@.+\\..+$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}
    
    public static  boolean isDate(String s,String format){
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		try {
			fmt.parse(s);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public static  boolean isNumber(String s){
		if(s==null ||"".equals(s)){
			return false;
		}
		try{
			Long.valueOf(s);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	 /** 验证身份证号的格式
	 * @param idCardNo
	 * @return
	 */
	public static  boolean verifyIdCardNo(String idCardNo){
		if(null == idCardNo || idCardNo.equals("")){
			return false;
		}else{
			String idcard  = "^\\d{15}(\\d{2}[0-9xX])?$";   
			Pattern pattern = Pattern.compile(idcard);   
			boolean tf = pattern.matcher(idCardNo).matches();
			return tf;
		}
	}
	
	/**
	 * 验证主帐号姓名大于4个字节小于20个字节以及格式
	 * @param name
	 * @return
	 */
	public static boolean verifyName(String name){
		int maxlength = 12;
		if(name==null ||"".equals(name)){
			return false;
		}else if(name.getBytes().length>maxlength || name.getBytes().length<4){
			return false;
		}else if(name.indexOf(" ")!=-1){//include blank space
			return false;
		}else{
			//中文名称构成方式：只能由字母、数字、下划线、减号、@符、英文句点、半角()构成
			String all  = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w@().-]+$";   
			Pattern pattern = Pattern.compile(all);   
			boolean tf = pattern.matcher(name).matches();   
			return tf;
		}
	}
	
	/**
	 * 检验主帐号登录名是否符合规范大于4字节小于32字节
	 * @param loginName
	 * @return
	 */
	public static  boolean verifyLoginName(String loginName){
		int length = 32;
		if(loginName==null ||"".equals(loginName)){
			return false;
		}else if(loginName.length()>length ||loginName.length()<4){
			return false;
		}else if(loginName.indexOf(" ")!=-1){//include blank space
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 检验手机号格式是否正确
	 * @param mobile
	 * @return
	 */
	public static boolean verifyMobile(String mobile){
		if(mobile==null || mobile.length()!=11){
			return false;
		}else if(!isNumber(mobile)){
			return false;
		}else{
			boolean tag = true;
			final String pattern1 = "^(((1[3-9][0-9]{1}))+\\d{8})$";
			final Pattern pattern = Pattern.compile(pattern1);
			final Matcher mat = pattern.matcher(mobile);
			if (!mat.find()) {
				tag = false;
			}
			return tag;
		}
	}
	
	/**
	 * 过滤带4的自增序列
	 * 
	 * @param index
	 * @return
	 */
	public static  int increaseWithout4(int index) {
		if (PropertiesUtil.getProperty("increasewithout4") != null
				&& PropertiesUtil.getProperty("increasewithout4").equals("1")
				&& String.valueOf(index).indexOf("4") != -1) {
			int length = String.valueOf(index).length();
			int index4 = String.valueOf(index).indexOf("4");
			int inc = 1;
			for (int i = 0; i < length - (index4 + 1); i++) {
				inc = inc * 10;
			}
			index = index + inc;
		}
		return index;
	}

	public static String toUpper(String string){
		if(isNotBlank(string))
			return string.toUpperCase();
		else
			return string;
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 *            字符串
	 * @return 为空时返回true,否则返回false
	 */
	public static boolean isNull(String value) {
		return (value == null || value.trim().equals(""));
	}

	/**
	 * 判断字符串是否非空
	 * 
	 * @param value
	 *            字符串
	 * @return 非空返回true,否则返回false
	 */
	public static boolean isNotNull(String value) {
		return (value != null && !value.trim().equals(""));
	}

	/**
	 * 区分大小写判断两个字符串是否相等
	 * 
	 * @param value
	 *            原字符串
	 * @param compareValue
	 *            被比较字符串
	 * @return 相等返回true,否则返回false
	 */
	public static boolean isEqual(String value, String compareValue) {
		return (value != null && value.equals(compareValue));
	}

	/**
	 * 不区分大小写判断两个字符串是否相等
	 * 
	 * @param value
	 *            原字符串
	 * @param compareValue
	 *            被比较字符串
	 * @return 相等返回true,否则返回false
	 */
	public static boolean isEqualsIgnoreCase(String value, String compareValue) {
		return (value != null && value.equalsIgnoreCase(compareValue));
	}

	/**
	 * 判断字符串是否等于Y，不区分大小写
	 * 
	 * @param value
	 *            被比较字符串
	 * @return 等于y，返回true,否则false
	 */
	public static boolean isEqualsY(String value) {
		return isEqualsIgnoreCase(value, "y");
	}

	/**
	 * 判断字符串是否等于true，不区分大小写
	 * 
	 * @param value
	 *            被比较字符串
	 * @return 等于true，返回true,否则false
	 */
	public static boolean isEqualsTrue(String value) {
		return isEqualsIgnoreCase(value, "true");
	}
	
	/**
	 * 动态添加表前缀，对没有前缀的表增加前缀
	 * 
	 * @param table
	 * @param prefix
	 * @return
	 */
	public static String addPrefix(String table, String prefix) {
		String result = "";
		if (table.length() > prefix.length()) {
			if (table.substring(0, prefix.length()).toLowerCase()
					.equals(prefix.toLowerCase()))
				result = table;
			else
				result = prefix + table;
		} else
			result = prefix + table;

		return result;
	}
	
	/**
	 * 去掉sql的注释
	 */
	public static String delSqlComment(String content) {
		String pattern = "/\\*(.|[\r\n])*?\\*/";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(content);
		if (m.find()) {
			content = m.replaceAll("");
		}
		return content;
	}
}
