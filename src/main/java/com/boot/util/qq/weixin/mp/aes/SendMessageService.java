package com.boot.util.qq.weixin.mp.aes;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.google.gson.Gson;




/**@desc  : 发送消息
 * 
 * @author: shirayner
 * @date  : 2017-8-18 上午10:06:23
 */
public class SendMessageService {
    private static Logger log = LoggerFactory.getLogger(SendMessageService.class);  

    private  static  String sendMessage_url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";  

    /**
     * @desc ：0.公共方法：发送消息
     *  
     * @param accessToken
     * @param message void
     */
    public void sendMessage(BaseMessage message){
		WeiXinUtil weiXinUtil = new WeiXinUtil();
        //1.获取json字符串：将message对象转换为json字符串    
        Gson gson = new Gson(); 
        String jsonMessage =gson.toJson(message);      //使用gson.toJson(user)即可将user对象顺序转成json
        String accessToken = weiXinUtil.getRedisToken();
        log.info("发送消息sendMessage,得到的redis中存放的token【"+accessToken+"】");
        //2.获取请求的url  
        String url=sendMessage_url.replace("ACCESS_TOKEN", accessToken);

        //3.调用接口，发送消息
        JSONObject jsonObject = WeiXinUtil.httpRequest(url, "POST", jsonMessage);  
        

        //4.错误消息处理 
        int errcode = jsonObject.getInt("errcode");
        if (null != jsonObject) {
       	 if (errcode==40014) {
       		 log.info("发送消息失败,token 失效，调用后台设置redis的token方法"+jsonObject.toString());
    		 weiXinUtil.setRedisToken();
    		 
    	 }
            if (0 != jsonObject.getInt("errcode")) {  
                log.error("消息发送失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
    }
    
    
    
    
}