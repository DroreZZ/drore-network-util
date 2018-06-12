package com.drore.cloud.tdp.entity;

import com.drore.cloud.tdp.entity.StatusLog;

public interface StatusLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(StatusLog record);

    int insertSelective(StatusLog record);

    StatusLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StatusLog record);

    int updateByPrimaryKey(StatusLog record);
}