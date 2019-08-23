package com.boot.web.todaywork.controller;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.krm.common.constant.Constant;
import com.boot.util.BaseController;

import com.boot.util.CommonEntity;
import com.boot.util.Result;
import com.boot.util.DateUtils;
import com.boot.util.JsonHelper;
import com.boot.util.StringConvert;
import com.boot.util.StringUtil;
import com.boot.util.excel.ExportExcel;
import com.boot.util.excel.ImportExcel;
import com.boot.web.sys.model.SysUser;
import com.boot.web.todaywork.model.DoufuTodayWork;
import com.boot.web.todaywork.service.DoufuTodayWorkService;
import com.boot.util.SysUserUtils;

/**
 * 
 * @author 赵祖龙 当天工作记录信息表控制层 2019-08-03
 */
@Controller
@RequestMapping("/todaywork/doufuTodayWork")
public class DoufuTodayWorkController extends BaseController {

	public static final String BASE_URL = "todaywork/doufuTodayWork";
	private static final String BASE_PATH = "todaywork/doufuTodayWork/";

	@Resource
	private DoufuTodayWorkService doufuTodayWorkService;

	@Override
	protected String getBaseUrl() {
		return BASE_URL;
	}

	@Override
	protected String getBasePath() {
		return BASE_PATH;
	}

	@Override
	protected String getBasePermission() {
		return "todaywork:doufuTodayWork";
	}

	/**
	 * 跳转到模块页面
	 * 
	 * @param model
	 * @return 模块html
	 */
	@RequestMapping
	public String toDoufuTodayWork(Model model) {
		logger.info("跳转到当天工作记录信息表页面(" + getBasePath() + "doufuTodayWork-list)");
		checkPermission("query");
		return getBasePath() + "doufuTodayWork-list";
	}

	/**
	 * 分页显示
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<CommonEntity> list(@RequestParam Map<String, Object> params, Model model) {
		logger.info("分页显示当天工作记录信息表，参数：" + params.toString());
		// checkPermission("query");
		// 权限语句
		params.put("dynamicSQL", SysUserUtils.dataScopeFilterString1("o", "u", getBaseUrl(), "id"));
		if (params.containsKey("sortC")) {
			// 如果传过来的参数是驼峰式，这里需要将驼峰转成下划线式
			params.put("sortC", StringConvert.camelhumpToUnderline(params.get("sortC").toString()));
		}
		PageInfo<CommonEntity> page = doufuTodayWorkService.queryPageInfo(params);
		return page;
	}

	/**
	 * 添加或更新
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@ModelAttribute DoufuTodayWork entry, MultipartHttpServletRequest request) {
		logger.info("开始保存当天工作记录信息表");
		int count = 0;
		if (StringUtil.isEmpty(entry.getId())) {
			checkPermission("add");
			entry.setId(doufuTodayWorkService.generateId());
			count = doufuTodayWorkService.save(entry);
		} else {
			checkPermission("update");
			count = doufuTodayWorkService.update(entry);
		}
		if (count > 0) {
			logger.info("保存当天工作记录信息表成功！");
			return Result.successResult();
		}
		return Result.errorResult();
	}

	/**
	 * 删除
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Result del(String id, @RequestParam Map<String, Object> params) {
		logger.info("开始删除当天工作记录信息表，参数：" + id);
		checkPermission("delete");
		int count = doufuTodayWorkService.deleteDoufuTodayWork(id);
		if (count > 0) {
			logger.info("删除当天工作记录信息表成功！");
			return Result.successResult();
		}
		logger.info("删除当天工作记录信息表失败！");
		return Result.errorResult();
	}

	/**
	 * 弹窗显示
	 * 
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 * @return
	 */
	@RequestMapping(value = "{mode}/showlayer", method = RequestMethod.POST)
	public String layer(String id, @RequestParam Map<String, Object> params, @PathVariable String mode, Model model) {
		DoufuTodayWork entry = null;
		if (StringUtils.equals("add", mode)) {
			logger.info("弹窗显示【当天工作记录信息表】添加页面(" + getBasePath() + "doufuTodayWork-add)");
			checkPermission("add");
			return getBasePath() + "doufuTodayWork-add";
		} else if (StringUtils.equals("edit", mode)) {
			logger.info("弹窗显示【当天工作记录信息表】编辑页面(" + getBasePath() + "doufuTodayWork-update)");
			checkPermission("update");
			params.put("id", id);
			entry = doufuTodayWorkService.queryOne(params);
			model.addAttribute("entry", entry);
			return getBasePath() + "doufuTodayWork-update";
		} else if (StringUtils.equals("detail", mode)) {
			logger.info("弹窗显示【当天工作记录信息表】详情页面(" + getBasePath() + "doufuTodayWork-detail)");
			checkPermission("query");
			params.put("id", id);
			CommonEntity entity = doufuTodayWorkService.queryOneCommon(params);
			model.addAttribute("entry", entity);
		} else if (StringUtils.equals("import", mode)) {
			logger.info("弹窗显示【当天工作记录信息表】Excel导入页面(" + getBasePath() + "doufuTodayWork-import)");
			checkPermission("import");
			return getBasePath() + "doufuTodayWork-import";
		}
		return getBasePath() + "doufuTodayWork-detail";
	}

	/**
	 * 当天工作记录信息表Excel导入模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("import/template/download")
	public void importDoufuTodayWorkTemplate(HttpServletResponse response) throws Exception {
		logger.info("开始下载当天工作记录信息表Excel导入模板");
		checkPermission("import");
		String fileName = "当天工作记录信息表Excel导入模板.xlsx";
		List<DoufuTodayWork> list = Lists.newArrayList();
		list.add(new DoufuTodayWork());
		new ExportExcel("当天工作记录信息表", DoufuTodayWork.class, 2).setDataList(list).write(response, fileName).dispose();
	}

	/**
	 * 当天工作记录信息表数据导入
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	@ResponseBody
	public Result importFile(@RequestParam("file") MultipartFile fileList[], HttpServletResponse response)
			throws Exception {
		logger.info("开始导入当天工作记录信息表数据");
		checkPermission("import");
		Long start = System.currentTimeMillis();
		int successNum = 0;
		int failureNum = 0;
		for (MultipartFile file : fileList) {
			ImportExcel ei;
			StringBuilder failureMsg = new StringBuilder();
			ei = new ImportExcel(file, 1, 0);
			List<DoufuTodayWork> list = ei.getDataList(DoufuTodayWork.class);
			for (DoufuTodayWork entry : list) {
				entry.setId(doufuTodayWorkService.generateId());
				entry.setCreateBy(SysUserUtils.getCacheLoginUser().getId());
				entry.setCreateDate(new Date());
				entry.setDelFlag(Constant.DEL_FLAG_NORMAL);
			}
			successNum = doufuTodayWorkService.insertBatch(list);
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败导入 " + failureNum + " 条当天工作记录信息表数据，导入信息如下：");
			}
			Long end = System.currentTimeMillis();
			DecimalFormat df = new DecimalFormat("######0.00");
			logger.info("导入用时" + df.format((double) (end - start) / (double) 1000) + "秒");
		}
		return new Result(1, "操作成功！，成功导入" + successNum + "条，失败导入" + failureNum + "条");
	}

	/**
	 * 当天工作记录信息表导出excel
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
		logger.info("开始导出当天工作记录信息表数据");
		checkPermission("export");
		String fileName = "当天工作记录信息表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		// 权限语句
		params.put("dynamicSQL", SysUserUtils.dataScopeFilterString1("o", "u", getBaseUrl(), "id"));
		try {
			for (String key : params.keySet()) { // 处理中文乱码
				String paramsTrans = new String(((String) params.get(key)).getBytes("ISO-8859-1"), "UTF-8");
				paramsTrans = java.net.URLDecoder.decode(paramsTrans, "UTF-8");
				params.put(key, paramsTrans.trim());
			}
		} catch (Exception e) {
		}
		List<DoufuTodayWork> list = doufuTodayWorkService.entityList(params);
		new ExportExcel("当天工作记录信息表", DoufuTodayWork.class).setDataList(list).write(response, fileName).dispose();
	}

	/**
	 * 当天工作记录信息表数据保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "savebatch", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Result saveBatch(HttpServletRequest request) throws Exception {
		String data = request.getParameter("data") == null ? "" : request.getParameter("data");
		if (data == "[]") {
			return new Result(0, "操作失败，前台没有数据更改需要保存！");
		}
		data = URLDecoder.decode(URLDecoder.decode(data, "utf-8"), "utf-8");

		StringBuilder failureMsg = new StringBuilder();
		logger.info("开始保存当天工作记录信息表数据");
		// checkPermission("savebatch");
		Long start = System.currentTimeMillis();
		int successNumInsert = 0;
		int failureNumInsert = 0;
		int successNumUpdate = 0;
		int failureNumUpdate = 0;
		SysUser sysuser = (SysUser) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
		if (!"".equals(data)) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) JsonHelper.decode(data);
			List<DoufuTodayWork> listEntyInsert = new ArrayList<DoufuTodayWork>();
			List<DoufuTodayWork> listEntyUpdate = new ArrayList<DoufuTodayWork>();
			for (int i = list.size() - 1; i >= 0; i--) {
				Map<String, Object> row = list.get(i);
				String vid = row.get("id") != null ? row.get("id").toString() : "";
				String state = row.get("_state") != null ? row.get("_state").toString() : "";
				DoufuTodayWork doufuTodayWork = new DoufuTodayWork(row);
				if (vid == "" || vid == null) {
					// 前台记录没有主键值 ，为新增add
					String uuid = UUID.randomUUID().toString();
					doufuTodayWork.setId(uuid);
					doufuTodayWork.setProjectGroupId(sysuser.getProjectGroupId());
					doufuTodayWork.setProjectId(sysuser.getProjectGroupId());
					doufuTodayWork.setCreateDate(new Date());
					doufuTodayWork.setUpdateDate(new Date());
					doufuTodayWork.setDelFlag(Constant.DEL_FLAG_NORMAL);
					doufuTodayWork.setStatus(Constant.DEL_FLAG_NORMAL);
					doufuTodayWork.setInstId(sysuser.getOrganId());
					doufuTodayWork.setLoginIp(sysuser.getLoginIp());
					doufuTodayWork.setLoginDate(new Date());
					doufuTodayWork.setCreateBy(sysuser.getId());
					doufuTodayWork.setUpdateBy(sysuser.getId());
					doufuTodayWork.setRemarks("");
					doufuTodayWork.setImpoLevel("0");
					doufuTodayWork.setLoginDate(new Date());
					listEntyInsert.add(doufuTodayWork);

				} else {
					// 前台记录存在主键值 ，为更新update

					doufuTodayWork.setUpdateDate(new Date());
					doufuTodayWork.setUpdateBy(sysuser.getId());
					doufuTodayWork.setLoginIp(sysuser.getLoginIp());

					listEntyUpdate.add(doufuTodayWork);
				}

			}
			if (listEntyInsert != null && listEntyInsert.size() > 0) {
				successNumInsert = doufuTodayWorkService.insertBatch(listEntyInsert);
			}
			if (listEntyUpdate != null && listEntyUpdate.size() > 0) {
				successNumUpdate = doufuTodayWorkService.updateBatch(listEntyUpdate);
			}

			if (failureNumInsert > 0) {
				failureMsg.insert(0, "，失败保存,增加数据时 " + failureNumInsert + " 条当天工作记录信息表数据，导入信息如下：");
			}
			if (failureNumUpdate > 0) {
				failureMsg.insert(0, "，失败保存,更新数据时 " + failureNumUpdate + " 条当天工作记录信息表数据，导入信息如下：");
			}
			Long end = System.currentTimeMillis();
			DecimalFormat df = new DecimalFormat("######0.00");
			logger.info("批量插入，用时" + df.format((double) (end - start) / (double) 1000) + "秒");
		}

		int failureNum = 0;
		int successNum = 0;
		failureNum = failureNumInsert + failureNumUpdate;
		successNum = successNumInsert + successNumUpdate;
		return new Result(1, "操作成功！，成功保存" + successNum + "条，失败保存" + failureNum + "条");

	}

	@RequestMapping(value = "queryPageList")
	@ResponseBody
	PageInfo<DoufuTodayWork> queryPageList(HttpServletRequest request) {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String sortField = request.getParameter("sortField").toString();
		String sortOrder = request.getParameter("sortOrder").toString();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageNum", pageIndex);
		params.put("pageSize", pageSize);
		params.put("sortC", StringConvert.camelhumpToUnderline(sortField));
		params.put("order", sortOrder);
		logger.info("分页显示当天工作记录信息表，参数：" + params.toString());
		// checkPermission("query");
		// 权限语句
		// params.put("dynamicSQL", SysUserUtils.dataScopeFilterString1("o", "u",
		// getBaseUrl(), "id"));

		/*
		 * if (params.containsKey("sortC")) { // 如果传过来的参数是驼峰式，这里需要将驼峰转成下划线式
		 * params.put("sortC",
		 * StringConvert.camelhumpToUnderline(params.get("sortC").toString())); }
		 */

		PageInfo<DoufuTodayWork> page = doufuTodayWorkService.queryPageInfoEntity(params);

		return page;
	}

	@RequestMapping(value = "queryList")
	@ResponseBody
	List<DoufuTodayWork> queryList(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sortField = request.getParameter("sortField").toString();
		String sortOrder = request.getParameter("sortOrder").toString();
		params.put("sortC", StringConvert.camelhumpToUnderline(sortField));
		params.put("order", sortOrder);


		List<DoufuTodayWork> lstRtn = doufuTodayWorkService.entityList(params);

		return lstRtn;
	}

	/**
	 * 批量删除
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deletes", method = RequestMethod.POST)
	@ResponseBody
	public Result dels(@RequestParam(value = "ids[]") String[] ids) {

		if (ids.length==0) {
			return new Result(0, "操作失败，没有要删除的行被选中！");
		}


		logger.info("开始批量删除当天工作记录信息表，参数：" + ids);
		// checkPermission("delete");
		int count = doufuTodayWorkService.deleteDoufuTodayWork(ids);
		if (count > 0) {
			logger.info("删除当天工作记录信息表成功！");
			return Result.successResult();
		}
		logger.info("删除当天工作记录信息表失败！");
		return Result.errorResult();
	}

}