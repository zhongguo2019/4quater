package com.boot.serviceTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.boot.baseTest.SpringTestCase;
import com.boot.util.excel.template.utils.PoiUtil;
import com.boot.web.todaywork.service.DoufuTodayWorkService;
import com.boot.web.wxgroup.model.WxUserGroup;

public class commonTest extends SpringTestCase {
	DoufuTodayWorkService doufuTodayWorkService;
	@Test
	public void debugExcelUtil() throws FileNotFoundException {
		
		String fileName = "G:\\GITBRANCH_LOCAL\\sharewithothers\\4quater\\reporttemplate\\本半月工作计划及执行情况表_模版.xlsx";
        InputStream is = null;
        Workbook wb = null;
        is = new FileInputStream(fileName);
       // List readExcel(InputStream is, String fileName)
		PoiUtil.readExcel(is,fileName);
		
	}
	
	
	
}
