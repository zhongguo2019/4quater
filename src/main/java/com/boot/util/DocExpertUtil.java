package com.boot.util;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
//@EnableAutoConfiguration
public class DocExpertUtil {
    @Autowired

    JdbcTemplate jdbcTemplate;
    public  void Export(String primarkey) throws IOException {
        String sql = null;
//        sql="SELECT work_content,CASE doning WHEN 1 THEN '0%' WHEN 2 THEN '10%' WHEN 3 THEN '50%' WHEN 4 THEN '80%' ELSE '100%' END  AS do,'客户风险系统' AS xt FROM t_happy_work_content WHERE title_id=?";
       sql="SELECT t.work_content,CASE t.doning WHEN 1 THEN '0%' WHEN 2 THEN '10%' WHEN 3 THEN '50%' WHEN 4 THEN '80%' ELSE '100%' END  AS do,c.systemname AS xt ,date_format(b.begindate,'%Y.%m.%d') as begindate1,date_format(b.enddate,'%Y.%m.%d') as enddate1,b.work_title FROM  "+ ConfigUtil.getValue("db.schema")+"t_happy_work_content t " +
               "   INNER JOIN  "+ConfigUtil.getValue("db.schema")+"t_happy_work_title b ON b.id=t.title_id  INNER JOIN "+ConfigUtil.getValue("db.schema")+"t_systemmaintenance c ON c.id=t.systemmaintenance WHERE  t.title_id=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{primarkey});

        String path = System.getProperty("user.dir") + File.separator + "db" + File.separator;
        File file = new File(path + "XX模板.docx");
        InputStream inputStream = new FileInputStream(file);
        XWPFDocument document = new XWPFDocument(inputStream);
        Iterator it = document.getTablesIterator();
        //表格内容替换添加
        Map<String,Object> map = new HashMap<String,Object>();
        String date=list.size()!=0?(list.get(0).get("begindate1").toString()+"-"+list.get(0).get("enddate1").toString()):(CurrentWeek.getCurrenproDay("")+"-"+CurrentWeek.getCurrenaftDay(""));
        map.put("date",date);
        while(it.hasNext()){
            XWPFTable table = (XWPFTable)it.next();
            int rcount = table.getNumberOfRows();
            rcount=(rcount>list.size()+3)?(list.size()+3):rcount;
            for(int i =0 ;i <=rcount;i++){
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells =  row.getTableCells();
                if (i<=3){
                for (XWPFTableCell cell : cells){
                    for(Map.Entry<String, Object> entry : map.entrySet()){
                        if (cell.getText().contains("${" + entry.getKey() + "}")){
                            XWPFParagraph newPara = new XWPFParagraph(cell.getCTTc().addNewP(), cell);
                            XWPFRun run=newPara.createRun();
                            String content= entry.getValue() != null ? entry.getValue().toString(): "无";
                            cell.removeParagraph(0);
                            run.setText(content);
                        }
                    }
                }
                }else if(i<=15){
                    Map<String, Object> map1 = list.get(i - 4);
                    for (int i1 = 1; i1 < cells.size(); i1++) {
                            String key="";
                            if (i1==1){
                                key = map1.get("work_content").toString();
                            }else if(i1==2){
                                key = map1.get("xt").toString();
                            }else if(i1==3){
                                key = map1.get("do").toString();
                            }
                            XWPFTableCell cell = cells.get(i1);
                            XWPFParagraph newPara = new XWPFParagraph(cell.getCTTc().addNewP(), cell);
                            XWPFRun run=newPara.createRun();
                            run.setText(key);

                        }
                }
            }
        }
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        String filename=list.size()!=0?(""+list.get(0).get("work_title").toString()+"周报["+list.get(0).get("begindate1").toString()+"-"+list.get(0).get("enddate1").toString()+"]"):("许春林"+CurrentWeek.getYear()+"年"+CurrentWeek.getMonth()+"月第"+CurrentWeek.getWeekOfMonth()+"周周报["+CurrentWeek.getCurrenproDay("")+"-"+CurrentWeek.getCurrenaftDay("")+"]");
        FileOutputStream fileOutputStream=new FileOutputStream(new File( com.getPath()+ File.separator+File.separator+filename+".doc"));
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.close();
        byteArrayOutputStream.close();
        inputStream.close();
        /*ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        outputStream.write(byteArrayOutputStream.toByteArray());
        inputClose(inputStream);*/
    }

    private void outClose(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void inputClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        System.out.println(com.getPath());


    }



}
