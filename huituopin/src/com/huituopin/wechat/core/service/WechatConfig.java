package com.huituopin.wechat.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class WechatConfig {
	private String appID;		//公众号 appid
	private String appsecret;	//公众号 密钥
	private String token;		//公众号 token
	private String redirect_uri;  // 网页授权回调url
	private String key;			 //微信商户key
	private String Mch_id;	//商户id
	private String Notify_url;  //支付成功回调url
	
	

	private static WechatConfig wechatConfig=null;
	public static WechatConfig getInstance(){
		if(wechatConfig==null){
			synchronized (WechatConfig.class) {
				if(wechatConfig==null){
					wechatConfig=new WechatConfig();
					setFiled();
				}
			}
		}
		return wechatConfig;
	}
	
	private static void setFiled() {
		InputStream is=WechatConfig.class.getClassLoader().getResourceAsStream("wechat.properties");
		Properties p =new Properties();
		try {
			if(is==null){
				System.out.print("微信配置文件未找到");
			}
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		wechatConfig.setAppID(p.getProperty("wechat.appID"));
		wechatConfig.setAppsecret(p.getProperty("wechat.appsecret"));
		wechatConfig.setToken(p.getProperty("wechat.token"));
		wechatConfig.setRedirect_uri(p.getProperty("wechat.redirect_uri"));
		wechatConfig.setMch_id(p.getProperty("wechat.Mch_id"));
		wechatConfig.setKey(p.getProperty("wechat.Mch_key"));
		wechatConfig.setNotify_url(p.getProperty("wechat.notify_url"));
	}

	private WechatConfig(){
		
	}
	
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getMch_id() {
		return Mch_id;
	}

	public void setMch_id(String mch_id) {
		Mch_id = mch_id;
	}
	public String getNotify_url() {
		return Notify_url;
	}

	public void setNotify_url(String notify_url) {
		Notify_url = notify_url;
	}

}
