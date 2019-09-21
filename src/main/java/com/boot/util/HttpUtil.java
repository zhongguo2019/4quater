package com.boot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	/**
	 * 
	 * <获取参数map>
	 * 
	 * 
	 * 
	 * @return 参数map
	 * 
	 * @throws Exception
	 * 
	 */

	public static Map<String, Object> getParameterMap(HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, String[]> tempMap = request.getParameterMap();

		Set<String> keys = tempMap.keySet();

		for (String key : keys) {

			byte source[] = request.getParameter(key).getBytes("iso8859-1");

			String modelname = new String(source, "UTF-8");

			resultMap.put(key, modelname);

		}

		return resultMap;

	}

	public static String getDefaultKeyString(Map<String, Object> mpValue, String key) {
		String strRtn = null;
		if (null == mpValue) {
			return strRtn;
		}

		if (null == mpValue.get(key)) {
			return strRtn;
		} else {
			strRtn = mpValue.get(key).toString();
		}

		return strRtn;
	}

	public static String getPostJsonData(HttpServletRequest request) {
		JSONObject result = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = request.getReader();) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
			result = JSONObject.parseObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String strRtn = result.getString("code"); // 拿到微信端传过来的参数
		return strRtn;
	}

	public static String getPostStringData(HttpServletRequest request) throws IOException {
		String result = null;
		StringBuilder sb = new StringBuilder();

		try (BufferedReader reader = request.getReader();) {
			char[] buff = new char[1024];
			int len;
			if (null != reader) {
				while ((len = reader.read(buff)) != -1) {
					sb.append(buff, 0, len);
				}
				result = sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String strRtn = result; // 拿到微信端传过来的参数
		return strRtn;
	}

}
