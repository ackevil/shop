package com.huituopin.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.persistence.Entity;

/**
 * 解析properties文件
 * @param key
 * @return
 * @throws IOException
 */
@Entity
public class Analytical {
	
	
	/**
	 * 解析properties文件
	 * @param key
	 * @return
	 * @throws IOException
	 */
	 public static String readPropertiesFile(String key)   {
				InputStream ins = Analytical.class.getClassLoader().getResourceAsStream("config.properties");
				Properties pro = new Properties();
				try {
					pro.load(ins);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return pro.getProperty(key);
	 }
	 /**
		 * 解析properties文件
		 * @param key
		 * @return
		 * @throws IOException
		 */
	 public static String readjdbcFile(String key) throws IOException  {
			InputStream ins = Analytical.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties pro = new Properties();
			pro.load(ins);
			return pro.getProperty(key);
}
	
	 

}
