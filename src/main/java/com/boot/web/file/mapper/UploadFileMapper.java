package com.boot.web.file.mapper;

import java.util.List;
import java.util.Map;

import com.boot.util.CommonEntity;
import com.boot.web.file.model.UploadFile;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author Parker
 * 附件表DAO层
 * 2017-12-11
 */
public interface UploadFileMapper extends Mapper<UploadFile> {

    List<CommonEntity> queryPageInfo(Map<String, Object> params);

    List<UploadFile> entityList(Map<String, Object> params);

    UploadFile queryOne(Map<String, Object> params);

    int deleteByParams(Map<String, Object> params);

    int deleteRepeted(Map<String, Object> params);
}
