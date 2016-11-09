package com.huituopin.wechat.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

import com.huituopin.wechat.core.service.impl.WechatServiceImpl;

public class WechatValid {
	private  String TOKEN=null;
	/*首次微信激活验证调用*/
	public boolean valid(String signature ,String timeStamp ,String nonce ){
		
		InputStream is=WechatServiceImpl.class.getClassLoader().getResourceAsStream("wechat.properties");
		Properties p =new Properties();
		try {
			if(is==null){
				System.out.print("文件未找到");
			}
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("文件未找到"+TOKEN);
		TOKEN=p.getProperty("wechat.token");
		
		return checkSignature(signature,timeStamp,nonce);
	}
	/*验证是否是微信服务器发送的信息*/
	public boolean checkSignature(String signature, String timeStamp,
			String nonce) {
		String [] tmpArr =new String[]{TOKEN,timeStamp,nonce};
		Arrays.sort(tmpArr);
		StringBuffer tmpStr= new StringBuffer();
		for(String string : tmpArr){
			tmpStr.append(string);
		}
		String resultString =null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest=md.digest(tmpStr.toString().getBytes());
		    resultString = byteToStr(digest);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resultString!=null?resultString.equals(signature.toUpperCase()):false;
	}
	/*字节数组转化十六进制字符串*/
	private String byteToStr(byte[] digest) {
		String strDigeString="";
		for(int i =0 ;i<digest.length;i++){
			strDigeString+=byteToHexStr(digest[i]);
		}
		return strDigeString;
	}
	/*字节转化十六进制字串*/
	private String byteToHexStr(byte b) {
		char[] digit={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		char[] tempArr = new char[2];
		tempArr[0]=digit[((b>>>4)&0X0F)];
		tempArr[1]=digit[b&0X0F];
		String string = new String(tempArr);
		return string;
	}
}
