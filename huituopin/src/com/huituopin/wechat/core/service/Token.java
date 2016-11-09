package com.huituopin.wechat.core.service;

import java.util.Date;

import com.huituopin.wechat.core.service.impl.WechatServiceImpl;

/*
 * 单例用来保存 accessToken 和  jsticket
 */
public class Token {

	private String accessToken;
	private Date   accessTokenTime;
	private String jsticket;
	private Date   jsticketTime;
	
	private static Token token =null;
	public static Token getInstance(){
		if(token==null){
			synchronized (Token.class) {
				if(token==null){
					token=new Token();
				}
			}
			new Thread(new Runnable() {
				WechatServiceImpl we=new WechatServiceImpl();
				@Override
				public void run() {
					while(true){
						we.getAccessToken();
						we.getJsTicket();
						try {
							Thread.sleep(3600000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}  
					}
				}
			}).start();
		}
		return token;
	}
	
	private Token(){}
	
	
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Date getAccessTokenTime() {
		return accessTokenTime;
	}
	public void setAccessTokenTime(Date accessTokenTime) {
		this.accessTokenTime = accessTokenTime;
	}
	public String getJsticket() {
		return jsticket;
	}
	public void setJsticket(String jsticket) {
		this.jsticket = jsticket;
	}
	public Date getJsticketTime() {
		return jsticketTime;
	}
	public void setJsticketTime(Date jsticketTime) {
		this.jsticketTime = jsticketTime;
	}
}
