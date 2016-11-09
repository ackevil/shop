package com.huituopin.wechat.core.service.impl;



import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.huituopin.wechat.core.service.IWechatService;
import com.huituopin.wechat.core.service.Token;
import com.huituopin.wechat.core.service.WechatConfig;
import com.huituopin.wechat.http.HttpEngine;

@Service
public class WechatServiceImpl implements IWechatService {
	
	private WechatConfig wechatConfig ;
	
	public WechatServiceImpl(){
		//初始化微信配置
		wechatConfig =WechatConfig.getInstance();
	}
	public String getCode() {
		/**
		 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&
		 * redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#
		 * wechat_redirect
		 */
		String url="https://open.weixin.qq.com/connect/oauth2/authorize";
		
		LinkedHashMap<String,String> requestParams=new LinkedHashMap<String,String>();
		requestParams.put("appid", wechatConfig.getAppID());
		try {
			requestParams.put("redirect_uri", URLEncoder.encode(wechatConfig.getRedirect_uri(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		requestParams.put("response_type", "code");
		requestParams.put("scope", "snsapi_userinfo");
		requestParams.put("state","STATE#wechat_redirect");
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
	//此accessToken 与 Token 中不同，专门为个人网页授权
	public String getAccessToken(String code) {
		String url="https://api.weixin.qq.com/sns/oauth2/access_token";
		LinkedHashMap<String,String> requestParams=new LinkedHashMap<String,String>();
		requestParams.put("appid", wechatConfig.getAppID());
		requestParams.put("secret",wechatConfig.getAppsecret());
		requestParams.put("code", code);
		requestParams.put("grant_type", "authorization_code");
		HttpEngine httpEngine=HttpEngine.getInstance();
		HttpURLConnection connection=httpEngine.sendRequest(url,HttpEngine.REQUEST_TYPE_GET,null,requestParams);
		return  httpEngine.getStringFromCon(connection);
	}
	
	/*微信主AccessToken*/
	public String getAccessToken(){
		Token token=Token.getInstance();
		if(token.getAccessTokenTime()==null){
    		token.setAccessTokenTime(new Date());
    	}
    	Date now  =new Date();
    	long delay = now.getTime() - token.getAccessTokenTime().getTime();
    	if(token.getAccessToken()!=null &&  delay<=3600000){
    		return token.getAccessToken();
    	}
    	
    	//https://api.weixin.qq.com/cgi-bin/token?
    	//grant_type=client_credential&appid=APPID&secret=APPSECRET
    			
    	String url="https://api.weixin.qq.com/cgi-bin/token";
		LinkedHashMap<String,String> requestParams=new LinkedHashMap<String,String>();
		requestParams.put("grant_type", "client_credential");
		requestParams.put("appid", wechatConfig.getAppID());
		requestParams.put("secret",wechatConfig.getAppsecret());
		
		HttpEngine httpEngine=HttpEngine.getInstance();
		HttpURLConnection connection=httpEngine.sendRequest(url,HttpEngine.REQUEST_TYPE_GET,null,requestParams);
		String accessTokenJson=  httpEngine.getStringFromCon(connection);
		JSONObject jsonObject= new JSONObject(accessTokenJson);
		String accessToken=jsonObject.getString("access_token");
		token.setAccessToken(accessToken);
		token.setAccessTokenTime(now);
		return accessToken;
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

	@Override
	public Map<String, String> getJsConfig(String url) {
		 String nonce_str = create_nonce_str();
	     String timestamp = create_timestamp();
	     String jsapi_ticket=getJsTicket();
	     	Map<String,String> map = new HashMap<String,String>();
	     	map.put("jsapi_ticket", jsapi_ticket);
		    map.put("noncestr", nonce_str);
		    map.put("timestamp", timestamp);
		    map.put("url", url);
		    String signature=getJsSign(map);
		    map.put("signature",signature);
		    map.put("appId", wechatConfig.getAppID());
		return map;
	}
	public String getJsSign(Map<String,String> map){
	    	ArrayList<String> list = new ArrayList<String>();
	        for(Map.Entry<String,String> entry:map.entrySet()){
	            if(entry.getValue()!=""){
	                list.add(entry.getKey() + "=" + entry.getValue() + "&");
	            }
	        }
	        int size = list.size();
	        String [] arrayToSort = list.toArray(new String[size]);
	        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < size; i ++) {
	            sb.append(arrayToSort[i]);
	        }
	        String result = sb.toString().substring(0,sb.length()-1);
	        
	        try {
	        	 MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	             crypt.reset();
	             crypt.update(result.getBytes("UTF-8"));
	             result = byteToHex(crypt.digest());
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return result;
	    }
	
	 private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash)
	        {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
	    }
	private String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	private String create_nonce_str() {
		 return UUID.randomUUID().toString();
	}

		public String getJsTicket(){
			Token token=Token.getInstance();
	    	if(token.getJsticketTime() == null) {
	    		token.setJsticketTime(new Date());
	    	}
	    	
	    	Date now = new Date();
	    	
	    	long delay = now.getTime() - token.getJsticketTime().getTime();
	    	if(token.getJsticket()!=null && delay <=3600000){
	    		return token.getJsticket();
	    	}
	    	
	    	
	    	
	    	//https://api.weixin.qq.com/cgi-bin/ticket/getticket?
	    	//access_token=ACCESS_TOKEN&type=jsapi
	    	//token.setJsticketTime(now);
	    	String accessToken = getAccessToken();
	    	String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
			LinkedHashMap<String,String> requestParams=new LinkedHashMap<String,String>();
			requestParams.put("access_token",accessToken);
			requestParams.put("type","jsapi");
			HttpEngine httpEngine=HttpEngine.getInstance();
			HttpURLConnection connection=httpEngine.sendRequest(url,HttpEngine.REQUEST_TYPE_GET,null,requestParams);
			String jsTicketJson=  httpEngine.getStringFromCon(connection);
			
			JSONObject jsonObject= new JSONObject(jsTicketJson);
			String jsTicket=jsonObject.getString("ticket");
			token.setJsticket(jsTicket);
			token.setJsticketTime(now);
			return jsTicket;
	    
	    }
}
