package com.boot.serviceTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.baseTest.SpringTestCase;
import com.boot.util.CommonEntity;
import com.boot.util.StringConvert;
import com.boot.util.SysUserUtils;
import com.boot.web.sys.service.SysUserService;
import com.boot.web.todaywork.model.DoufuTodayWork;
import com.boot.web.todaywork.service.DoufuTodayWorkService;
import com.github.pagehelper.PageInfo;
import com.boot.web.sys.model.SysUser;
import com.boot.web.todaywork.controller.DoufuTodayWorkController;

public class UserServiceTest extends SpringTestCase {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private DoufuTodayWorkService doufuTodayWorkService;
     
	@Autowired
	private DoufuTodayWorkController doufuTodayWorkController;
	@Test
	public void selectUserByIdTest() {

		SysUser user = sysUserService.selectUserByName("admin");
		logger.info("查找结果" + user);

		Map<String, Object> params = new HashMap<String, Object>();
		DoufuTodayWork doufuTodayWork = new DoufuTodayWork();

		try {

			logger.info("查找结果==----------------------===");
			params.put("pageNum", "1");
			params.put("pageSize", "5");			
//			params.put("pageList", "[10, 25, 50, 100]");				

			//List<DoufuTodayWork> lstRtn = (List<DoufuTodayWork>) doufuTodayWorkService.queryOne(mapparams);
			
		//	List<CommonEntity>  lst = sysUserService.getUserModule(mapparams);
			
//			params.put("dynamicSQL", SysUserUtils.dataScopeFilterString1("o", "u", " todaywork/doufuTodayWork", "id"));
			if (params.containsKey("sortC")) {
				// 如果传过来的参数是驼峰式，这里需要将驼峰转成下划线式
				params.put("sortC", StringConvert.camelhumpToUnderline(params.get("sortC").toString()));
			}
			PageInfo<DoufuTodayWork> page = doufuTodayWorkService.queryPageInfo1(params);
			PageInfo<CommonEntity> page2 =doufuTodayWorkService.queryPageInfo(params);
			// doufuTodayWorkService.queryOne(params);
			CameHumpInterceptor.
			
			logger.info("----------------------------查找结果=====");
		} catch (Exception e) {
			logger.info(e.getMessage());

		}

	}

	public void getWeek() {
		long startTime1 = 1530613938532l;
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// 设置星期一为一周开始的第一天
		calendar.setMinimalDaysInFirstWeek(4);// 可以不用设置
		calendar.setTimeInMillis(System.currentTimeMillis());// 获得当前的时间戳
		int weekYear = calendar.get(Calendar.YEAR);// 获得当前的年
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);// 获得当前日期属于今年的第几周

		System.out.println("第几周：" + weekOfYear);
		calendar.setWeekDate(weekYear, weekOfYear, 2);// 获得指定年的第几周的开始日期
		long starttime = calendar.getTime().getTime();// 创建日期的时间该周的第一天，
		calendar.setWeekDate(weekYear, weekOfYear, 1);// 获得指定年的第几周的结束日期
		long endtime = calendar.getTime().getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStart = simpleDateFormat.format(starttime);// 将时间戳格式化为指定格式
		String dateEnd = simpleDateFormat.format(endtime);
		System.out.println(dateStart);
		System.out.println(dateEnd);
	}
}
