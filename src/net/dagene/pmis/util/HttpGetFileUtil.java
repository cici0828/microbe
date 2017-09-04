package net.dagene.pmis.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpGetFileUtil {

	// 获取文件数据
	public static byte[] GetFileBytes(String file_name) {
		try {
			String domain = PropertiesUtil
					.getResourceProperty("resourceImageDomain");
			domain = domain.lastIndexOf(1) == '/' ? domain : domain + "/";
			URL url = new URL(domain + file_name);
			// new
			// URL("http://localhost:3000/cslt/getCsltPicByID.action?reportpic=133900394_102.jpg");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(60 * 1000);
			InputStream inStream = conn.getInputStream();

			byte[] data = readInputStream(inStream);
			// File imageFile = new File("C:/BeautyGirl.jpg");
			// FileOutputStream outStream = new FileOutputStream(imageFile);
			// outStream.write(data);
			// outStream.close();
			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	public static String readInputStreamString(InputStream inStream)
			throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toString();
	}

	/**
	 * 接口调用 网页获取信息
	 */
	public static String httpURLConnectionPOST(String POST_URL,
			Map<String, String> params) {
		StringBuilder sb = new StringBuilder(); // 用来存储响应数据
		try {
			URL url = new URL(POST_URL);

			// 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
			// (标识一个url所引用的远程对象连接)
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();// 此时cnnection只是为一个连接对象,待连接中

			// 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
			connection.setDoOutput(true);

			// 设置连接输入流为true
			connection.setDoInput(true);

			// 设置请求方式为post
			connection.setRequestMethod("POST");

			// post请求缓存设为false
			connection.setUseCaches(false);

			// 设置该HttpURLConnection实例是否自动执行重定向
			connection.setInstanceFollowRedirects(true);

			// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
			// application/x-javascript text/xml->xml数据
			// application/x-javascript->json对象
			// application/x-www-form-urlencoded->表单数据
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			// 建立连接
			// (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
			connection.connect();

			// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
			DataOutputStream dataout = new DataOutputStream(
					connection.getOutputStream());

			// String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //
			// URLEncoder.encode()方法
			// 为字符串进行编码
			String parm = "";
			if (!params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					parm += URLEncoder.encode(entry.getKey(), "utf-8") + "="
							+ URLEncoder.encode(entry.getValue(), "utf-8")
							+ "&";
				}
				parm.substring(0, parm.length() - 1);
			}

			// 将参数输出到连接
			dataout.writeBytes(parm);

			// 输出完成后刷新并关闭流
			dataout.flush();
			dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

			System.out.println(connection.getResponseCode());

			// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			// 循环读取流,若不到结尾处
			while ((line = bf.readLine()) != null) {
				sb.append(bf.readLine());
			}
			bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
			connection.disconnect(); // 销毁连接
			// System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
