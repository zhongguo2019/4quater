package com.boot.web.tomorrowplan.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
import com.boot.util.BaseController;
import com.boot.util.CommonEntity;
import com.boot.util.Result;
import com.boot.util.DateUtils;
import com.boot.util.StringConvert;
import com.boot.util.StringUtil;
import com.boot.util.excel.ExportExcel;
import com.boot.util.excel.ImportExcel;
import com.boot.web.tomorrowplan.model.DoufuTomorrowPlan;
import com.boot.web.tomorrowplan.service.DoufuTomorrowPlanService;
import com.boot.util.SysUserUtils;
import com.krm.common.constant.Constant;
import com.boot.web.sys.model.SysUser;
import java.net.URLDecoder;
import com.boot.util.JsonHelper;
import java.util.ArrayList;
import java.util.UUID;
import com.boot.util.HttpUtil;

/**
 * 
 * @author 赵祖龙
 * 明天工作计划表控制层
 * 2019-08-27
 */
@Controller
@RequestMapping("tomorrowplan/doufuTomorrowPlan")
public class DoufuTomorrowPlanController extends BaseController {
	
	public static final String BASE_URL = "tomorrowplan/doufuTomorrowPlan";
	private static final String BASE_PATH = "tomorrowplan/doufuTomorrowPlan/";
	
	@Resource
	private DoufuTomorrowPlanService doufuTomorrowPlanService;
	
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
		return "tomorrowplan:doufuTomorrowPlan";
	}
	
	/**
	 * 跳转到模块页面
	 * @param model
	 * @return 模块html
	 */
	@RequestMapping
	public String toDoufuTomorrowPlan(Model model){
		logger.info("跳转到明天工作计划表页面(" + getBasePath() + "doufuTomorrowPlan-list)");
		//checkPermission("query");
		return getBasePath() + "doufuTomorrowPlan-list";
	}
	
	/**
	 * 分页显示
	 * @param params
	 * @return
	 */
	@RequestMapping(value="list", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<CommonEntity> list(@RequestParam Map<String, Object> params, Model model){
		logger.info("分页显示明天工作计划表，参数：" + params.toString());
        //checkPermission("query");
        //权限语句
		params.put("dynamicSQL", SysUserUtils.dataScopeFilterString1("o", "u", getBaseUrl(), "id"));
		if (params.containsKey("sortC")){
			//如果传过来的参数是驼峰式，这里需要将驼峰转成下划线式
			params.put("sortC", StringConvert.camelhumpToUnderline(params.get("sortC").toString()));
		}
		PageInfo<CommonEntity> page = doufuTomorrowPlanService.queryPageInfo(params);
		return page;
	}
	
	/**
	 * 添加或更新
	 * @param params
	 * @return
	 */
	@RequestMapping(value="save", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@ModelAttribute DoufuTomorrowPlan entry, MultipartHttpServletRequest request){
		logger.info("开始保存明天工作计划表");
		int count = 0;
		if(StringUtil.isEmpty(entry.getId())){
			checkPermission("add");
       		entry.setId(doufuTomorrowPlanService.generateId());
       		count = doufuTomorrowPlanService.save(entry);
		}else{
			checkPermission("update");
       		count = doufuTomorrowPlanService.update(entry);
		}
		if(count > 0){
			logger.info("保存明天工作计划表成功！");
			return Result.successResult();
		}
		return Result.errorResult();
	}
	
	/**
	 * 删除
	 * @param 
	 * @return
	 */
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public Result del(String id, @RequestParam Map<String, Object> params){
		logger.info("开始删除明天工作计划表，参数：" + id);
		checkPermission("delete");
		int count = doufuTomorrowPlanService.deleteDoufuTomorrowPlan(id);
		if(count > 0){
			logger.info("删除明天工作计划表成功！");
			return Result.successResult();
		}
		logger.info("删除明天工作计划表失败！");
		return Result.errorResult();
	}
	
	/**
	 * 批量删除
	 * @param
	 * @return
	 */
	@RequestMapping(value="deletes", method = RequestMethod.POST)
	@ResponseBody
	public Result dels(@RequestParam(value = "ids[]") String[] ids){
		logger.info("开始批量删除明天工作计划表，参数：" + ids);
		//checkPermission("delete");
		if (ids.length==0) {
			return new Result(0, "操作失败，没有要删除的行被选中！");
		}

		int count = doufuTomorrowPlanService.deleteDoufuTomorrowPlan(ids);
		if(count > 0){
			logger.info("删除明天工作计划表成功！");
			return Result.successResult();
		}
		logger.info("删除明天工作计划表失败！");
		return Result.errorResult();
	}
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 * @return
	 */
	@RequestMapping(value="{mode}/showlayer", method=RequestMethod.POST)
	public String layer(String id, @RequestParam Map<String, Object> params, @PathVariable String mode, Model model){
		DoufuTomorrowPlan entry = null;
		if(StringUtils.equals("add", mode)){
			logger.info("弹窗显示【明天工作计划表】添加页面(" + getBasePath() + "doufuTomorrowPlan-add)");
			checkPermission("add");
			return getBasePath() + "doufuTomorrowPlan-add";
		}else if(StringUtils.equals("edit", mode)){
			logger.info("弹窗显示【明天工作计划表】编辑页面(" + getBasePath() + "doufuTomorrowPlan-update)");
			checkPermission("update");
			params.put("id", id);
			entry = doufuTomorrowPlanService.queryOne(params);
			model.addAttribute("entry", entry);
			return getBasePath() + "doufuTomorrowPlan-update";
		}else if(StringUtils.equals("detail", mode)){
			logger.info("弹窗显示【明天工作计划表】详情页面(" + getBasePath() + "doufuTomorrowPlan-detail)");
			checkPermission("query");
			params.put("id", id);
			CommonEntity entity = doufuTomorrowPlanService.queryOneCommon(params);
			model.addAttribute("entry", entity);
		}else if(StringUtils.equals("import", mode)){
			logger.info("弹窗显示【明天工作计划表】Excel导入页面(" + getBasePath() + "doufuTomorrowPlan-import)");
			checkPermission("import");
			return getBasePath() + "doufuTomorrowPlan-import";
		}
		return getBasePath() + "doufuTomorrowPlan-detail";
	}
	
	/**
     * 明天工作计划表Excel导入模板
     * @param response
     * @param redirectAttributes
     * @return
     * @throws Exception 
     */
    @RequestMapping("import/template/download")
    public void importDoufuTomorrowPlanTemplate(HttpServletResponse response) throws Exception {
    	logger.info("开始下载明天工作计划表Excel导入模板");
    	checkPermission("import");
		String fileName = "明天工作计划表Excel导入模板.xlsx";
		List<DoufuTomorrowPlan> list = Lists.newArrayList();
		list.add(new DoufuTomorrowPlan());
		new ExportExcel("明天工作计划表", DoufuTomorrowPlan.class, 2).setDataList(list).write(response, fileName).dispose();
    }
    
    /**
     * 明天工作计划表数据导入
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    @ResponseBody
    public Result importFile(@RequestParam("file") MultipartFile fileList[], HttpServletResponse response) throws Exception {
    	logger.info("开始导入明天工作计划表数据");
    	checkPermission("import");
    	Long start = System.currentTimeMillis();
    	int successNum = 0;
    	int failureNum = 0;
    	for (MultipartFile file : fileList) {
    		ImportExcel ei;
    		StringBuilder failureMsg = new StringBuilder();
   			ei = new ImportExcel(file, 1, 0);
   			List<DoufuTomorrowPlan> list = ei.getDataList(DoufuTomorrowPlan.class);
   			for (DoufuTomorrowPlan entry : list) {
   				entry.setId(doufuTomorrowPlanService.generateId());
   				entry.setCreateBy(SysUserUtils.getCacheLoginUser().getId());
   				entry.setCreateDate(new Date());
   				entry.setDelFlag(Constant.DEL_FLAG_NORMAL);
			}
   			successNum = doufuTomorrowPlanService.insertBatch(list);
    		if (failureNum > 0){
    			failureMsg.insert(0, "，失败导入 " + failureNum + " 条明天工作计划表数据，导入信息如下：");
    		}
    		Long end = System.currentTimeMillis();
    		DecimalFormat df = new DecimalFormat("######0.00");
    		logger.info("导入用时"+df.format((double)(end-start)/(double)1000)+"秒");
		}
    	return new Result(1, "操作成功！，成功导入"+successNum+"条，失败导入"+failureNum+"条");
    }
    
    /**
	 * 明天工作计划表导出excel
	 */
	@RequestMapping(value="export", method = RequestMethod.POST)
	public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception{
		logger.info("开始导出明天工作计划表数据");
		checkPermission("export");
		String fileName = "明天工作计划表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		//权限语句
		params.put("dynamicSQL", SysUserUtils.dataScopeFilterString1("o", "u", getBaseUrl(), "id"));
		try {
			for (String key : params.keySet()){ // 处理中文乱码
				String paramsTrans = new String(((String) params.get(key)).getBytes("ISO-8859-1"), "UTF-8");
				paramsTrans = java.net.URLDecoder.decode(paramsTrans, "UTF-8");
				params.put(key, paramsTrans.trim());
			}
		} catch (Exception e) {
		}
		List<DoufuTomorrowPlan> list = doufuTomorrowPlanService.entityList(params);
		new ExportExcel("明天工作计划表", DoufuTomorrowPlan.class).setDataList(list)
			.write(response, fileName).dispose();
	}

	/**
	 * 查询分页得到实体对象列表
	 * @param
	 * @return
	 */
	@RequestMapping(value="queryPageList", method = RequestMethod.POST)
	@ResponseBody
	PageInfo<DoufuTomorrowPlan>  queryPageList(HttpServletRequest request) throws Exception{
		HttpUtil httpUtil = new HttpUtil();
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex")) + 1;
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String sortField = request.getParameter("sortField").toString();
		String sortOrder = request.getParameter("sortOrder").toString();
		String reportDate =  request.getParameter("reportDate").toString();
		Map<String, Object> params = httpUtil.getParameterMap(request) ;

		params.put("pageNum", pageIndex);
		params.put("pageSize", pageSize);
		params.put("sortC", StringConvert.camelhumpToUnderline(sortField));
		params.put("order", sortOrder);
		params.put("reportDate", reportDate );
		logger.info("分页显示当天工作记录信息表，参数：" + params.toString());
		PageInfo<DoufuTomorrowPlan> page = doufuTomorrowPlanService.queryPageInfoEntity(params);
		return page;

	}

	/**
	 * 查询得到实例列表
	 * @param
	 * @return
	 */
	@RequestMapping(value="queryList", method = RequestMethod.POST)
	@ResponseBody
	List<DoufuTomorrowPlan>  queryList(HttpServletRequest request) throws Exception{
		HttpUtil httpUtil = new HttpUtil();
		Map<String, Object> params =  httpUtil.getParameterMap(request) ;;
		String reportDate =  request.getParameter("reportDate").toString();
		String sortField = request.getParameter("sortField").toString();
		String sortOrder = request.getParameter("sortOrder").toString();
		params.put("sortC", StringConvert.camelhumpToUnderline(sortField));
		params.put("order", sortOrder);
		params.put("reportDate", reportDate );
		List<DoufuTomorrowPlan> lstRtn = doufuTomorrowPlanService.entityList(params);
		return lstRtn;

	}
	/**
	 * 添加或更新
	 * @param params
	 * @return
	 */
	@RequestMapping(value="saveBatch", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Result saveBatch(HttpServletRequest request)  throws Exception {
		String data = request.getParameter("data") == null ? "" : request.getParameter("data");
		String report_date = request.getParameter("reportDate") == null ? "" : request.getParameter("reportDate");
		if (data == "[]") {
			return new Result(0, "操作失败，前台没有数据更改需要保存！");
		}
		if (report_date == "") {
			return new Result(0, "操作失败，前台没有设定报告时间！");
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
			List<DoufuTomorrowPlan> listEntyInsert = new ArrayList<DoufuTomorrowPlan>();
			List<DoufuTomorrowPlan> listEntyUpdate = new ArrayList<DoufuTomorrowPlan>();
			for (int i = list.size() - 1; i >= 0; i--) {
				Map<String, Object> row = list.get(i);
				String vid = row.get("id") != null ? row.get("id").toString() : "";
				String state = row.get("_state") != null ? row.get("_state").toString() : "";
				DoufuTomorrowPlan entryDoufuTomorrowPlan= new DoufuTomorrowPlan(row);
				if (vid == "" || vid == null) {
					String uuid = UUID.randomUUID().toString();
					entryDoufuTomorrowPlan.setId(uuid);
					entryDoufuTomorrowPlan.setProjectGroupId(sysuser.getProjectGroupId());
					entryDoufuTomorrowPlan.setProjectId(sysuser.getProjectGroupId());
					entryDoufuTomorrowPlan.setCreateDate(new Date());
					entryDoufuTomorrowPlan.setUpdateDate(new Date());
					entryDoufuTomorrowPlan.setDelFlag(Constant.DEL_FLAG_NORMAL);
					entryDoufuTomorrowPlan.setStatus(Constant.DEL_FLAG_NORMAL);
					entryDoufuTomorrowPlan.setInstId(sysuser.getOrganId());
					entryDoufuTomorrowPlan.setLoginIp(sysuser.getLoginIp());
					entryDoufuTomorrowPlan.setLoginDate(new Date());
					entryDoufuTomorrowPlan.setCreateBy(sysuser.getId());
					entryDoufuTomorrowPlan.setUpdateBy(sysuser.getId());
					entryDoufuTomorrowPlan.setReportDate(report_date);
					listEntyInsert.add(entryDoufuTomorrowPlan);
				} else {
					entryDoufuTomorrowPlan.setUpdateDate(new Date());
					entryDoufuTomorrowPlan.setUpdateBy(sysuser.getId());
					entryDoufuTomorrowPlan.setLoginIp(sysuser.getLoginIp());
					listEntyUpdate.add(entryDoufuTomorrowPlan);
				}
                              }
			if (listEntyInsert != null && listEntyInsert.size() > 0) {
				successNumInsert =  doufuTomorrowPlanService.insertBatch(listEntyInsert);
			}
			if (listEntyUpdate != null && listEntyUpdate.size() > 0) {
				successNumUpdate = doufuTomorrowPlanService.updateBatch(listEntyUpdate);
			}

			if (failureNumInsert > 0) {
				failureMsg.insert(0, "，失败保存,增加数据时 " + failureNumInsert + " 条明天工作计划表数据，");
			}
			if (failureNumUpdate > 0) {
				failureMsg.insert(0, "，失败保存,更新数据时 " + failureNumUpdate + " 条明天工作计划表数据，");
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
}