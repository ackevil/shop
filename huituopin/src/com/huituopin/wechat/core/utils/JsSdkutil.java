package com.huituopin.wechat.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public  class JsSdkutil {
	
	  public static void downloadImg(String mediaId, String access_token, String filePath) throws Exception
	  {
	    InputStream is = null;
	    String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + access_token + "&media_id=" + mediaId;
	   // http://file.api.weixin.qq.com/cgi-bin/media/get
	    System.out.println(url);
	    
	      URL urlGet = new URL(url);
	      HttpURLConnection conn = (HttpURLConnection)urlGet.openConnection();
	      conn.setRequestMethod("GET");
	      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	      conn.setDoOutput(true);
	      conn.setDoInput(true);
	      conn.connect();
	      is = conn.getInputStream();

	      byte[] data = new byte[1024];
	      int len = 0;
	      FileOutputStream fileOutputStream = null;
	      
	    	File file=new File(filePath);
	    	if(file.exists())
	    	{
	    		return ;
	    	}
	    	if(!file.getParentFile().exists())
	    	{
	    		file.getParentFile().mkdirs();
	    	}
	    	file.createNewFile();
	        fileOutputStream = new FileOutputStream(file);

	        while ((len = is.read(data)) != -1)
	        	fileOutputStream.write(data, 0, len);
	        
	        if (is != null) 
	            is.close();
	        if (fileOutputStream != null)
	        	fileOutputStream.close();
	        
	        
	  }    
	   
	  
}
