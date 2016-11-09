package com.huituopin.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.ConfigurationException;
import javax.persistence.Entity;

/**
 * 读取src下 *.properties配置文件
 * 
 * @author tangjingyuan
 * @time 2014-04-17
 * 
 */
@Entity
public class Configuration {
    private Properties properties = new Properties();// 记录配置项

    private String fn = null;// 记录配置文件名

    // 此构造方法用于新建配置文件
    public Configuration() {
    }

    // 从指定文件名读入配置信息
    public Configuration(String fileName) {
        try {
            InputStream fin = Configuration.class.getResourceAsStream(fileName);
            properties.load(fin); // 载入文件
            fin.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        fn = fileName;
    }

    // 指定配置项名称，返回配置值
    public String getValue(String itemName) {
        return properties.getProperty(itemName);
    }

    // 指定配置项名称和默认值，返回配置值
    public String getValue(String itemName, String defaultValue) {
        return properties.getProperty(itemName, defaultValue);
    }

    // 设置配置项名称及其值
    public void setValue(String itemName, String value) {
        properties.setProperty(itemName, value);
    }

    // 保存配置文件，指定文件名和抬头描述
    public void saveFile(String fileName, String description) throws ConfigurationException {
        try {
            String file = Configuration.class.getResource(fileName).getFile();
            File f = new File(file);
            FileOutputStream fout = new FileOutputStream(f);
            properties.store(fout, description);// 保存文件
            fout.close();
        } catch (IOException ex) {
            throw new ConfigurationException("无法保存指定的配置文件:" + fileName);
        }
    }

    // 保存配置文件，指定文件名
    public void saveFile(String fileName) throws ConfigurationException {
        saveFile(fileName, "");
    }

    // 保存配置文件，采用原文件名
    public void saveFile() throws ConfigurationException {
        if (fn.length() == 0)
            throw new ConfigurationException("需指定保存的配置文件名");
        saveFile(fn);
    }
}
