package com.wwq.tank.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

	static Properties props = new Properties();
	
	static {
		init();
	}

	private static void init() {
		try {
			props.load(Properties.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object get(String key) {
		if(props == null) return null;
		return props.get(key);
	}
	
	public static int getToInt(String key, int defValue) {
		Object value = get(key);
		if(value == null) return defValue;
		return Integer.valueOf(defValue);
	}
}