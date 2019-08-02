package com.boot.util.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.boot.util.CommonEntity;
import com.boot.util.mybatis.provider.CommonSqlProvider;

@Repository("baseMapper")
public interface BaseMapper {

    @SelectProvider(type = CommonSqlProvider.class, method = "beforeDeleteTreeStructureSql")
    int beforeDeleteTreeStructure(Map<String, Object> params);

    @SelectProvider(type = CommonSqlProvider.class, method = "findEntityListByDataScope")
    <T> List<T> findEntityListByDataScope(T record);

    @SelectProvider(type = CommonSqlProvider.class, method = "exeuteDynamicSql")
    List<CommonEntity> exeuteDynamicSql(Map<String, Object> params);

}
