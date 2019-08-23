package com.boot.util.excel.fieldtype;

import com.boot.util.spring.utils.SpringContextHolder;
import com.boot.web.codegen.model.Projects;
import com.boot.web.codegen.service.ProjectsService;

/**
 * 字段类型转换
 *
 * @author
 */
public class ProjectsType {

    static ProjectsService projectsService = SpringContextHolder.getBean("projectsService");

    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {
        if (!val.equals("")) {
            for (Projects e : projectsService.selectAll()) {
                if (val.trim().equals(e.getProName())) {
                    return e.getId();
                }
            }

        }
        return null;
    }

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null) {
            for (Projects e : projectsService.selectAll()) {
                if (val.toString().equals(e.getId())) {
                    return e.getProName();
                }
            }
        }
        return null;
    }
}
