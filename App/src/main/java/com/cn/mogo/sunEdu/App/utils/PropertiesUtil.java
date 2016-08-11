package com.cn.mogo.sunEdu.App.utils;/**
 * Created by Administrator on 2016/6/7 0007.
 */

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtil
 *
 * @author xufeng
 * @date 2016/6/7 0007
 */
public class PropertiesUtil {

	public static String getValue(String key) {
		Properties properties = new Properties();
		try {
			InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/redis.properties");
			properties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(getValue("redis.server"));
	}

	public static Properties getDefaultProperties(String classPath) {

		Resource resource = new ClassPathResource("/" + classPath);
		EncodedResource encRes = new EncodedResource(resource, "UTF-8");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(encRes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}


}

