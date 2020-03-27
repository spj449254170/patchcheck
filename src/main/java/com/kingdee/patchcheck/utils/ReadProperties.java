package com.kingdee.patchcheck.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;

public class ReadProperties {

    /*
     * @param  propertiesPath 配置文件全路径
     * @param  key 需要在配置文件中获取的key值
     * */
    public static String getValue(String propertiesPath, String key) {
        String value = null;
        Properties prop = new Properties();
        try {
            ClassLoader classLoader = ReadProperties.class.getClassLoader();// 读取属性文件xxxxx.properties
            InputStream in = classLoader.getResourceAsStream(propertiesPath);
            prop.load(in); /// 加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                if (it.next().equals(key)) {
                    value = prop.getProperty(key);
                }
            }
            in.close();
        } catch (Exception e) {

        }
        return value;
    }
    public static void main(String[] args) {
        System.out.println(ReadProperties.getValue("ftp.properties", "host"));
    }
}
