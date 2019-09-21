package com.boot.util.qq.weixin.mp.aes;

import java.io.File;




import net.sf.json.JSONObject;

/**@desc  : 临时素材业务类
 * 
 * @author: shirayner
 * @date  : 2017-8-18 下午2:07:25
 */
public class TempMaterialService {
    //上传临时素材url
    public static String uploadTempMaterial_url="https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    /**
     * @desc ：上传临时素材
     *  
     * @param accessToken   接口访问凭证 
     * @param type   媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file) 
     * @param fileUrl  本地文件的url。例如 "D/1.img"。
     * @return JSONObject   上传成功后，微信服务器返回的参数，有type、media_id    、created_at
     */
    public JSONObject uploadTempMaterial(String accessToken,String type,String fileUrl){
        //1.创建本地文件
        File file=new File(fileUrl);
        
        //2.拼接请求url
        uploadTempMaterial_url=uploadTempMaterial_url.replace("ACCESS_TOKEN", accessToken)
                .replace("TYPE", type);
        
        //3.调用接口，发送请求，上传文件到微信服务器
        String result=WeiXinUtil.httpRequest(uploadTempMaterial_url, file);
        
        //4.json字符串转对象：解析返回值，json反序列化
        result = result.replaceAll("[\\\\]", "");
        System.out.println("result:" + result);
        JSONObject resultJSON = JSONObject.fromObject(result);
        
        //5.返回参数判断
        if (resultJSON != null) {
            if (resultJSON.get("media_id") != null) {
                System.out.println("上传" + type + "永久素材成功");
                return resultJSON;
            } else {
                System.out.println("上传" + type + "永久素材失败");
            }
        }
        return null;
    }

}