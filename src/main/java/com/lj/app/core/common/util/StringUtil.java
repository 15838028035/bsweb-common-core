package com.lj.app.core.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lj.app.core.common.properties.PropertiesUtil;

/**
 * 
 *内容摘要:处理数字类型共用类.
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
	 * 去除空格
	 * @param str
	 * @return
	 */
	public static String trimBlank(String str) {
		return str == null ?"":str.trim();
	}
	
	public static String toString(Object o) {
		return o == null ? "" : o.toString();
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
     * 判断字符串数组空
     */
    public static boolean isBlank(String [] str) {
    	if(str==null || str.length==0) {
    		return true;
    	}
    	return false;
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
    public static boolean isNotBlank(String [] str) {
    	if(str==null || str.length==0) {
    		return false;
    	}
    	return true;
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

	/**
	* 随机获取UUID字符串(无中划线)
	*
	* @return UUID字符串
	*/
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}

	/**
	 * 随机获取字符串
	 * 
	 * @param length
	 *            随机字符串长度
	 * 
	 * @return 随机字符串
	 */
	public static String getRandomString(int length) {
	    if (length <= 0) {
	        return "";
	    }
	    char[] randomChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
	            'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
	    Random random = new Random();
	    StringBuffer stringBuffer = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        int r = random.nextInt();
	        if(r < 0){
	            continue;
	        }
	        stringBuffer.append(randomChar[Math.abs(r) % randomChar.length]);
	    }
	    return stringBuffer.toString();
	}
	
	/**
	 * 根据指定长度 分隔字符串
	 * 
	 * @param str
	 *            需要处理的字符串
	 * @param length
	 *            分隔长度
	 * 
	 * @return 字符串集合
	 */
	public static List<String> splitString(String str, int length) {
	    List<String> list = new ArrayList<String>();
	    for (int i = 0; i < str.length(); i += length) {
	        int endIndex = i + length;
	        if (endIndex <= str.length()) {
	            list.add(str.substring(i, i + length));
	        } else {
	            list.add(str.substring(i, str.length() - 1));
	        }
	    }
	    return list;
	}
	
	/**
	 * 将字符串List转化为字符串，以分隔符间隔.
	 * 
	 * @param list
	 *            需要处理的List.
	 *            
	 * @param separator
	 *            分隔符.
	 * 
	 * @return 转化后的字符串
	 */
	public static String toString(List<String> list, String separator) {
	    StringBuffer stringBuffer = new StringBuffer();
	    for (String str : list) {
	        stringBuffer.append(separator + str);
	    }
	    stringBuffer.deleteCharAt(0);
	    return stringBuffer.toString();
	}

	/**
	 * 转换成list数组
	 * @param str
	 * @return
	 */
	public static List<String> splitStringToStringList(String str) {
		str = trimBlank(str);
		String []strArray =  str.split(",");
		
		List<String> strList = new ArrayList<String>();
		for(String strObj: strArray) {
			strList.add(strObj);
		}
		return strList;
	}
	
	public static String convertArrayToSplitString(Object[] array,
			String splitStr) {
		String toString = "";
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				toString += splitStr;
			}
			toString += array[i].toString();
		}
		return toString;
	}

	public static String convertArrayToSplitString2(Object[] array,
			String splitStr) {
		String toString = "";
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				toString += splitStr;
			}
			toString += "'" + array[i].toString() + "'";
		}
		return toString;
	}

	public static String[] divideString(String source, String divideFlag) {
		if (source == null) {
			return null;
		}
		if (source.equals("")) {
			return new String[] { "" };
		}
		if (source == null || source.equals("")) {
			return new String[] { source };
		}
		StringTokenizer st = new StringTokenizer(source, divideFlag);
		int count = st.countTokens();
		String apple[] = new String[count];
		for (int ii = 0; ii < count; ii++) {
			apple[ii] = st.nextToken();
		}
		return apple;
	}

	public static final String[] stringToArray(String str, String separators) {
		StringTokenizer tokenizer;
		String[] array = null;
		int count = 0;
		if (str == null) {
			return array;
		}
		if (separators == null) {
			separators = ",";
		}
		tokenizer = new StringTokenizer(str, separators);
		if ((count = tokenizer.countTokens()) <= 0) {
			return array;
		}
		array = new String[count];
		int ix = 0;
		while (tokenizer.hasMoreTokens()) {
			array[ix] = tokenizer.nextToken();
			ix++;
		}
		return array;
	}
	
	public final static String getStringByArray(String[] values) {
		StringBuffer valueStr = new StringBuffer();
		if (StringUtil.isNotBlank(values)) {
			for (int i = 0; i < values.length; i++) {
				valueStr.append(values[i]);
			}
		}
		return valueStr.toString();
	}

	public static String delEnter(String str) {
		String finalStr = "";
		if (str == null || str.equals("")) {
			return str;
		}
		for (int ii = 0; ii < str.length(); ii++) {
			if ((str.charAt(ii) != 13) && (str.charAt(ii) != 10)) {
				finalStr += str.charAt(ii);
			}
		} // end for
		return finalStr;
	}

	/**
	 * 判断字符串childStr是否包含在字符串allStr中
	 * 
	 * @param allStr
	 * @param childStr
	 * @return
	 */
	public static  boolean isInclude(String allStr, String childStr) {
		if (isBlank(allStr) || isBlank(childStr))
			return false;
		String[] arr = allStr.split(",");
		for (int i = 0; i < arr.length; i++) {
			if (childStr.equals(arr[i])) {
				return true;
			}
		}
		return false;
	}
    
	 /**
     * 将字符串转换为 int.
     * @param input 输入的字串

     * @param defautlInt :           
     * @return 结果数字
     */
    public static int parseInt(String input, int defaultInt) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
        }
        return defaultInt;
    }
    
    /**
     * 将字符串转换为 int.
     * @param input 输入的字串

     * @param defautlInt :           
     * @return 结果数字
     */
    public static int parseInt(Object input, int defaultInt) {
        try {
        	if(null == input){
        		return 0;
        	}else{
        		 return Integer.parseInt(input.toString());
        	}
        } catch (Exception e) {
        }
        return defaultInt;
    }

    /**
     * 将字符串转换为 float.
     * @param input 输入的字串

     * @return 结果数字
     */
    public static float parseFloat(String input, float defaultFloat) {
        try {
            return Float.parseFloat(input);
        } catch (Exception e) {
        }
        return defaultFloat;
    }
    
    /**
     * 将字符串转换为 float.
     * @param input 输入的字串

     * @return 结果数字
     */
	public static float parseFloat(Object input, float defaultFloat){
    	if(null == input){
    		return defaultFloat;
    	}
    	try{
    		return Float.parseFloat(input.toString());
    	}catch(Exception ex){}
    	return defaultFloat;
    }
	
	 /**
     * 转换由表单读取的数据的内码(从 ISO8859 转换到 UTF-8).
     * 
     * @param input
     *            输入的字符串
     * @return 转换结果, 如果有错误发生, 则返回原来的值

     */
    public static String toChi(String input) {
        try {
            byte[] bytes = input.getBytes("ISO8859-1");
            return new String(bytes, "UTF-8");
        } catch (Exception ex) {
        }
        return input;
    }

    /**
     * 转换由表单读取的数据的内码到 ISO(从 UTF-8 转换到ISO8859-1).
     * 
     * @param input
     *            输入的字符串
     * @return 转换结果, 如果有错误发生, 则返回原来的值

     */
    public static String toISO(String input) {
        return changeEncoding(input, "UTF-8", "ISO8859-1");
    }

    /**
     * 转换字符串的内码.
     * 
     * @param input
     *            输入的字符串
     * @param sourceEncoding
     *            源字符集名称
     * @param targetEncoding
     *            目标字符集名称

     * @return 转换结果, 如果有错误发生, 则返回原来的值

     */
    public static String changeEncoding(String input, String sourceEncoding, String targetEncoding) {
        if (input == null || input.equals("")) {
            return input;
        }

        try {
            byte[] bytes = input.getBytes(sourceEncoding);
            return new String(bytes, targetEncoding);
        } catch (Exception ex) {
        }
        return input;
    }

    /**
     * 将字符串 source 中的 oldStr 替换为 newStr, 并以大小写敏感方式进行查找

     * 
     * @param source
     *            需要替换的源字符串
     * @param oldStr
     *            需要被替换的老字符串
     * @param newStr
     *            替换为的新字符串
     */
    public static String replace(String source, String oldStr, String newStr) {
        return replace(source, oldStr, newStr, true);
    }

    /**
     * 将字符串 source 中的 oldStr 替换为 newStr, matchCase 为是否设置大小写敏感查找
     * 
     * @param source
     *            需要替换的源字符串
     * @param oldStr
     *            需要被替换的老字符串
     * @param newStr
     *            替换为的新字符串
     * @param matchCase
     *            是否需要按照大小写敏感方式查找
     */
    public static String replace(String source, String oldStr, String newStr, boolean matchCase) {
        if (source == null) {
            return null;
        }
        // 首先检查旧字符串是否存在, 不存在就不进行替换

        if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1) {
            return source;
        }
        int findStartPos = 0;
        int a = 0;
        while (a > -1) {
            int b = 0;
            String str1, str2, str3, str4, strA, strB;
            str1 = source;
            str2 = str1.toLowerCase();
            str3 = oldStr;
            str4 = str3.toLowerCase();
            if (matchCase) {
                strA = str1;
                strB = str3;
            }
            else {
                strA = str2;
                strB = str4;
            }
            a = strA.indexOf(strB, findStartPos);
            if (a > -1) {
                b = oldStr.length();
                findStartPos = a + b;
                StringBuffer bbuf = new StringBuffer(source);
                source = bbuf.replace(a, a + b, newStr) + "";
                // 新的查找开始点位于替换后的字符串的结尾
                findStartPos = findStartPos + newStr.length() - b;
            }
        }
        return source;
    }
    
    
    /**
     * 得到文件的扩展名.
     * 
     * @param fileName
     *            需要处理的文件的名字.
     * @return the extension portion of the file's name.
     */
    public static String getExtension(String fileName) {
        if (fileName != null) {
            int i = fileName.lastIndexOf('.');
            if (i > 0 && i < fileName.length() - 1) {
                return fileName.substring(i + 1).toLowerCase();
            }
        }
        return "";
    }
    
    /**
     * 得到文件的前缀名.
     * 
     * @param fileName
     *            需要处理的文件的名字.
     * @return the prefix portion of the file's name.
     */
    public static String getPrefix(String fileName) {
        if (fileName != null) {
            fileName = fileName.replace('\\', '/');

            if (fileName.lastIndexOf("/") > 0) {
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
            }

            int i = fileName.lastIndexOf('.');
            if (i > 0 && i < fileName.length() - 1) {
                return fileName.substring(0, i);
            }
        }
        return "";
    }
    
    /**
     * 得到文件的短路径, 不保护目录.
     * 
     * @param fileName
     *            需要处理的文件的名字.
     * @return the short version of the file's name.
     */
    public static String getShortFileName(String fileName) {
        if (fileName != null) {
            String oldFileName = new String(fileName);

            fileName = fileName.replace('\\', '/');

            // Handle dir
            if (fileName.endsWith("/")) {
                int idx = fileName.indexOf('/');
                if (idx == -1 || idx == fileName.length() - 1) {
                    return oldFileName;
                }
                else {
                    return oldFileName.substring(idx + 1, fileName.length() - 1);
                }

            }
            if (fileName.lastIndexOf("/") > 0) {
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
            }

            return fileName;
        }
        return "";
    }

    /**
     * 
     *@功能描述：将list中的字符串组合成以str为分隔符的形式，例如xxx,fsd,erwr
     * @param list	字符串集合

     * @param str 要分隔的符号
     * @return xxx,fsd,erwr形式的字符串
     */
    public static String getStrSplitWithChar(List<String> list, String str){
    	String result = "";
    	if (list == null)
    		return result;
    	for (int i = 0; i < list.size(); i++){
    		result += list.get(i) + str;
    	}
    	if (result.endsWith(str)){
    		int pos = result.lastIndexOf(str);
    		if (pos > -1)
    			result = result.substring(0, pos );
    	}
    	return result;	
    } 
    
    /**
   	 *截取字符串

   	 *@param input : 传入字符串

   	 *@param regex : 切割字符串的字符串，或者正则表达式
   	 *@param index : 切割字符串后的字符串数组，返回改数组的对应下标的字符串

   	 */
       public static String splitStr(String input, String regex, int index){
       	if(isBlank(input)){
   			return "";
   		}else{
   			if(isBlank(regex))
   				regex = "=";
   			if(isBlank(index+""))
   				index = 1;
   			String[] temp = input.split(regex);
   			if(temp.length == 1)
   				return input;
   			else
   				return temp[index];
   		}
       }
       
       public static String spliptListToString(List<Map<String, Object>> list, String column) {
   		if(null == list || list.size() == 0){
   			return "";
   		}
   		StringBuffer sb = new StringBuffer();
   		Map<String, Object> map = null;
   		for(int i=0; i<list.size(); i++){
   			map = list.get(i);
   			if(null == map.get(column))
   				continue;
   			sb.append(map.get(column));
   			sb.append(",");
   		}
   		int index = sb.lastIndexOf(",");
       	if(index != -1){
       		sb.delete(index, sb.length());
       	}
       	return sb.toString();
   		
   	}
       
   /**
    * 去除Map中value的空格

    * @param map
    * @return
    */
   public static Map<String,Object> toTrim(Map<String,Object> map){
   	if(map == null){
   		return null;
   	}
   	Map<String,Object> m = map;
   	Iterator i = m.entrySet().iterator();
   	while(i.hasNext()){
   		Map.Entry entry =  (Entry) i.next();
   		entry.setValue(StringUtil.trimBlank(String.valueOf(entry.getValue())));
   	}
   	return m;
   }
     
   /**
    * 将list元素转换成“col1,col2”形式

    * @return
    */
   public static String arrayToString(List<String> s){
   	StringBuffer sb = new StringBuffer();
   	for(int i=0;i<s.size();i++){
   		if(i != s.size() - 1){
   			sb.append("'"+s.get(i)+"',");
   		}else{
   			sb.append("'"+s.get(i)+"'");
   		}
   	}
   	return sb.toString();
   }
   
   /**
    * 将list元素转换成“col1,col2”形式，剔除空串的情况

    * @return
    */
   public static String arrayToString2(List<String> s){
   	StringBuffer sb = new StringBuffer();
   	for(int i=0;i<s.size();i++){
   		if (s.get(i).equals(""))
   			continue;
   		if(i != s.size() - 1){
   			sb.append("'"+s.get(i)+"',");
   		}else{
   			sb.append("'"+s.get(i)+"'");
   		}
   	}
   	return sb.toString();
   }
   
   /**
    * 去除List中Map的value的空格

    * @param map
    * @return
    */
   public static List<Map<String,Object>> toTrim(List<Map<String,Object>> map){
   	List<Map<String,Object>> list = map;
   	for(int i=0;i<list.size();i++){
   		toTrim(list.get(i));
   	}
   	return list;
   }
   
   /**
	 * 功能描述：补0操作
	 * @param srcStr
	 * @param insertStr
	 * @param len
	 * @return
	 */
	public static String insertStr(String srcStr,String insertStr,int len){
		String tmp = srcStr;
		int srcLen = StringUtil.trimBlank(tmp).length();//原串长

		int insertLen = len - srcLen;
		if(insertLen > 0){
			for(int i = 0; i < insertLen; i++){
				tmp = insertStr + tmp;
			}
		}     
		return tmp;
	}
	
	/**
	 * 功能描述：清除字符串中所有的空格
	 * @param srcStr
	 * @return
	 */          	
	public static String clearSpace(String srcStr){
		if(srcStr == null) return "";
		Pattern p = Pattern.compile("\\s*|\t|\r|\n"); 
	    Matcher m = p.matcher(srcStr); 
	    String after = m.replaceAll(""); 
	    return after;

	}
	
	/**
	 * 功能描述：清楚回车换行

	 * @param srcStr
	 * @return
	 */
	public static String clearWrap(String srcStr){
		if(srcStr == null) return "";
		Pattern p = Pattern.compile("\\t|\r|\n"); 
	    Matcher m = p.matcher(srcStr); 
	    String after = m.replaceAll(""); 
	    return after;
	}
	
	public static String replaceHtmlEdit(String inputStr){
		return clearSpace(inputStr.replace("\"", "\\\""));
	}
	
	/**
	 * 判断字符串是否为浮点型

	 * @param String str	要判断的字符串

	 * @return	true 浮点型,false 非浮点型
	 */
	 public static boolean isDecimal(String str) {
		 if(str==null || "".equals(str))
	     return false;  
	     Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
	     return pattern.matcher(str).matches();
	 }
	 
	 /**
	  * 查找字符串数组中是否有匹配值

	  * @param String[] array	字符串数组

	  * @param String str	要匹配的值

	  * @return	是否存在匹配值

	  */
	 public static boolean findMatchArray(String[] array, String str){
		 boolean isMatch = false;
		 for (int i = 0; i < array.length; i++) {
			if(str.equals(array[i])){
				isMatch = true;
			}
		 }
		 return isMatch;
	 }
	
}
