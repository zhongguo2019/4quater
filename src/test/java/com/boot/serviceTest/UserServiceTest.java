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
import com.boot.web.sysuser.service.SysUserService;
import com.boot.web.todaywork.model.DoufuTodayWork;
import com.boot.web.todaywork.service.DoufuTodayWorkService;
import com.boot.web.sysuser.model.SysUser;

public class UserServiceTest extends SpringTestCase{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired  
    private SysUserService sysUserService; 
	
	@Autowired  
    private DoufuTodayWorkService doufuTodayWorkService; 
	@Test  
    public void selectUserByIdTest(){  
		
		  SysUser user = sysUserService.selectUserByName("admin"); 
		  logger.info("查找结果" +  user);
		 
	     Map<String, Object> mapparams = new HashMap<String,Object>();
	     DoufuTodayWork doufuTodayWork =new DoufuTodayWork();
	     mapparams.put("dynamicSQL", "");
		try{
			  logger.info("查找结果==----------------------===" );
			  mapparams.put("id", "1");
			  List<DoufuTodayWork> lstRtn = (List<DoufuTodayWork>) doufuTodayWorkService.queryOne(mapparams);
			  logger.info("----------------------------查找结果=====" );
		}catch(Exception e){
	      logger.info(e.getMessage());
	     
		}
    }  

	public void getWeek() {
        long startTime1 = 1530613938532l;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setMinimalDaysInFirstWeek(4);//可以不用设置
        calendar.setTimeInMillis(System.currentTimeMillis());//获得当前的时间戳
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周
        
        System.out.println("第几周："+weekOfYear);
        calendar.setWeekDate(weekYear, weekOfYear, 2);//获得指定年的第几周的开始日期
        long starttime = calendar.getTime().getTime();//创建日期的时间该周的第一天，
        calendar.setWeekDate(weekYear, weekOfYear, 1);//获得指定年的第几周的结束日期
        long endtime = calendar.getTime().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStart = simpleDateFormat.format(starttime);//将时间戳格式化为指定格式
        String dateEnd = simpleDateFormat.format(endtime);
        System.out.println(dateStart);
        System.out.println(dateEnd);
	}
}
