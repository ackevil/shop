package com.huituopin.wechat.entity;
/*
 * */
public class ConfigXmlEntity {
	private String  URL ;
	private String AppID;
	private String Appsecret;
	private String Token;
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getAppID() {
		return AppID;
	}
	public void setAppID(String appID) {
		AppID = appID;
	}
	public String getAppsecret() {
		return Appsecret;
	}
	public void setAppsecret(String appsecret) {
		Appsecret = appsecret;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getAccessTokenApi() {
		return AccessTokenApi;
	}
	public void setAccessTokenApi(String accessTokenApi) {
		AccessTokenApi = accessTokenApi;
	}
	private String AccessTokenApi;
	
}
