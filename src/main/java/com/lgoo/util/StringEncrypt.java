/*
 * Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
 *
 * 注意：本内容仅限于深圳市归一科技研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
 */
package com.lgoo.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.gy.hsxt.common.exception.HsException;
import com.gy.hsxt.uc.as.api.enumerate.ErrorCodeEnum;

/**
 * 提供加密解密工具
 * 
 * @Package: com.gy.hsxt.uc.util
 * @ClassName: StringEncrypt
 * @Description: TODO
 * 
 * @author: lixuan
 * @date: 2015-10-20 下午4:31:34
 * @version V1.0
 */
public class StringEncrypt {
	private final static String MOUDLENAME = "com.gy.hsxt.uc.util.StringEncrypt";
	private final static String ivNum = "9999999999999999"; // 向量（必须是全是数字）

	/**
	 * aes加密
	 * 
	 * @param md5
	 *            MD5密码
	 * @param secretKey
	 *            加密秘钥
	 * @return
	 * @throws HsException
	 */
	public static String encrypt(String md5, String secretKey)
			throws HsException {
		if (secretKey == null) {
			throw new HsException(ErrorCodeEnum.PARAM_IS_REQUIRED.getValue(),
					ErrorCodeEnum.PARAM_IS_REQUIRED.getDesc());
		}
		// 判断Key是否为16位
		if (secretKey.length() != 16) {
			throw new HsException(
					ErrorCodeEnum.SECRETKEY_LENGTH_SIXTEEN.getValue(),
					ErrorCodeEnum.SECRETKEY_LENGTH_SIXTEEN.getDesc());
		}
		byte[] raw = secretKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(ivNum.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		try {
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		byte[] encrypted = null;
		try {
			encrypted = cipher.doFinal(md5.getBytes());
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		//System.out.println(HexBin.encode(encrypted));
		return Base64.encodeBase64String(encrypted);// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
	}

	/**
	 * MD5加码 生成16位md5码
	 * 
	 * @param inStr
	 *            密码
	 * @return
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
		//return md5Code.substring(8, 24);
	}

	
	public static String string2MD52(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		String md5Code = hexValue.toString();
		return md5Code;
	}
	/**
	 * 解密
	 * 
	 * @param sSrc
	 *            待解密内容
	 * @param sKey
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String aesPwd, String secretKey)
			throws HsException {

		try {
			// 判断Key是否正确
			if (secretKey == null) {
				throw new HsException(
						ErrorCodeEnum.PARAM_IS_REQUIRED.getValue(), "AES秘钥为空");
			}
			// 判断Key是否为16位
			if (secretKey.length() != 16) {
				throw new HsException(
						ErrorCodeEnum.SECRETKEY_LENGTH_SIXTEEN.getValue(),
						ErrorCodeEnum.SECRETKEY_LENGTH_SIXTEEN.getDesc());
			}
			byte[] raw = secretKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivNum.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(aesPwd);// 先用bAES64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (HsException e) {
				throw new HsException(ErrorCodeEnum.DATA_DECRYPT.getValue(),
						ErrorCodeEnum.DATA_DECRYPT.getDesc());
			}
		} catch (Exception ex) {
			throw new HsException(ErrorCodeEnum.DATA_DECRYPT.getValue(),
					ErrorCodeEnum.DATA_DECRYPT.getDesc());
		}

	}

	/**
	 * 使用sha256方式加密
	 * 
	 * @param content
	 *            待加密的字符串
	 * @return
	 */
	public static String sha256(String content) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = content.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	/**
	 * 使用sha256方式加密
	 * 
	 * @param content
	 *            待加密的字符串
	 * @return
	 */
	public static String sha1(String content) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = content.getBytes();
		try {

			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	/**
	 * 将byte转换为16进制字符类型
	 * 
	 * @param bts
	 *            待转换的byte
	 * @return
	 */
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * 将16进制的字符转换为byte
	 * 
	 * @param hexStr
	 *            待转换的字符
	 * @return
	 */
	public static byte[] hex2Bytes(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		computeDbLoginPwd();		
		System.out.println(System.currentTimeMillis() -start);
	}
	
	public static void computeDbLoginPwd(){
		String blankPwd = "666666";
		String salt = "99137520";
		blankPwd = StringEncrypt.string2MD5(blankPwd);
		System.out.println(StringEncrypt.sha256(blankPwd + salt) );
	}
	
	public static void computeWebLoginPwd(){
		String plantPwd = "666666";
		String token = "8610439aa01a58e3";
		plantPwd = StringEncrypt.encrypt(plantPwd, token);
		System.out.println("AES加密后登录密码：" + plantPwd);
	}
	public static void computeWebTranPwd(){
		String plantPwd = "123123";
		String token = "a6bf9255a82701f0";
		//plantPwd = StringEncrypt.string2MD5(plantPwd);
		//System.out.println("MD5密码："  + plantPwd);
		plantPwd = StringEncrypt.encrypt(plantPwd, token);
		System.out.println("AES加密后：" + plantPwd);
	}
	
	
	
}
