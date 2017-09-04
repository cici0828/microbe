package net.dagene.pmis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties properties;
	private static String filename = "/resource.properties";

	//获取值
	public static String getResourceProperty(String key) {
		String value = "";
		Properties pro = GetProperties();
		if (pro != null)
			value = pro.getProperty(key);
		return value;
	}

	// 读文件获取Properties对象
	private static Properties GetProperties() {
		if (properties != null) {
			return properties;
		}
		properties = new Properties();
		try {
			InputStream inputStream = PropertiesUtil.class
					.getResourceAsStream(filename);
			// InputStream inputStream = new FileInputStream(filename);
			properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

//	// 写文件
//	public static void WriteProperties(String filename, Map<String, String> map) {
//		Properties properties = new Properties();
//		try {
//			URL u = PropertiesUtil.class.getResource(filename);
//			OutputStream outputStream = new FileOutputStream(u.getPath());
//			// OutputStream outputStream = new FileOutputStream(filename);
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//				properties.setProperty(entry.getKey(), entry.getValue());
//			}
//			properties.store(outputStream, "author:cslt");
//			outputStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	//
	// public static void main(String[] args) {
	//
	// String readfile = "e:" + File.separator + "readfile.properties";
	// String writefile = "e:" + File.separator + "writefile.properties";
	// String readxmlfile = "e:" + File.separator + "readxmlfile.xml";
	// String writexmlfile = "e:" + File.separator + "writexmlfile.xml";
	// String readtxtfile = "e:" + File.separator + "readtxtfile.txt";
	// String writetxtfile = "e:" + File.separator + "writetxtfile.txt";
	//
	// readPropertiesFile(readfile); // 读取properties文件
	// writePropertiesFile(writefile); // 写properties文件
	// readPropertiesFileFromXML(readxmlfile); // 读取XML文件
	// writePropertiesFileToXML(writexmlfile); // 写XML文件
	// readPropertiesFile(readtxtfile); // 读取txt文件
	// writePropertiesFile(writetxtfile); // 写txt文件
	// }
	//
	//
	// // 读取资源文件,并处理中文乱码
	// public static void readPropertiesFile(String filename) {
	// Properties properties = new Properties();
	// try {
	// InputStream inputStream =
	// PropertiesUtil.class.getResourceAsStream(filename);
	// //InputStream inputStream = new FileInputStream(filename);
	// properties.load(inputStream);
	// inputStream.close(); // 关闭流
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// String username = properties.getProperty("username");
	// String passsword = properties.getProperty("password");
	// String chinese = properties.getProperty("chinese");
	// try {
	// chinese = new String(chinese.getBytes("ISO-8859-1"), "GBK"); // 处理中文乱码
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// System.out.println(username);
	// System.out.println(passsword);
	// System.out.println(chinese);
	// }
	//
	// // 读取XML文件,并处理中文乱码
	// public static void readPropertiesFileFromXML(String filename) {
	// Properties properties = new Properties();
	// try {
	// InputStream inputStream =
	// PropertiesUtil.class.getResourceAsStream(filename);
	// //InputStream inputStream = new FileInputStream(filename);
	// properties.loadFromXML(inputStream);
	// inputStream.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// String username = properties.getProperty("username");
	// String passsword = properties.getProperty("password");
	// String chinese = properties.getProperty("chinese"); // XML中的中文不用处理乱码，正常显示
	// System.out.println(username);
	// System.out.println(passsword);
	// System.out.println(chinese);
	// }
	//
	// // 写资源文件，含中文
	// public static void writePropertiesFile(String filename) {
	// Properties properties = new Properties();
	// try {
	// URL u=PropertiesUtil.class.getResource(filename);
	// OutputStream outputStream = new FileOutputStream(u.toString());
	// //OutputStream outputStream = new FileOutputStream(filename);
	// properties.setProperty("username", "myname");
	// properties.setProperty("password", "mypassword");
	// properties.setProperty("chinese", "中文");
	// properties.store(outputStream, "author: shixing_11@sina.com");
	// outputStream.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// // 写资源文件到XML文件，含中文
	// public static void writePropertiesFileToXML(String filename) {
	// Properties properties = new Properties();
	// try {
	// URL u=PropertiesUtil.class.getResource(filename);
	// OutputStream outputStream = new FileOutputStream(u.toString());
	// //OutputStream outputStream = new FileOutputStream(filename);
	// properties.setProperty("username", "myname");
	// properties.setProperty("password", "mypassword");
	// properties.setProperty("chinese", "中文");
	// properties.storeToXML(outputStream, "author: shixing_11@sina.com");
	// outputStream.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
