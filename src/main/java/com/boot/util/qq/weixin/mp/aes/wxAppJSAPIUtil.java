package com.boot.util.qq.weixin.mp.aes;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
import com.alibaba.fastjson.JSONObject;
 
public class wxAppJSAPIUtil {
 
	/**
	 * @name 中文名称
	 * @description 根据页面URL和页面ticket生成接入JS-SDK接入码
	 * @time 创建时间:2018年7月23日19:58:05
	 * @param url：接入js-sdk的页面地址
	 * 			ticket：通过token生成的接入js-sdk的ticket
	 * @return 请求返回接入js-sdk所需json对象
	 * @author 朱浩
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static JSONObject getSignature(String url, String ticket) {
		JSONObject rul = new JSONObject();
 
		String noncestr = getRandomString(16);
		String timestamp = (int)(System.currentTimeMillis()/1000)+"";
		String sign = "";
		sign += wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL_PARAM_TICKET
				+ wxappJSAPIConstants.QYWX_EQUAL
				+ ticket
				+ wxappJSAPIConstants.QYWX_AND
				+ wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL_PARAM_NONCESTR
				+ wxappJSAPIConstants.QYWX_EQUAL
				+ noncestr
				+ wxappJSAPIConstants.QYWX_AND
				+ wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL_PARAM_TIMESTAMP
				+ wxappJSAPIConstants.QYWX_EQUAL + timestamp
				+ wxappJSAPIConstants.QYWX_AND
				+ wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL_PARAM_URL
				+ wxappJSAPIConstants.QYWX_EQUAL + url;
		String signature = "";
		try {
			// 指定sha1算法
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(sign.getBytes());
			// 获取字节数组
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			signature = hexString.toString();
 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		rul.put(wxappJSAPIConstants.QYWX_GET_JSAPITICKET_RETURN_SIGNATURE,
				signature);
		rul.put(wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL_PARAM_NONCESTR,
				noncestr);
		rul.put(wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL_PARAM_TIMESTAMP,
				timestamp);
		return rul;
	}
 
	/**
	 * @name 中文名称
	 * @description 根据扫码登录返回的code值和应用token值获取扫码登录人用户id(注：id为企业微信扫码人id)
	 * @time 创建时间:2018年7月23日14:07:28
	 * @param code
	 *            :前台扫码登录后微信js插件会自动跳转链接，且附带该code。 accessToken: 应用调用接口凭证，
	 *            该类下getTencentToken()方法可直接获取。
	 * @return 请求返回userid
	 * @author 朱浩
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static String getTencentUserInfo(String code, String accessToken) {
		String url = "";
		url += wxappJSAPIConstants.QYWX_GET_USERINFO_URL
				+ wxappJSAPIConstants.QYWX_QUERY;
		url += wxappJSAPIConstants.QYWX_GET_USERINFO_URL_PARAM_TOKEN
				+ wxappJSAPIConstants.QYWX_EQUAL + accessToken;
		url += wxappJSAPIConstants.QYWX_AND;
		url += wxappJSAPIConstants.QYWX_GET_USERINFO_URL_PARAM_CODE
				+ wxappJSAPIConstants.QYWX_EQUAL + code;
		
		
		System.out.println(url);
		
		JSONObject userInfoJson = sendPostRequest(url);
		String errcode = userInfoJson
				.getString(wxappJSAPIConstants.QYWX_GET_USERINFO_RETURN_ERRCODE);
		if (wxappJSAPIConstants.QYWX_GET_USERINFO_RETURN_SUCCESS_CODE
				.equals(errcode)) {
			return userInfoJson
					.getString(wxappJSAPIConstants.QYWX_GET_USERINFO_RETURN_USERID);
		} else {
		}
		return null;
	}
 
	/**
	 * @name 中文名称
	 * @description 获取企业微信Token（应用调用接口凭证） 该值具有以下特性： 1、通用性：该企业微信应用下通用， 2、
	 *              有效性：该值有效时长为7200秒， 3、微信端不可长期无限制请求，推荐使用缓存保存改值
	 * @time 创建时间:2018年7月23日14:07:28
	 * @param 请求URL地址
	 *            ，参数需自行拼接
	 * @return 请求返回token值
	 * @author 朱浩
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static String getTencentToken() {
		String url = "";
		url += wxappJSAPIConstants.QYWX_GET_TOKEN_URL
				+ wxappJSAPIConstants.QYWX_QUERY;
		url += wxappJSAPIConstants.QYWX_GET_TOKEN_URL_PARAM_CORPID
				+ wxappJSAPIConstants.QYWX_EQUAL
				+ wxappJSAPIConstants.QYWX_CORPID;
		url += wxappJSAPIConstants.QYWX_AND
				+ wxappJSAPIConstants.QYWX_GET_TOKEN_URL_PARAM_CORPSECRET
				+ wxappJSAPIConstants.QYWX_EQUAL;
		url += wxappJSAPIConstants.QYWX_CORPSECRET;
		JSONObject tokenJson = sendPostRequest(url);
		System.out.println("getTencentToken url is 【"+url+"】");
		String errcode = tokenJson
				.getString(wxappJSAPIConstants.QYWX_GET_TOKEN_RETURN_ERRCODE);
		if (wxappJSAPIConstants.QYWX_GET_TOKEN_RETURN_SUCCESS_CODE
				.equals(errcode)) {
			return tokenJson
					.getString(wxappJSAPIConstants.QYWX_GET_TOKEN_RETURN_TOKEN);
		} else {
		}
		return null;
	}
 
	/**
	 * @name 中文名称
	 * @description 根据token获取接入js-sdk的ticket
	 * @time 创建时间:2018年7月23日19:33:47
	 * @param accessToken
	 *            : 应用调用接口凭证， 该类下getTencentToken()方法可直接获取。
	 * @return 请求返回ticket值。
	 * @author 朱浩
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static String getTencentJSSDKTicket(String token) {
		String url = "";
		url += wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL
				+ wxappJSAPIConstants.QYWX_QUERY;
		url += wxappJSAPIConstants.QYWX_GET_JSAPITICKET_URL_PARAM_TOKEN
				+ wxappJSAPIConstants.QYWX_EQUAL + token;
		JSONObject ticketJson = sendPostRequest(url);
		String errcode = ticketJson
				.getString(wxappJSAPIConstants.QYWX_GET_JSAPITICKET_RETURN_ERRCODE);
		if (wxappJSAPIConstants.QYWX_GET_JSAPITICKET_RETURN_SUCCESS_CODE
				.equals(errcode)) {
			return ticketJson
					.getString(wxappJSAPIConstants.QYWX_GET_JSAPITICKET_RETURN_TICKET);
		} else {
		}
		return null;
	}
 
	/**
	 * @name 中文名称
	 * @description 相关说明
	 * @time 创建时间:2018年7月23日11:48:33
	 * @param 请求URL地址
	 *            ，参数需自行拼接
	 * @return 请求返回json对象
	 * @author 朱浩
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	private static JSONObject sendPostRequest(String url) {
		StringBuffer stringBuffer = new StringBuffer("");
		try {
			URL postUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.parseObject(stringBuffer.toString());
	}
 
	/**
	 * @name 中文名称
	 * @description 获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
	 * @time 创建时间:2018年7月23日14:17:21
	 * @param 获取字符串长度
	 * @return 对应长度的随机字符串
	 * @author 朱浩
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	private static String getRandomString(int length) {
		// 随机字符串的随机字符库
		String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		int len = KeyString.length();
		for (int i = 0; i < length; i++) {
			sb.append(KeyString.charAt((int) Math.round(Math.random()
					* (len - 1))));
		}
		return sb.toString();
	}
}