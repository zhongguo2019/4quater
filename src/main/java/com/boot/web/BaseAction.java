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
import com.boot.util.qq.weixin.mp.aes.wxAppJSAPIUtil;

import com.krm.common.constant.Constant;

import net.sf.json.JSONObject;

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
@RequestMapping("/main")
public class BaseAction {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SysUserService sysUserService;

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DaoUtil daoUtil;
	@Autowired
	DocExpertUtil docExpertUtil;

	@RequestMapping(value = "/new")
	@ResponseBody
	String mainnew(HttpServletRequest request) {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		// String sql="SELECT id FROM t_happy_work_title WHERE
		// date(substr(begindate,0,11), 'unixepoch')=? AND date(substr(enddate,0,11),
		// 'unixepoch')=? ";
		String sql = "SELECT id FROM " + ConfigUtil.getValue("db.schema")
				+ "t_happy_work_title WHERE begindate=? AND enddate=? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				new Object[] { CurrentWeek.getCurrenproDay("yyyy-MM-dd"), CurrentWeek.getCurrenaftDay("yyyy-MM-dd") });
		String title_id = list.get(0).get("id").toString();
		String type = request.getParameter("type") != null ? request.getParameter("type").toString() : "";

		sql = "SELECT * FROM " + ConfigUtil.getValue("db.schema") + "t_happy_work_content WHERE title_id=? ";
		if (!type.equals("")) {
			if (type.equals("1")) {
				sql = sql + " AND importance='1' AND emergency='1' ";
			} else if (type.equals("2")) {
				sql = sql + " AND importance='1' AND emergency='2' ";
			} else if (type.equals("3")) {
				sql = sql + " AND importance='2' AND emergency='1' ";
			} else if (type.equals("4")) {
				sql = sql + " AND importance='2' AND emergency='2' ";
			}

		} else {
			sql = sql + " UNION  SELECT * FROM " + ConfigUtil.getValue("db.schema")
					+ "t_happy_work_content WHERE doning NOT in('5')";
		}
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql, new Object[] { title_id });
		String sortField = request.getParameter("sortField");
		String sortOrder = request.getParameter("sortOrder");
		if (StringUtils.isNotBlank(sortField) == true) {
			if ("desc".equals(sortOrder) == false)
				sortOrder = "asc";
			sql += " order by " + sortField + " " + sortOrder;
		} else {
			sql += "";
		}
		String DataSql = "select * from (" + sql + ") acc LIMIT ?,?";
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(DataSql,
				new Object[] { title_id, pageIndex * pageSize, pageSize });
		List datas = new ArrayList();
		Map result = new HashMap();
		for (int i = 0, l = maps.size(); i < l; i++) {
			List data = new ArrayList();
			Map record = (Map) maps.get(i);
			if (record == null)
				continue;
			datas.add(record);
		}
		result.put("data", datas);
		result.put("total", list1.size());
		String json = JsonHelper.encode(result);
		return json;
	}

	@RequestMapping(value = "/nosearch")
	@ResponseBody
	String nosearch(HttpServletRequest request) {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		String sql = "SELECT t.*,(SELECT a.work_title FROM " + ConfigUtil.getValue("db.schema")
				+ "t_happy_work_title a WHERE a.id=t.title_id) AS title_name FROM t_happy_work_content t WHERE doning NOT in('5')";
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql, new Object[] {});
		String sortField = request.getParameter("sortField");
		String sortOrder = request.getParameter("sortOrder");
		if (StringUtils.isNotBlank(sortField) == true) {
			if ("desc".equals(sortOrder) == false)
				sortOrder = "asc";
			sql += " order by " + sortField + " " + sortOrder;
		} else {
			sql += "";
		}
		String DataSql = "select * from (" + sql + ") acc LIMIT ?,?";
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(DataSql,
				new Object[] { pageIndex * pageSize, pageSize });
		List datas = new ArrayList();
		Map result = new HashMap();
		for (int i = 0, l = maps.size(); i < l; i++) {
			List data = new ArrayList();
			Map record = (Map) maps.get(i);
			if (record == null)
				continue;
			datas.add(record);
		}
		result.put("data", datas);
		result.put("total", list1.size());
		String json = JsonHelper.encode(result);

		return json;
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	String search(HttpServletRequest request) {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//        String sql="SELECT id FROM t_happy_work_title WHERE date(substr(begindate,0,11), 'unixepoch')=? AND date(substr(enddate,0,11), 'unixepoch')=? ";
		String sql = "SELECT id FROM " + ConfigUtil.getValue("db.schema")
				+ "t_happy_work_title WHERE begindate=? AND enddate=? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				new Object[] { CurrentWeek.getCurrenproDay("yyyy-MM-dd"), CurrentWeek.getCurrenaftDay("yyyy-MM-dd") });
		String title_id = request.getParameter("title") == null ? list.get(0).get("id").toString()
				: request.getParameter("title").toString();
		sql = "SELECT * FROM " + ConfigUtil.getValue("db.schema") + "t_happy_work_content WHERE title_id=? ";
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql, new Object[] { title_id });
		String sortField = request.getParameter("sortField");
		String sortOrder = request.getParameter("sortOrder");
		if (StringUtils.isNotBlank(sortField) == true) {
			if ("desc".equals(sortOrder) == false)
				sortOrder = "asc";
			sql += " order by " + sortField + " " + sortOrder;
		} else {
			sql += "";
		}
		String DataSql = "select * from (" + sql + ") acc LIMIT ?,?";
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(DataSql,
				new Object[] { title_id, pageIndex * pageSize, pageSize });
		List datas = new ArrayList();
		Map result = new HashMap();
		for (int i = 0, l = maps.size(); i < l; i++) {
			List data = new ArrayList();
			Map record = (Map) maps.get(i);
			if (record == null)
				continue;
			datas.add(record);
		}
		result.put("data", datas);
		result.put("total", list1.size());
		String json = JsonHelper.encode(result);

		return json;
	}

	@RequestMapping("/doing")
	String doing(Map<String, Object> map) {
//        String sql="SELECT id FROM t_happy_work_title WHERE date(substr(begindate,0,11), 'unixepoch')=? AND date(substr(enddate,0,11), 'unixepoch')=? ";
		String sql = "SELECT id FROM " + ConfigUtil.getValue("db.schema")
				+ "t_happy_work_title WHERE begindate=? AND enddate=? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				new Object[] { CurrentWeek.getCurrenproDay("yyyy-MM-dd"), CurrentWeek.getCurrenaftDay("yyyy-MM-dd") });

		map.put("id", list.get(0).get("id").toString());

		return "Doing";
	}

	@RequestMapping("/save")
	@ResponseBody
	String save(HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
		String data = request.getParameter("data") == null ? "" : request.getParameter("data");
		data = URLDecoder.decode(URLDecoder.decode(data, "utf-8"), "utf-8");
		if (!"".equals(data)) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) JsonHelper.decode(data);
			for (int i = list.size() - 1; i >= 0; i--) {
				Map<String, Object> row = list.get(i);
				String vid = row.get("id") != null ? row.get("id").toString() : "";
				String state = row.get("_state") != null ? row.get("_state").toString() : "";
				if (state.equals("added")) {
					String uuid = UUID.randomUUID().toString();
					row.put("id", uuid);
					daoUtil.insert(ConfigUtil.getValue("db.schema") + "t_happy_work_content", row);
				} else if (state.equals("removed") || state.equals("deleted")) {
					daoUtil.delete(ConfigUtil.getValue("db.schema") + "t_happy_work_content", row, "id='" + vid + "'");
				} else if (state.equals("modified") || state.equals("")) {
					daoUtil.update(ConfigUtil.getValue("db.schema") + "t_happy_work_content", row, "id='" + vid + "'");
				}
			}
		}

		return null;

	}

	/**
	 * <br>
	 * 该方法为获取Miniui下拉框信息，该方法还提供拼英检索功能； <br>
	 * <b>url地址为：</b>${root}/publicAction.do?method=getPingYinFild&fileName=${FileConfig.properties}
	 * <br>
	 * 调用该方法是需要引用</b>fileNam</b>e字段； <br>
	 * <b>fileName </b>字段内容来源于FileConfig.properties
	 */
	@RequestMapping("/getPingYinFild")
	@ResponseBody
	String getPingYinFild(HttpServletRequest request) {
		String result = "";
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		String type = "";
		// String type =request.getParameter("type")== null ? "" :
		// request.getParameter("type").toString();
		if (StringUtils.isNotBlank(request.getQueryString())) {
			String[] split = request.getQueryString().split("&");
			for (int i = 0; i < split.length; i++) {
				String s = split[i];
				if (s.contains("type=")) {
					type = (s.split("="))[1];
				}
			}
		}

		// type =request.getQueryString()== null ? "" :
		// (request.getQueryString().split("="))[1].toString();
		String sql = "SELECT vcode as id,vname as text FROM " + ConfigUtil.getValue("db.schema")
				+ "t_happy_dic WHERE type=?";
		if (StringUtils.isNotBlank(sql))
			try {
				maps = jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
					@Override
					public Map<String, Object> mapRow(ResultSet resultSet, int j) throws SQLException {
						Map m = new HashMap();
						ResultSetMetaData md = resultSet.getMetaData();
						int colCount = md.getColumnCount();
						for (int i = 1; i <= colCount; i++) {
							String colName = md.getColumnName(i).toLowerCase();
							if (colName.equals("text")) {
								// colName = "isLeaf";
								m.put("py", ChineseToEnglish
										.getPinYinHeadChar(String.valueOf(resultSet.getObject(colName))));
							}
							m.put(colName, resultSet.getObject(colName));
						}
						return m;

					}
				}, new Object[] { type });
				result = JsonHelper.encode(maps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	@RequestMapping("/getTitle")
	@ResponseBody
	String getTitle(HttpServletRequest request) {
		String result = "";
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		String sql = "SELECT id,work_title AS text FROM " + ConfigUtil.getValue("db.schema") + "t_happy_work_title";
		if (StringUtils.isNotBlank(sql))
			try {
				maps = jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
					@Override
					public Map<String, Object> mapRow(ResultSet resultSet, int j) throws SQLException {
						Map m = new HashMap();
						ResultSetMetaData md = resultSet.getMetaData();
						int colCount = md.getColumnCount();
						for (int i = 1; i <= colCount; i++) {
							String colName = md.getColumnName(i).toLowerCase();
							if (colName.equals("text")) {
								// colName = "isLeaf";
								m.put("py", ChineseToEnglish
										.getPinYinHeadChar(String.valueOf(resultSet.getObject(colName))));
							}
							m.put(colName, resultSet.getObject(colName));
						}
						return m;

					}
				}, new Object[] {});
				result = JsonHelper.encode(maps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	@RequestMapping("/export")
	@ResponseBody
	String export(HttpServletRequest request) {
		String titleid = request.getParameter("titleid") == "" ? this.getWeektitle(request)
				: request.getParameter("titleid").toString();
		String fname = "" + CurrentWeek.getMonth() + "月第" + CurrentWeek.getWeekOfMonth() + "周周报["
				+ CurrentWeek.getCurrenproDay("") + "-" + CurrentWeek.getCurrenaftDay("");
		try {
			docExpertUtil.Export(titleid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fname;
	}

	@RequestMapping("/systemmaintenance")
	@ResponseBody
	String systemmaintenance(HttpServletRequest request) {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String sql = "SELECT id, systemname, is_del FROM " + ConfigUtil.getValue("db.schema")
				+ "t_systemmaintenance WHERE is_del=0";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] {});
		String sortField = request.getParameter("sortField");
		String sortOrder = request.getParameter("sortOrder");
		if (StringUtils.isNotBlank(sortField) == true) {
			if ("desc".equals(sortOrder) == false)
				sortOrder = "asc";
			sql += " order by " + sortField + " " + sortOrder;
		} else {
			sql += "";
		}
		String DataSql = "select * from (" + sql + ") acc LIMIT ?,?";
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(DataSql,
				new Object[] { pageIndex * pageSize, pageSize });
		List datas = new ArrayList();
		Map result = new HashMap();
		for (int i = 0, l = maps.size(); i < l; i++) {
			List data = new ArrayList();
			Map record = (Map) maps.get(i);
			if (record == null)
				continue;
			datas.add(record);
		}
		result.put("data", datas);
		result.put("total", list.size());
		String json = JsonHelper.encode(result);
		return json;
	}

	@RequestMapping("/systemmaintenancesave")
	@ResponseBody
	String systemmaintenancesave(HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
		String data = request.getParameter("data") == null ? "" : request.getParameter("data");
		data = URLDecoder.decode(URLDecoder.decode(data, "utf-8"), "utf-8");
		if (!"".equals(data)) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) JsonHelper.decode(data);
			for (int i = list.size() - 1; i >= 0; i--) {
				Map<String, Object> row = list.get(i);
				String vid = row.get("id") != null ? row.get("id").toString() : "";
				String state = row.get("_state") != null ? row.get("_state").toString() : "";
				if (state.equals("added")) {
					String uuid = UUID.randomUUID().toString();
					row.put("id", uuid);
					daoUtil.insert(ConfigUtil.getValue("db.schema") + "t_systemmaintenance", row);
				} else if (state.equals("removed") || state.equals("deleted")) {
					daoUtil.delete(ConfigUtil.getValue("db.schema") + "t_systemmaintenance", row, "id='" + vid + "'");
				} else if (state.equals("modified") || state.equals("")) {
					daoUtil.update(ConfigUtil.getValue("db.schema") + "t_systemmaintenance", row, "id='" + vid + "'");
				}
			}
		}

		return null;

	}

	@RequestMapping("/getsystem")
	@ResponseBody
	String getsystem(HttpServletRequest request) {
		String result = "";
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		String sql = "SELECT id,systemname AS text FROM " + ConfigUtil.getValue("db.schema")
				+ "t_systemmaintenance WHERE is_del=0";
		if (StringUtils.isNotBlank(sql))
			try {
				maps = jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
					@Override
					public Map<String, Object> mapRow(ResultSet resultSet, int j) throws SQLException {
						Map m = new HashMap();
						ResultSetMetaData md = resultSet.getMetaData();
						int colCount = md.getColumnCount();
						for (int i = 1; i <= colCount; i++) {
							String colName = md.getColumnName(i).toLowerCase();
							if (colName.equals("text")) {
								// colName = "isLeaf";
								m.put("py", ChineseToEnglish
										.getPinYinHeadChar(String.valueOf(resultSet.getObject(colName))));
							}
							m.put(colName, resultSet.getObject(colName));
						}
						return m;

					}
				}, new Object[] {});
				result = JsonHelper.encode(maps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	@RequestMapping("/firstpage")
	String login(HttpServletRequest request) {
		logger.info(" 登录验证成功，进入到系统管理的首页面");
		return "main/outlookmenu";
	}

	@RequestMapping("/loginlayout")
	String loginlayout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SysUser sysuser = null;
		if (null != session.getAttribute(Constant.SESSION_LOGIN_USER)) {
			sysuser = (SysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
		}
		if (null == sysuser) {
			return "login/loginLayout";
		} else {
			return "main/outlookmenu";
		}
	}

	@RequestMapping(value = "/titleid")
	@ResponseBody
	String getWeektitle(HttpServletRequest request) {

		String title_id = CurrentWeek.getDescWeekName();
		logger.info(title_id);
		return title_id;
	}

	@RequestMapping("/productmodule")
	@ResponseBody
	String getModuleList(HttpServletRequest request) throws UnsupportedEncodingException {

		SysUser sysuser = (SysUser) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
		List<CommonEntity> lst = new ArrayList();
		String rtn = "";
		if (null == sysuser) {

		} else {
			Map<String, Object> userMap = new HashMap();
			userMap.put("username", sysuser.getUsername().toString());

			lst = sysUserService.getUserModule(userMap);
			rtn = JsonHelper.encode(lst);
		}

		return rtn;

	}

	@RequestMapping("/wxconnect")
	@ResponseBody
	String getWXConnect(HttpServletRequest request) throws Exception {
		WeiXinUtil weiXinUtil = new WeiXinUtil();
		
	//	logger.info("------------------------------企业微信发来调用消息,处理开始------------------------------！");
		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WeiXinParamesUtil.token, WeiXinParamesUtil.encodingAESKey,
				WeiXinParamesUtil.corpId);
		
		
		Map<String, Object> paramter = HttpUtil.getParameterMap(request);
		String strMsgSig = HttpUtil.getDefaultKeyString(paramter, "msg_signature");
		String strTimeStamp = HttpUtil.getDefaultKeyString(paramter, "timestamp");
		String strReqNonce = HttpUtil.getDefaultKeyString(paramter, "nonce");
		String strEchostr = HttpUtil.getDefaultKeyString(paramter, "echostr");

		String strRtnEchoStr = ""; // 需要返回的明文
		request.getSession().setAttribute(Constant.SESSION_LOGIN_WXFLAG, "Y");
	
		//后台通过微信登录，得到sys_user表中的用户信息，放到session中。
		if (null != paramter.get("echostr")) {
	//		logger.info("------------------------------验证连接服务器地址,处理开始------------------------------！");
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
		//	logger.info("------------------------------验证连接服务器地址,处理结束------------------------------！");
			return strRtnEchoStr;
		}

		String strReqData = HttpUtil.getPostStringData(request);
		if ("".equals(strReqData) || null != strReqData) {
//			logger.info("------------------------------企业微信发来调用消息, 开处理-------------------");
			strRtnEchoStr = weiXinUtil.msgDeal(wxcpt, strMsgSig, strTimeStamp, strReqNonce, strReqData, request);
		}
		/* logger.info("返回消息内容："+strRtnEchoStr); */
		return strRtnEchoStr;
		

	}

	@RequestMapping("/wxfirstpage")
	String WXLogin(HttpServletRequest request) throws Exception {
	
		return "main/outlookmenu";
	}

	@RequestMapping("/fourQuadrant")
	String fourQuadrant(HttpServletRequest request) throws UnsupportedEncodingException {
		return "main/fourQuadrant";
	}

	@RequestMapping("/wxgetJSSConfig")
	@ResponseBody
	String wxAppJSAPIgetAppid(HttpServletRequest request) throws UnsupportedEncodingException {
        JSONObject wxConfig = new JSONObject();
		 wxConfig = WeiXinUtil.getWxConfigJSON(request);
		 return  wxConfig.toString();
	}
	
	@RequestMapping("/wxgetJSSUser")
	@ResponseBody
	String wxgetJSSUser(HttpServletRequest request) throws UnsupportedEncodingException {
		
		String data = request.getParameter("data") == null ? "" : request.getParameter("data");
	    String usercode = null;
		String strRtn=null;
		WeiXinUtil weiXinUtil = new WeiXinUtil();
		if (!"".equals(data)) {

			data= URLDecoder.decode(URLDecoder.decode(data, "utf-8"), "utf-8");
			logger.info("前台传入的data【"+data+"】");		
			//List<Map<String, Object>> list = (List<Map<String, Object>>) JsonHelper.decode(data);
			
			strRtn = 	weiXinUtil.getTencentUserInfo(data);
		}else {
			logger.info("前台传入的code为空！");
		}
		return strRtn;
		
	}

	@RequestMapping("/wxCommitForm")
	@ResponseBody
	String wxCommitForm(HttpServletRequest request) throws UnsupportedEncodingException {
		String data = request.getParameter("data") == null ? "" : request.getParameter("data");
		data = URLDecoder.decode(URLDecoder.decode(data, "utf-8"), "utf-8");

		if (!"".equals(data)) {
		
		
			}
	

		return null;
	}
}
