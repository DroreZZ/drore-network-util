package com.drore.cloud.tdp.service;

import com.alibaba.fastjson.JSONObject;
import com.drore.cloud.tdp.entity.DeviceStatus;
import com.drore.cloud.tdp.entity.DeviceStatusMapper;

import java.util.List;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 16:14 <br/>
 * @Author: zhangz
 */
public interface DeviceStatusService  {

    int save(JSONObject deviceStatus);

    void saveAll(List<DeviceStatus> list);

    int delete(String id);

    int update(String id);

    List<DeviceStatus> findAll();

    DeviceStatus findByIp(String ip);

    List<DeviceStatus> findByType(String type);
}
