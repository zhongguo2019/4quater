package com.boot.web;

import com.boot.web.sys.model.SysUser;
import com.boot.web.sys.service.SysUserService;
import com.boot.web.todaywork.service.DoufuTodayWorkService;
import com.github.pagehelper.PageInfo;
import com.boot.util.ChineseToEnglish;
import com.boot.util.CommonEntity;
import com.boot.util.ConfigUtil;
import com.boot.util.CurrentWeek;
import com.boot.util.DaoUtil;
import com.boot.util.DocExpertUtil;
import com.boot.util.HttpUtil;
import com.boot.util.JsonHelper;
import com.boot.util.qq.weixin.mp.aes.AesException;
import com.boot.util.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.boot.util.qq.weixin.mp.aes.WeiXinParamesUtil;
import com.boot.util.qq.weixin.mp.aes.WeiXinUtil;
import com.krm.common.constant.Constant;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/*
 * 主界面的所有控制层
 * 
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/wxmain")
public class WXBaseAction {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SysUserService sysUserService;

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DaoUtil daoUtil;
	@Autowired
	DocExpertUtil docExpertUtil;

	

	@RequestMapping("/wxconnect")
	@ResponseBody
	String getWXConnect(HttpServletRequest request) throws Exception {

		logger.info("------------------------------企业微信发来调用消息,处理开始------------------------------！");
		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WeiXinParamesUtil.token, WeiXinParamesUtil.encodingAESKey,
				WeiXinParamesUtil.corpId);
		Map<String, Object> paramter = HttpUtil.getParameterMap(request);
		String strMsgSig = HttpUtil.getDefaultKeyString(paramter, "msg_signature");
		String strTimeStamp = HttpUtil.getDefaultKeyString(paramter, "timestamp");
		String strReqNonce = HttpUtil.getDefaultKeyString(paramter, "nonce");
		String strEchostr = HttpUtil.getDefaultKeyString(paramter, "echostr");
		logger.info("服务器验证时传过来的加密字符[" + strEchostr + "]");
		String strRtnEchoStr = ""; // 需要返回的明文

		if (null != paramter.get("echostr")) {
			logger.info("------------------------------验证连接服务器地址,处理开始------------------------------！");
			try {
				strRtnEchoStr = wxcpt.VerifyURL(strMsgSig, strTimeStamp, strReqNonce, strEchostr);
				// System.out.println("服务器验证成功，返回的明文: " + strRtnEchoStr);
				// 验证URL成功，将sEchoStr返回
				// HttpUtils.SetResponse(sEchoStr);
			} catch (Exception e) {
				// 验证URL失败，错误原因请查看异常
				e.printStackTrace();
				strRtnEchoStr = "------------------------------签名验证错误！------------------------------";
			}
			logger.info("------------------------------验证连接服务器地址,处理结束------------------------------！");
			return strRtnEchoStr;
		}

		
		
		
		String strReqData = HttpUtil.getPostStringData(request);
		if ("".equals(strReqData) || null != strReqData) {
			logger.info("------------------------------企业微信发来调用消息, 开处理-------------------");
			WeiXinUtil weiXinUtil = new WeiXinUtil();
			strRtnEchoStr = weiXinUtil.msgDeal(wxcpt, strMsgSig, strTimeStamp, strReqNonce, strReqData, request);
			logger.info("------------------------------企业微信发来调用消息, 处理结束 -----------------\n 返回值 \n" + strRtnEchoStr + "");
		}
		return strRtnEchoStr;
		
		
		
	}

	@RequestMapping("/WXLogin")
	String WXLogin(HttpServletRequest request) throws Exception {
		return "main/outlookmenu";
	}
	
	@RequestMapping("/fourQuadrant")
	String fourQuadrant(HttpServletRequest request) throws UnsupportedEncodingException {
		// logger.info("企业微信发来调用消息！");
		return "main/fourQuadrant";
	}

	@RequestMapping
	public void init(HttpServletRequest request) {
		System.out.println(111 + "-----------------------------");
	}

}
