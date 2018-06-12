package com.drore.cloud.tdp.entity;

import com.drore.cloud.tdp.entity.DeviceStatus;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface DeviceStatusMapper {

    int deleteByPrimaryKey(String id);

    int insert(DeviceStatus record);

    int insertSelective(DeviceStatus record);

    DeviceStatus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceStatus record);

    int updateByPrimaryKey(String id);

    DeviceStatus selectByIp(String ip);

    List<DeviceStatus> selectByType(String type);

    List<DeviceStatus> selectAll();
}