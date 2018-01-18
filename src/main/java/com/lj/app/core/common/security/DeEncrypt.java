package com.lj.app.core.common.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeEncrypt {
  static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1,
      -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
      12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33,
      34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(StrToBin("069171"));
    System.out.println(StrToBinstr("0"));
    System.out.println(denpry("100110000001101100011100100110001001101110011000"));
  }

  public static String StrToBin(String str) {
    String result = "";
    if (str == null || str.equals("")) {
      return "";
    }
    for (int i = 0; i < str.length(); i++) {
      result = result + StrToBinstr(str.substring(i, i + 1));
    }
    result = result.substring(result.length() - 1, result.length()) + result.substring(0, result.length() - 1);// �����һλ�Ƶ���ǰ��ȥ
    return result;
  }

  // ���ַ�ת���ɶ������ַ��Կո����
  public static String StrToBinstr(String str) {
    char[] strChar = str.toCharArray();
    String result = "";
    for (int i = 0; i < strChar.length; i++) {
      result += Integer.toBinaryString(strChar[i]) + "";
    }
    if (result.length() < 8) {
      String temp = "";
      for (int i = 0; i < 8 - result.length(); i++) {
        temp = temp + "0";
      }
      result = temp + result;
    }
    return result;
  }

  public static String denpry(String b) {
    // ����ǰ��һλ�Ƶ������ȥ
    String d = b.substring(1) + b.substring(0, 1);
    String decrpty = "";
    for (int i = 0; i < 6; i++) {
      String temp = "";
      String dd = d.substring(i * 8, (i + 1) * 8);
      BigInteger src = new BigInteger(dd, 2);// ת��ΪBigInteger����
      if (src.intValue() == 48) {
        temp = "0";
      } else if (src.intValue() == 49) {
        temp = "1";
      } else if (src.intValue() == 50) {
        temp = "2";
      } else if (src.intValue() == 51) {
        temp = "3";
      } else if (src.intValue() == 52) {
        temp = "4";
      } else if (src.intValue() == 53) {
        temp = "5";
      } else if (src.intValue() == 54) {
        temp = "6";
      } else if (src.intValue() == 55) {
        temp = "7";
      } else if (src.intValue() == 56) {
        temp = "8";
      } else if (src.intValue() == 57) {
        temp = "9";
      }
      decrpty = decrpty + temp;
    }
    System.out.println("==" + decrpty);

    return decrpty;
  }

  public static String base64decode(String str) {
    if (str == null)
      return null;

    int c1, c2, c3, c4;
    int i, len;
    len = str.length();
    i = 0;
    String out = "";
    while (i < len) {
      /* c1 */
      do {
        c1 = base64DecodeChars[str.charAt(i++) & 0xff];
      } while (i < len && c1 == -1);
      if (c1 == -1)
        break;
      /* c2 */
      do {
        c2 = base64DecodeChars[str.charAt(i++) & 0xff];
      } while (i < len && c2 == -1);
      if (c2 == -1)
        break;
      out += (char) ((c1 << 2) | ((c2 & 0x30) >> 4));
      /* c3 */
      do {
        c3 = str.charAt(i++) & 0xff;
        if (c3 == 61)
          return out;
        c3 = base64DecodeChars[c3];
      } while (i < len && c3 == -1);
      if (c3 == -1)
        break;
      out += (char) (((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
      /* c4 */
      do {
        c4 = str.charAt(i++) & 0xff;
        if (c4 == 61)
          return out;
        c4 = base64DecodeChars[c4];
      } while (i < len && c4 == -1);
      if (c4 == -1)
        break;
      out += (char) (((c3 & 0x03) << 6) | c4);
    }
    return out;
  }

  public static final String getMD5Str(String str, String charSet) { // md5加密
    MessageDigest messageDigest = null;
    try {
      messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.reset();
      if (charSet == null) {
        messageDigest.update(str.getBytes());
      } else {
        messageDigest.update(str.getBytes(charSet));
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    byte[] byteArray = messageDigest.digest();
    StringBuffer md5StrBuff = new StringBuffer();
    for (int i = 0; i < byteArray.length; i++) {
      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
        md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
      else
        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
    }
    return md5StrBuff.toString();
  }
}
