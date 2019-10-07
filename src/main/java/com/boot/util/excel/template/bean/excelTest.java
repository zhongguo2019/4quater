package com.boot.util.excel.template.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.boot.util.FileUtils;
import com.boot.util.excel.template.utils.PoiUtil;

public class excelTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//List<TempCell> lst = new ArrayList();
		String fileName = "G:\\GITBRANCH_LOCAL\\sharewithothers\\4quater\\reporttemplate\\本半月工作计划及执行情况表_模版.xlsx";
		String outfileName = "G:\\GITBRANCH_LOCAL\\sharewithothers\\4quater\\reporttemplate\\本半月工作计划及执行情况表_赵祖龙.xlsx";
	    InputStream is = null;
	    Workbook wb = null;
	    try {
			is = new FileInputStream(fileName);
			
			 FileInputStream tps = new FileInputStream(new File(fileName));
	         final XSSFWorkbook tpWorkbook = new XSSFWorkbook(tps);
	         XSSFWorkbook workbook = new XSSFWorkbook();
	         workbook = tpWorkbook;
	 		FileUtils.createFile(outfileName);
	 		Sheet sheet = workbook.createSheet("赵祖龙半月报");
	        FileOutputStream os = new FileOutputStream(outfileName);
	        workbook.write(os);
	 			//List readExcel(InputStream is, String fileName) 
			os.close();
			tps.close();
			
			//List lst = PoiUtil.readExcel(is,fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


}
