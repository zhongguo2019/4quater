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
		BufferedReader reader=null;   
        char[] buff = new char[1024];
	    int len;
        try
        {
            reader = request.getReader();
			if (null != reader) {
				while ((len = reader.read(buff)) != -1) {
					sb.append(buff, 0, len);
				}
				result = sb.toString();
			}
        } catch (Exception e) {

            e.printStackTrace( );

            // TODO: handle exception

        }finally {

            try {

                //这里关闭流要从大到小关闭

                //fw.close();   

                //bw.close();

                //这样的顺序就是不行，fw流自己关闭了。然后bw流又把

                //fw流关闭一次就会说java.io.IOException: Stream closed

            	reader.close();

            } catch (IOException e)  {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }
      }
		String strRtn = result; // 拿到微信端传过来的参数
		return strRtn;
	}

}
