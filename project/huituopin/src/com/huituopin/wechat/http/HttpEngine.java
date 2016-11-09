package com.huituopin.wechat.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class HttpEngine {
	public static final String REQUEST_TYPE_GET= "GET" ;   //请求类型get
	public static final String REQUEST_TYPE_POST= "POST" ;//请求类型post
	
	public static HttpEngine instance =null; //single 实例
	private HttpEngine(){						
		
	} ;
	/*获取单一实例*/
	public static HttpEngine getInstance(){
		if(instance==null){
			synchronized (HttpEngine.class) {
				if(instance ==null){
					instance = new HttpEngine();
				}
			}
		}
		return instance ;
	}
	public HttpURLConnection sendRequest(String urlsString,String requestType,HashMap<String, String> requestHeader ,HashMap<String, String> requestParams){
		
		/*如果是GET 组装 url*/
		if(requestType.equals(REQUEST_TYPE_GET)){
			/*添加请求参数到url后面*/
			if(requestParams!=null){
				urlsString+="?";
				for (Map.Entry<String,String> entry:requestParams.entrySet()) {
					urlsString+=entry.getKey()+"="+entry.getValue()+"&";
				}
				urlsString=urlsString.substring(0,urlsString.length()-1);
			}
		}
		
		try {
			URL url= new URL(urlsString);	
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestType);
			connection.setDoOutput(true);
			/*如果有请求头，添加请求头*/
			if(requestHeader!=null){
				for (Map.Entry<String,String> entry:requestHeader.entrySet()) {
					connection.addRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			
			/*组装POST实体*/
			if(requestType.equals(REQUEST_TYPE_POST)){
				if(requestParams!=null){
					StringBuffer params = new StringBuffer();
					for (Map.Entry<String,String> entry:requestParams.entrySet()) {
					params.append(entry.getKey()+"="+entry.getValue());
					params.append("&");
					}
				params.deleteCharAt(params.length()-1);
				byte[] bypes = params.toString().getBytes();
				connection.getOutputStream().write(bypes);// 输入参数	
				}
			}
			connection.connect();
			if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
				return connection;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getStringFromCon(HttpURLConnection connection){
		try {
			BufferedReader brBufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sbBuffer =new StringBuffer();
			String string="";
			while((string =brBufferedReader.readLine())!=null){
				sbBuffer.append(string);
			}
			brBufferedReader.close();
			return sbBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
