package com.boot.util.qq.weixin.mp.aes;

public class WeiXinParamesUtil {
	/**
	 * 微信参数
	 * @author shirayner
	 *
	 */
    //1.微信参数
    //token
     public final static String token = "F8nRdfYNPYhAA4UWXccHJbX";
    // encodingAESKey
    public final static String encodingAESKey = "5R0ACT0eLA1pHjG7mmYieu3Vk5VaFv8olHZuHIf5AOn";
    //企业ID
    public final static String corpId = "ww8249590afca6f785";
    //测试及开发使用企业ID 如果与自建小程序关联，要改成自建小程序的corid
  //public final static String corpId = "wxce18d28f195e164b";
    

      //通讯录秘钥
    public final static String contactsSecret = "AwUEF1uWDE6-8OJdcmDMC9Pgx9QrdOifVzNCqe8isaw";


// 生产中配置--我的日报---应用的凭证密钥
    public final static String agentSecret = "tAgAYMMHqEtNs1OX5GC4Z3c-lntzwcw2nY9XiX7HX80"; 
    //企业应用的id，整型。可在应用的设置页面查看
    public final static int agentId = 1000002;
    
// 测试中配置---我的半月报--应用的凭证密钥
//    public final static String agentSecret = "7ISBD7JF8AnVtRZtTYdxkbWP1vZNv7XuHWLFxdW2y9Y";
//    public final static int agentId = 1000003;

	/**
	 * 获取企业微信token地址及对应参数
	 */
	public static final String QYWX_GET_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
	public static final String QYWX_GET_TOKEN_URL_PARAM_CORPID = "corpid";
	public static final String QYWX_GET_TOKEN_URL_PARAM_CORPSECRET = "corpsecret";

	/**
	 * 获取企业微信token返回成功对应errcode值
	 */
	public static final String QYWX_GET_TOKEN_RETURN_SUCCESS_CODE = "0";
	public static final String QYWX_GET_TOKEN_RETURN_ERRCODE = "errcode";
	public static final String QYWX_GET_TOKEN_RETURN_ERRMSG = "errmsg";
	public static final String QYWX_GET_TOKEN_RETURN_TOKEN = "access_token";

	/**
	 * 获取企业微信引入JS-SDK的ticket地址及对应参数
	 */
	public static final String QYWX_GET_JSAPITICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket";
	public static final String QYWX_GET_JSAPITICKET_URL_PARAM_TOKEN = "access_token";

	public static final String QYWX_GET_JSAPITICKET_URL_PARAM_TICKET = "jsapi_ticket";
	public static final String QYWX_GET_JSAPITICKET_URL_PARAM_NONCESTR = "noncestr";
	public static final String QYWX_GET_JSAPITICKET_URL_PARAM_TIMESTAMP = "timestamp";
	public static final String QYWX_GET_JSAPITICKET_URL_PARAM_URL = "url";

	public static final String QYWX_GET_JSAPITICKET_RETURN_SIGNATURE = "signature";

	/**
	 * 获取用户ticket参数
	 */
	public static final String QYWX_GET_JSAPITICKET_RETURN_SUCCESS_CODE = "0";
	public static final String QYWX_GET_JSAPITICKET_RETURN_ERRCODE = "errcode";
	public static final String QYWX_GET_JSAPITICKET_RETURN_ERRMSG = "errmsg";
	public static final String QYWX_GET_JSAPITICKET_RETURN_TICKET = "ticket";

	/**
	 * 通过code获取用户信息url及其对应参数
	 */
	public static final String QYWX_GET_USERINFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
	public static final String QYWX_GET_USERINFO_URL_PARAM_TOKEN = "access_token";
	public static final String QYWX_GET_USERINFO_URL_PARAM_CODE = "code";

	/**
	 * 通过code获取用户信息返回参数
	 */
	public static final String QYWX_GET_USERINFO_RETURN_SUCCESS_CODE = "0";
	public static final String QYWX_GET_USERINFO_RETURN_ERRCODE = "errcode";
	public static final String QYWX_GET_USERINFO_RETURN_ERRMSG = "errmsg";
	public static final String QYWX_GET_USERINFO_RETURN_USERID = "UserId";

	/**
	 * 企业微信的CorpID，在企业微信管理端查看
	 */
	public static final String QYWX_CORPID = "";

	/**
	 * 授权方的网页应用ID，在具体的网页应用中查看
	 */
	public static final String QYWX_AGENTID = "";

	/**
	 * 应用的凭证密钥,在具体的网页应用中查看
	 */
	public static final String QYWX_CORPSECRET = "";

	/**
	 * 参数连接符
	 */
	public static final String QYWX_AND = "&";
	public static final String QYWX_EQUAL = "=";
	public static final String QYWX_QUERY = "?";   
 
    public final static String msgHelp="亲，提交的命令格式不对，请看帮助后进行操作!";
    public final static String helpInfor="-------------日报格式-------------\r\n" + 
    		"[日报] \r\n" + 
    		"今天\r\n" + 
    		"[XXXX报送系统]\r\n" + 
    		"1、人行统计报送工作内容描述XXXX。[XX%]\r\n" + 
    		"2、人行统计报送工作内容描述XXXX。[XX%]\r\n" + 
    		"[XXXX]\r\n" + 
    		"1、XXXX。[XX%]\r\n" + 
    		"2、XXXX。[XX%]\r\n" + 
    		"[XXXX]\r\n" + 
    		"1、XXXX。[XX%]\r\n" + 
    		"2、XXXX。[XX%]\r\n" + 
    		"明天\r\n" + 
    		"[[XXXX]\r\n" + 
    		"1、XXXX。[XX%]\r\n" + 
    		"2、XXXX。[XX%]\r\n" + 
    		"总结\r\n" + 
    		"XXXXXXX 内容不超200字。\r\n" + 
    		"\r\n" + 
    		"-------------补报格式-------------\r\n" + 
    		"[补报]\r\n" + 
    		"YYYY-MM-DD ----如：2019-09-10\r\n" + 
    		"[XXXX]\r\n" + 
    		"1、XXXX。[XX%]\r\n" + 
    		"2、XXXX。[XX%]\r\n" + 
    		"[[XXXX]\r\n" + 
    		"1、XXXX。[XX%]\r\n" + 
    		"2、XXXX。[XX%]\r\n" + 
    		"-------------查询格式-------------\r\n" + 
    		"查询\r\n" + 
    		"YYYY-MM-DD至YYYY-MM-DD\r\n" + 
    		"或\r\n" + 
    		"查询\r\n" + 
    		"YYYY-MM-DD\r\n" + 
    		"-------------下载格式-------------\r\n" + 
    		"[报告下载]\r\n" + 
    		"YYYY-MM-DD至YYYY-MM-DD\r\n" + 
    		"或\r\n" + 
    		"[报告下载]\r\n" + 
    		"YYYY-MM-DD\r\n" + 
    		"-------------调阅成员-------------\r\n" + 
    		"[调阅成员]\r\n" + 
    		"姓名\r\n" + 
    		"YYYY-MM-DD至YYYY-MM-DD\r\n" + 
    		"或\r\n" + 
    		"[调阅成员]\r\n" + 
    		"姓名\r\n" + 
    		"YYYY-MM-DD";
    //测试使用结束
    public final static String dayReportFormat = "亲，格式不对，模版如下：\r\n" + 
			"[日报]\r\n" + 
			"今天\r\n" + 
			"[系统名称一(或写其它)]\r\n" + 
			"1、工作内容描述一。[完成比例]\r\n" + 
			"2、工作内容描述二。[完成比例]\r\n" + 
			"[系统名称二(或写其它)]\r\n" + 
		     "1、工作内容描述一。[完成比例]\r\n" + 
			"2、工作内容描述二。[完成比例]\r\n" + 
			"明天\r\n" + 
			"[系统名称一(或写其它)]\r\n" + 
			"1、工作内容描述一。[完成比例]\r\n" + 
			"2、工作内容描述二。[完成比例]\r\n" + 
			"[系统名称二(或写其它)]\r\n" + 
			"1、工作内容描述一。[完成比例]\r\n" + 
			"2、工作内容描述二。[完成比例]\r\n" + 
			"总结\r\n" + 
			"内容描述，不超过200字。";
    
    //测试使用结束
    public final static String dayReportFormatAdd = "亲，格式不对，补报的模版如下：\r\n" + 
			"[补报]\r\n" + 
			"2019-09-01---补报的日期\r\n" + 
			"[人行统计报送]\r\n" + 
			"1、人行统计报送工作内容描述一。[完成比例]\r\n" + 
			"2、人行统计报送工作内容描述二。[完成比例]\r\n" + 
			"[PISA报送]\r\n" + 
			"1、PISA报送容描述一。[完成比例]\r\n" + 
			"2、PISA报送工作内容描述二。[完成比例]\r\n" + 
			"[其它]\r\n" + 
			"1、其它工作内容描述一。[完成比例]\r\n" + 
			"2、其它工作内容描述二。[完成比例]";
    public final static String dayReportFormatQuery = "亲，命令格式不对，查询操作的命令格式：\r\n" + 
    		"查询 ---关键字及格式\r\n" + 
    		"日期 ---关键字及格式\r\n" + 
    		"或是以下格式：\r\n" + 
    		"查询 ----关键字及格式\r\n" + 
    		"日期至日期----关键字及格式\r\n" + 
    		"如下：\r\n" + 
    		"查询\r\n" + 
    		"2019-09-18\r\n" + 
    		"或\r\n" + 
    		"查询\r\n" + 
    		"2019-09-18至2019-09-20"; 
    public final static String dayReportFormatDownload = "亲，命令格式不对，报告下载操作的命令格式：\r\n" + 
    		"报告下载 ---关键字及格式\r\n" + 
    		"日期 ---关键字及格式\r\n" + 
    		"或是以下格式：\r\n" + 
    		"报告下载 ----关键字及格式\r\n" + 
    		"日期至日期----关键字及格式\r\n" + 
    		"如下：\r\n" + 
    		"报告下载\r\n" + 
    		"2019-09-18\r\n" + 
    		"或\r\n" + 
    		"报告下载\r\n" + 
    		"2019-09-18至2019-09-20"; 
    public final static String dayReportQueryDownload = "亲，命令格式不对，报告下载操作的命令格式：\r\n" + 
    		"调阅成员---关键字及格式\r\n" + 
    		"姓名---关键字及格式\r\n" + 
    		"日期 ---关键字及格式\r\n" + 
    		"或是以下格式：\r\n" + 
    		"调阅成员 ----关键字及格式\r\n" + 
    		"姓名---关键字及格式\r\n" + 
    		"日期至日期----关键字及格式\r\n" + 
    		"如下：\r\n" + 
    		"调阅成员\r\n" + 
    		"赵祖龙\r\n" +     		
    		"2019-09-18\r\n" + 
    		"或\r\n" + 
    		"调阅成员\r\n" + 
    		"赵祖龙\r\n" +      		
    		"2019-09-18至2019-09-20"; 
}
