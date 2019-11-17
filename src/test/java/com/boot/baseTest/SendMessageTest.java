package com.boot.baseTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boot.util.qq.weixin.mp.aes.AccessToken;
import com.boot.util.qq.weixin.mp.aes.Article;
import com.boot.util.qq.weixin.mp.aes.BaseMessage;
import com.boot.util.qq.weixin.mp.aes.FileMessage;
import com.boot.util.qq.weixin.mp.aes.ImgMessage;
import com.boot.util.qq.weixin.mp.aes.Media;
import com.boot.util.qq.weixin.mp.aes.MyX509TrustManager;
import com.boot.util.qq.weixin.mp.aes.News;
import com.boot.util.qq.weixin.mp.aes.NewsMessage;
import com.boot.util.qq.weixin.mp.aes.Text;
import com.boot.util.qq.weixin.mp.aes.TextMessage;
import com.boot.util.qq.weixin.mp.aes.Textcard;
import com.boot.util.qq.weixin.mp.aes.TextcardMessage;
import com.boot.util.qq.weixin.mp.aes.Video;
import com.boot.util.qq.weixin.mp.aes.VideoMessage;
import com.boot.util.qq.weixin.mp.aes.VoiceMessage;
import com.boot.util.qq.weixin.mp.aes.SendMessageService;
import com.boot.util.qq.weixin.mp.aes.TempMaterialService;
import com.boot.util.qq.weixin.mp.aes.WeiXinParamesUtil;
import com.google.gson.Gson;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


/**
 * @desc : 消息推送之发送消息
 * 
 * @author: shirayner
 * @date : 2017-8-18 上午10:04:55
 */
public class SendMessageTest {
	private static Logger log = LoggerFactory.getLogger(SendMessageTest.class);
	private static String sendMessage_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

	// 1.发送文本消息
	@Test
	public void testSendTextMessage() {
		// 0.设置消息内容
		String content = "消息测试：通过企业微信将消息发到群通知里。\n出发前可查看" + "<a href=\"http://work.weixin.qq.com\">邮件中心视频实况"
				+ "</a>，聪明避开排队。";

		// 1.创建文本消息对象
		TextMessage message = new TextMessage();
		// 1.1非必需
		//message.setTouser("@all"); //不区分大小写
		message.setToparty("18");
		// txtMsg.setTotag(totag);
		// txtMsg.setSafe(0);

		// 1.2必需
		message.setMsgtype("text");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Text text = new Text();
		text.setContent(content);
		message.setText(text);

		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:" + accessToken);

		// 3.发送消息：调用业务类，发送消息
	    sendMessage(message,accessToken);

	}

	// 2.发送文本卡片消息
	// @Test
	public void testSendTextcardMessage() {
		// 0.设置消息内容
		String title = "代办事宜";
		String description = "<div class=\"gray\">2017年8月18日</div> <div class=\"normal\">"
				+ "恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">" + "请于2017年10月10日前联系行政同事领取</div>";
		String url = "http://www.cnblogs.com/shirui/p/7297872.html";

		// 1.创建文本卡片消息对象
		TextcardMessage message = new TextcardMessage();
		// 1.1非必需
		message.setTouser("shirui"); // 不区分大小写
		// message.setToparty("1");
		// message.setTotag(totag);
		// message.setSafe(0);

		// 1.2必需
		message.setMsgtype("textcard");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Textcard textcard = new Textcard();
		textcard.setTitle(title);
		textcard.setDescription(description);
		textcard.setUrl(url);
		message.setTextcard(textcard);

		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret)
				.getToken();
		System.out.println("accessToken:" + accessToken);

		// 3.发送消息：调用业务类，发送消息
		sendMessage(message,accessToken);

	}

	// 3.发送图片消息---无效的media_id
	// @Test
	public void testSendImgMessage() {
		// 0.设置消息内容
		String media_id = "MEDIA_ID";
		// 1.创建图片消息对象
		ImgMessage message = new ImgMessage();
		// 1.1非必需
		message.setTouser("@all"); // 不区分大小写
		// textMessage.setToparty("1");
		// txtMsg.setTotag(totag);
		// txtMsg.setSafe(0);

		// 1.2必需
		message.setMsgtype("image");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Media image = new Media();
		image.setMedia_id(media_id);
		message.setImage(image);

		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret)
				.getToken();
		System.out.println("accessToken:" + accessToken);

		// 3.发送消息：调用业务类，发送消息
	sendMessage(message,accessToken);

	}

	// 4.发送语音消息---无效的media_id
	// @Test
	public void testSendVoiceMessage() {
		// 0.设置消息内容
		String media_id = "MEDIA_ID";
		// 1.创建语音消息对象
		VoiceMessage message = new VoiceMessage();
		// 1.1非必需
		message.setTouser("@all"); // 不区分大小写
		// textMessage.setToparty("1");
		// txtMsg.setTotag(totag);
		// txtMsg.setSafe(0);

		// 1.2必需
		message.setMsgtype("image");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Media voice = new Media();
		voice.setMedia_id(media_id);
		message.setVoice(voice);

		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret)
				.getToken();
		System.out.println("accessToken:" + accessToken);

		// 3.发送消息：调用业务类，发送消息
        sendMessage(message,accessToken);

	}

	// 5.发送视频消息
	// @Test
	public void testSendVideoMessage() {
		// 0.设置消息内容
		String media_id = "MEDIA_ID";
		String title = "视频示例";
		String description = "好看的视频";

		// 1.创建视频消息对象
		VideoMessage message = new VideoMessage();
		// 1.1非必需
		message.setTouser("@all"); // 不区分大小写
		// message.setToparty("1");
		// message.setTotag(totag);
		// message.setSafe(0);

		// 1.2必需
		message.setMsgtype("video");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Video video = new Video();
		video.setMedia_id(media_id);
		video.setTitle(title);
		video.setDescription(description);
		message.setVideo(video);

		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret)
				.getToken();
		System.out.println("accessToken:" + accessToken);

		// 3.发送消息：调用业务类，发送消息
       sendMessage(message,accessToken);

	}

	// 6.发送文件消息
	//@Test
	public void testSendFileMessage() {
		// 0.设置消息内容
		String media_id = "3wQINVEJWI3sZmMQSdx3VqIdZ5Y32H8pbkgXbtQkfZF8Dy2TqHz9fgiAbO5qp5Uoy";

		// 1.创建文件对象
		FileMessage message = new FileMessage();
		// 1.1非必需
		message.setTouser("ZhaoZuLong"); // 不区分大小写
		// textMessage.setToparty("1");
		// txtMsg.setTotag(totag);
		// txtMsg.setSafe(0);

		// 1.2必需
		message.setMsgtype("file");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Media file = new Media();
		file.setMedia_id(media_id);
		message.setFile(file);

		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret)
				.getToken();
		System.out.println("accessToken:" + accessToken);

		// 3.发送消息：调用业务类，发送消息
		sendMessage(message,accessToken);

	}

	// 7.发送图文消息
	// @Test
	public void testSendNewsMessage() {

		// 1.创建图文消息对象
		NewsMessage message = new NewsMessage();
		// 1.1非必需
		message.setTouser("@all"); // 不区分大小写
		// textMessage.setToparty("1");
		// txtMsg.setTotag(totag);
		// txtMsg.setSafe(0);

		// 1.2必需
		message.setMsgtype("news");
		message.setAgentid(WeiXinParamesUtil.agentId);
		// 设置图文消息
		Article article1 = new Article();
		article1.setTitle("青年文摘");
		article1.setDescription("这是一个很特别的描述");
		article1.setPicurl("http://mat1.gtimg.com/fashion/images/index/2017/08/18/tpzs2.jpg");
		article1.setUrl("http://www.cnblogs.com/shirui/p/7297872.html");

		List<Article> articles = new ArrayList<Article>();
		articles.add(article1);

		News news = new News();
		news.setArticles(articles);
		message.setNews(news);

		// 2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken = getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret)
				.getToken();
		System.out.println("accessToken:" + accessToken);

		// 3.发送消息：调用业务类，发送消息
        sendMessage(message,accessToken);

	}

	/*
	 * // @Test public void testUploadTempMaterial() { // 1.初始化参数 String fileUrl =
	 * "G:/江西农信银行驻场项目管理规范.docx"; String type = "file"; WeiXinUtil weiXinUtil = new
	 * WeiXinUtil(); String accessToken = weiXinUtil.getRedisToken(); //
	 * 2.调用业务类，上传临时素材 WeiXinUtil.uploadTempMaterial(type, fileUrl); }
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpId}&corpsecret={corpsecret}";

		String requestUrl = access_token_url.replace("{corpId}", appid).replace("{corpsecret}", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);

		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 1.发起https请求并获取结果
	 * 
	 * @param requestUrl    请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr     提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	public void sendMessage(BaseMessage message, String accessToken) {
		// 1.获取json字符串：将message对象转换为json字符串
		Gson gson = new Gson();
		String jsonMessage = gson.toJson(message); // 使用gson.toJson(user)即可将user对象顺序转成json
		// 2.获取请求的url
		String url = sendMessage_url.replace("ACCESS_TOKEN", accessToken);
		
        log.info("url:"+url);
        log.info("jsonMessage:"+jsonMessage);
		// 3.调用接口，发送消息
		JSONObject jsonObject = httpRequest(url, "POST", jsonMessage);
		log.info("发送消息后返回jsonObject："+jsonObject);

		// 4.错误消息处理
		int errcode = jsonObject.getInt("errcode");

		if (null != jsonObject) {
			if (errcode == 40014) {
				log.info("发送消息失败,token 失效，调用后台设置redis的token方法" + jsonObject.toString());

			}
			if (0 != jsonObject.getInt("errcode")) {
				log.error("消息发送失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}

}