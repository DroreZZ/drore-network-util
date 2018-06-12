package com.drore.cloud.tdp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drore.cloud.tdp.entity.DeviceStatus;
import com.drore.cloud.tdp.entity.DeviceStatusMapper;
import com.drore.cloud.tdp.schedule.QuartzActionJob;
import com.drore.cloud.tdp.schedule.QuartzJob;
import com.drore.cloud.tdp.schedule.QuartzJobBuilder;
import com.drore.cloud.tdp.schedule.QuartzManage;
import com.drore.cloud.tdp.service.DeviceStatusService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 16:15 <br/>
 * @Author: zhangz
 */
@Component
public class DeviceStatusServiceImpl implements DeviceStatusService {

    @Autowired
    private DeviceStatusMapper deviceStatusMapper;


    @Override
    public int save(JSONObject deviceStatus) {
        QuartzManage quartz = QuartzManage.INSTANCE;
        quartz.start();
        JobDataMap jobdm = new JobDataMap();
        jobdm.put("ip", deviceStatus.getString("ip"));
        //生成定时任务需要的参数
        QuartzJob qj = QuartzJobBuilder.quickBuildLocalCronJob("ip",
                jobdm,
                QuartzActionJob.class,
                deviceStatus.getString("cron"));
       // scheduler.scheduleJob(job.getJobDetail(),job.getTrigger());
        quartz.add(qj);
        DeviceStatus ds= JSON.toJavaObject(deviceStatus,DeviceStatus.class);

       return deviceStatusMapper.insert(ds);
    }

    @Override
    public void saveAll(List<DeviceStatus> list) {
        for (DeviceStatus ds:list) {
            deviceStatusMapper.insert(ds);
        }
    }

    @Override
    public int delete(String id) {
        return deviceStatusMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(String id) {
        return deviceStatusMapper.updateByPrimaryKey(id);
    }

    @Override
    public List<DeviceStatus> findAll() {
        return  deviceStatusMapper.selectAll();
    }

    @Override
    public DeviceStatus findByIp(String ip) {

      return deviceStatusMapper.selectByIp(ip);

    }

    @Override
    public List<DeviceStatus> findByType(String type) {
        return deviceStatusMapper.selectByType(type);
    }
}
