package com.huituopin.user.sendsms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SendsmsConfig {
	private String account;
	private String passwrod;
	
	private static SendsmsConfig sendsmsConfig = null;
	
	public static SendsmsConfig getInstance(){
		if(sendsmsConfig == null){
			synchronized (SendsmsConfig.class) {
				if(sendsmsConfig == null){
					sendsmsConfig = new SendsmsConfig();
					setFiled();
				}
			}
		}
		return sendsmsConfig;
	}

	private static void setFiled() {
		InputStream is=SendsmsConfig.class.getClassLoader().getResourceAsStream("sendsms.properties");
		Properties p =new Properties();
		try {
			if(is==null){
				System.out.print("配置文件未找到");
			}
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendsmsConfig.setAccount(p.getProperty("sms.account"));
		sendsmsConfig.setPasswrod(p.getProperty("sms.password"));
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}
	
	
}
