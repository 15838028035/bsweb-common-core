package com.lj.app.core.common.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES安全编码组件
 *
 * @version 1.0
 */
public abstract class DESCoder {

	public static final String KEY_ALGORITHM = "DES";
	
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	
	private static final int DES_CODE_LENGTH = 56;
	
	/***
	 * 转换密钥
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Key toKey(byte []key) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		SecretKey  secretKey = keyFactory.generateSecret(keySpec);
		return secretKey;
	}
	
	/**
	 * 解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte []data, byte []key) throws Exception {
		Key k = toKey(key);
		Cipher  cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 加密操作
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte []data, byte []key) throws Exception {
		Key k = toKey(key);
		Cipher  cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 生成密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(CIPHER_ALGORITHM);
		kg.init(DES_CODE_LENGTH);
		SecretKey secrectKey = kg.generateKey();
		return secrectKey.getEncoded();
	}
}
