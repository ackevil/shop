package com.huituopin.user.sendsms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class Sendsms {
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	public static String semdsms(String mobile,int mobile_code) throws UnsupportedEncodingException{
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		String content = new String("您的验证码是："+mobile_code+"。请不要把验证码泄露给其他人。");
		
		
		
		System.out.println("utf:"+content.getBytes());
		SendsmsConfig sendsmsConfig = SendsmsConfig.getInstance();
		
		String account = sendsmsConfig.getAccount();
		String password = sendsmsConfig.getPasswrod();
		
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account",account), 
			    new NameValuePair("password",password),
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);
		String code = null;
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
			//System.out.println(SubmitResult);
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return code;	
	}
}
