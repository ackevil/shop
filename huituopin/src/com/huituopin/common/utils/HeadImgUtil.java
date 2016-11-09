package com.huituopin.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HeadImgUtil {
	public static void wechatImgToLocal(String imgUrl,String fileName){
		InputStream is = null;
		OutputStream os = null;
		try {
			URL url = new URL(imgUrl);
			URLConnection con = url.openConnection();
			is = con.getInputStream();
			byte[] bs = new byte[1024];
			int len;
			String path = fileName.substring(0,fileName.lastIndexOf("/"));
			File f = new File(path);
			if(!f.exists())
				f.mkdirs();
			os = new FileOutputStream(fileName);
			while((len = is.read(bs))!=-1){
				os.write(bs,0,len);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
				is.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
