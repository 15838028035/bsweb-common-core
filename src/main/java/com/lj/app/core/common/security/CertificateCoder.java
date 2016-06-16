package com.lj.app.core.common.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;

/**
 *证书组件
 *@version 1.0
 */
public abstract class CertificateCoder {
	
	public static final String CERT_TYPE = "X.509";
	
	/**
	 * 由keyStore获得私钥
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKeyByKeyStore(String keyStorePath, String alias, String password) throws Exception{
		KeyStore ks = getKeyStore(keyStorePath,password);
		return (PrivateKey) ks.getKey(alias, password.toCharArray());
	}

	/**
	 * 由keyStore获得公钥
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKeyByCertificate(String certificatePath) throws Exception {
		Certificate certificate = getCertificate(certificatePath);
		return certificate.getPublicKey();
	}
	
	/**
	 * 获得Certificate
	 * 
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	private static Certificate getCertificate(String certificatePath) throws Exception {
		CertificateFactory certificateFactory = CertificateFactory.getInstance(CERT_TYPE);
		FileInputStream in = new FileInputStream(certificatePath);
		Certificate certificate = certificateFactory.generateCertificate(in);
		in.close();
		return certificate;
	}
	
	/**
	 * 获得Certificate
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public  static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception {
		KeyStore ks = getKeyStore(keyStorePath,password);
		return ks.getCertificate(alias);
	}
	
	/**
	 * 获得KeyStore
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public  static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream in = new FileInputStream(keyStorePath);
		ks.load(in,password.toCharArray());
		in.close();
		return ks;
	}
	
	/**
	 * 私钥加密
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte []data, String keyStorePath, String alias, String password) throws Exception {
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, alias, password);
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 私钥解密
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] descryptByPrivateKey(byte []data, String keyStorePath, String alias, String password) throws Exception {
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, alias, password);
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥加密
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte []data, String keyStorePath, String alias, String password) throws Exception {
		PublicKey publicKey = getPublicKeyByCertificate(keyStorePath);
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥解密
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] descryptByPublicKey(byte []data, String keyStorePath, String alias, String password) throws Exception {
		PublicKey publicKey = getPublicKeyByCertificate(keyStorePath);
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 *签名
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] sign(byte []sign, String keyStorePath, String alias, String password) throws Exception {
		X509Certificate  x509Certificate  =(X509Certificate) getCertificate(keyStorePath, alias, password);
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, alias, password);
		signature.initSign(privateKey);
		signature.update(sign);
		return signature.sign();
	}
	
	/**
	 *校验
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte []data, String keyStorePath, String alias, String password) throws Exception {
		X509Certificate  x509Certificate  =(X509Certificate) getCertificate(keyStorePath, alias, password);
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		signature.initVerify(x509Certificate);
		signature.update(data);
		return signature.verify(data);
	}
}
