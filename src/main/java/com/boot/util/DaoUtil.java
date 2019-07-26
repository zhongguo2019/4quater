package com.boot.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Controller
//@EnableAutoConfiguration
public class DaoUtil {


    /**
     * @Title: getJdbcTemplate
     * @Description: 获取JDBC 数据源
     * @param
     * @return JdbcTemplate
     * @throws
     */
   /* static DataSource datesource = (DataSource) BeanFactoryUtil
            .getBean("yjyk.sys.DataSource");
*/
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     * <br>miniui插入数据方法
     *
     * @param TableName 表名称
     * @param valuesMap Map对象（表字段及对应的数据）
     */

    public  boolean insert(String TableName, Map valuesMap) {
        valuesMap.remove("_id");
        valuesMap.remove("_uid");
        valuesMap.remove("_state");
        String sql = buildInsertSql(TableName, valuesMap);
        MapSqlParameterSource parameterSource = buildParameterSource(valuesMap);
        return executeUpdate(jdbcTemplate, sql, parameterSource);

    }

    /**
     * <br>miniui更新数据方法
     *
     * @param tableName  表名称
     * @param valueMap   Map对象（表字段及对应的数据）
     * @param whereCause where条件语句
     */

    public  boolean update(String tableName, Map valueMap,
                                 String whereCause) throws SQLException {
        valueMap.remove("_id");
        valueMap.remove("_uid");
        valueMap.remove("_state");
        valueMap.remove("nom");
        String sql = buildUpdateSql(tableName, valueMap, whereCause);
        MapSqlParameterSource parameterSource = buildParameterSource(valueMap);
        return executeUpdate(jdbcTemplate, sql, parameterSource);
    }

    /**
     * <br>miniui删除数据方法
     *
     * @param tableName  表名称
     * @param valuesMap  Map对象（表字段及对应的数据）
     * @param whereCause where条件语句
     */

    public  boolean delete(String tableName, Map valuesMap,
                                 String whereCause) throws SQLException {
        valuesMap.remove("_id");
        valuesMap.remove("_uid");
        valuesMap.remove("_state");
        // valuesMap.remove("nom");
        String sql = buildDeleteSql(tableName, whereCause);
        MapSqlParameterSource parameterSource = buildParameterSource(valuesMap);
        return executeUpdate(jdbcTemplate, sql, parameterSource);
    }

    private MapSqlParameterSource buildParameterSource(Map values) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        Iterator it = values.entrySet().iterator();

        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            parameterSource.addValue((String) entry.getKey(), entry.getValue());
        }

        return parameterSource;
    }

    private  boolean executeUpdate(JdbcTemplate jdbcTemplate1, String sql,
                                   MapSqlParameterSource parameterSource) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
                jdbcTemplate1.getDataSource());
        int effectedRow = jdbcTemplate.update(sql, parameterSource);
        return effectedRow >= 1;
    }

    private  Number executeUpdateWithReturnKey(DataSource dataSource,
                                               String sql, MapSqlParameterSource parameterSource, String primaryKey) {
        int i = 0;
        GeneratedKeyHolder keyholder = new GeneratedKeyHolder();

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
                dataSource);

        jdbcTemplate.update(sql, parameterSource, keyholder,
                new String[]{primaryKey});
        return keyholder.getKey();
    }

    private  String buildUpdateSql(String tableName, Map valuesMap,
                                         String whereCause) {
        StringBuilder sb = new StringBuilder();
        ArrayList list = new ArrayList(valuesMap.keySet());
        sb.append("UPDATE ".concat(tableName).concat(" SET "));
        sb.append(((String) list.get(0)).concat("= :").concat(
                (String) list.get(0)));

        for (int i = 1; i < list.size(); ++i) {
            sb.append(", ".concat((String) list.get(i)).concat("= :")
                    .concat((String) list.get(i)));
        }

        if (whereCause != null && whereCause.trim().length() > 0) {
            sb.append(" WHERE ".concat(whereCause));
        }

        return sb.toString();
    }

    private  String buildDeleteSql(String tableName,
                                         String whereCause) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE from  ".concat(tableName));
        if (whereCause != null && whereCause.trim().length() > 0) {
            sb.append(" WHERE ".concat(whereCause));
        }

        return sb.toString();
    }


    private  String buildInsertSql(String tableName, Map valuesMap) {

        StringBuilder sb = new StringBuilder();
        List list = new ArrayList(valuesMap.keySet());
        sb.append("INSERT INTO ".concat(tableName));
        sb.append("(");
        sb.append((String) list.get(0));
        for (int value = 1; value < list.size(); ++value) {
            sb.append(" ,".concat((String) list.get(value)));
        }
        sb.append(") VALUES (");
        sb.append(":".concat((String) list.get(0)));
        for (int var9 = 1; var9 < list.size(); ++var9) {
            sb.append(", :".concat((String) list.get(var9)));
        }
        sb.append(")");
        //System.out.println(sb.toString());
        return sb.toString();

    }

}
