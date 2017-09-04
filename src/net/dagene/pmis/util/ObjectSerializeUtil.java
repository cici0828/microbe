package net.dagene.pmis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

public class ObjectSerializeUtil {
	/*
	 * 反序列化对象
	 */
	public static Object getObjectFromJsonString(String jsonString,
			Class pojoClass) throws Exception {
		return getObjectFromJsonString(jsonString, pojoClass, null);
	}
	
	public static Object getObjectFromJsonString(String jsonString,
			Class pojoClass, Map<String, Class> classMap) throws Exception {
		Object pojo;

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoClass, classMap);
		return pojo;
		/*
		 * JsonConfig jsonConfig = new JsonConfig();
		 * jsonConfig.setRootClass(pojoCalss);
		 * JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new
		 * String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		 */
	}

	/*
	 * 序列化对象
	 */
	public static String getStrFromObj(Object obj) throws Exception {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerDefaultValueProcessor(Integer.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						if (type != null
								&& Integer.class.isAssignableFrom(type)) {
							return Integer.valueOf(0);
						}
						return JSONNull.getInstance();
					}
				});
		jsonConfig.registerDefaultValueProcessor(Date.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						if (type != null && Date.class.isAssignableFrom(type)) {
							return 0;
						}
						return JSONNull.getInstance();
					}
				});

		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor(""));

		// jsonConfig.setIgnoreDefaultExcludes(false);
		JSONObject object = JSONObject.fromObject(obj, jsonConfig);
		return object.toString();
	}
	
	public static void main(String[] args){

	}
}
