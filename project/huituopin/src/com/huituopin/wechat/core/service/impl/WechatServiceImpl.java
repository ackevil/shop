package com.huituopin.wechat.core.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Properties;

import com.huituopin.wechat.core.service.IWechatService;
import com.huituopin.wechat.http.HttpEngine;


public class WechatServiceImpl implements IWechatService {
	private String appID ;
	private String url;
	private String appsecret;
	private String token;
	private String redirect_uri ;
	private String code;
	private String scope;
	private String state;
	public WechatServiceImpl(){
		setProperties();
	}

	private void setProperties() {
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
		System.out.print("文件找到");
		appID=p.getProperty("wechat.appID");
		appsecret=p.getProperty("wechat.appsecret");
		url=p.getProperty("authorize.url");
		token=p.getProperty("token");
		redirect_uri=p.getProperty("redirect_uri");
		code=p.getProperty("code");
		scope=p.getProperty("scope2");
		state=p.getProperty("state");
	}



	public String getCode() {
		/**
		 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&
		 * redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#
		 * wechat_redirect
		 */
		//String url="https://open.weixin.qq.com/connect/oauth2/authorize";
		LinkedHashMap<String,String> requestParams=new LinkedHashMap<String,String>();
		requestParams.put("appid", appID);
		requestParams.put("redirect_uri", URLEncoder.encode(redirect_uri));
		requestParams.put("response_type", code);
		requestParams.put("scope", scope);
		requestParams.put("state",state);
		String urlsString=url;
		if(requestParams!=null){
			urlsString+="?";
			for (Entry<String, String> entry:requestParams.entrySet()) {
				urlsString+=entry.getKey()+"="+entry.getValue()+"&";
			}
			urlsString=urlsString.substring(0,urlsString.length()-1);
		}
		return  urlsString;

	}

	public String getAccessToken(String code) {
		/**
		 * 获取code后，请求以下链接获取access_token：
		 https://api.weixin.qq.com/sns/oauth2/access_token?
		 appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		 */
		String url="https://api.weixin.qq.com/sns/oauth2/access_token";
		LinkedHashMap<String,String> requestParams=new LinkedHashMap<String,String>();
		requestParams.put("appid", appID);
		requestParams.put("secret",appsecret);
		requestParams.put("code", code);
		requestParams.put("grant_type", "authorization_code");
		HttpEngine httpEngine=HttpEngine.getInstance();
		HttpURLConnection connection=httpEngine.sendRequest(url,HttpEngine.REQUEST_TYPE_GET,null,requestParams);
		return  httpEngine.getStringFromCon(connection);


	}

	public String getUserInfo(String openID, String accessToken) {
		/**
		 * https://api.weixin.qq.com/sns/userinfo?
		 * access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		 */
		String url="https://api.weixin.qq.com/sns/userinfo";
		LinkedHashMap<String,String> requestParams=new LinkedHashMap<String,String>();
		requestParams.put("access_token",accessToken);
		requestParams.put("openid",openID);
		requestParams.put("lang", "zh_CN");
		HttpEngine httpEngine=HttpEngine.getInstance();
		HttpURLConnection connection=httpEngine.sendRequest(url,HttpEngine.REQUEST_TYPE_GET,null,requestParams);
		return  httpEngine.getStringFromCon(connection);
	}
	

}
