package com.qingofun.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

	/**
	 * 签名字符串
	 *
	 * @param text 需要签名的字符串
	 * @param key 密钥
	 * @param inputCharset 编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String inputCharset) {
		String text1 = text + key;
		System.out.println("md5.sign srcText["+text+"]");
		System.out.println("md5.sign srcKey["+key+"]");
		System.out.println("md5.sign srcString["+text1+"]");
		String resString = DigestUtils.md5Hex(getContentBytes(text1, inputCharset));
		System.out.println("md5.sign resString["+resString+"]");
		return resString;
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws java.security.SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if ((charset == null) || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}
	public static void main(String[] args) {
		//181b4d1e140c3117fb4179f0527b649f
		System.out.println(sign("b9e133a42b342f7eff0830bd30f68d6159e416e5cdcd511dcf56c3e8b5a385443c6943af1a4ef416ebb5bfd10e227dda3322c61a4f79122e8533be356d17831b4b93d746345f7e9501d49fba2b58967f","10803CD1214E09F56D742ECCE4AF336366C57E463D6744FE","UTF-8"));
	}

}