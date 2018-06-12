package com.drore.cloud.tdp.api;

import com.alibaba.fastjson.JSONObject;
import com.drore.cloud.tdp.entity.DeviceStatus;
import com.drore.cloud.tdp.pingip.PingAndPutIp;
import com.drore.cloud.tdp.service.DeviceStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 11:59 <br/>
 * @Author: zhangz
 */
@RestController
@RequestMapping("/tdp/device")
public class DeviceStatusController {

    @Resource
    private DeviceStatusService deviceStatusService;


    /**
     * 根据IP查询监测结果
     */
    @RequestMapping(value = "/findByIp")
    public DeviceStatus findByIp(@RequestBody JSONObject ip){
        return   deviceStatusService.findByIp(ip.getString("ip"));
    }

    /**
     * 查询监测结果
     */
    @GetMapping(value = "/findAll")
    public List<DeviceStatus> findAll(){
        return   deviceStatusService.findAll();
    }

    /**
     * 根据设备类型查询监测结果
     */
    @RequestMapping(value = "/findByType")
    public List<DeviceStatus> findByType(@RequestBody JSONObject type){
        return   deviceStatusService.findByType(type.getString("type"));
    }

    /**
     * 添加监测定时任务
     */
    @RequestMapping(value = "/saveSchedule")
    public int saveSchedule(@RequestBody JSONObject deviceStatus){
      return   deviceStatusService.save(deviceStatus);
    }
    /**
     * 删除监测定时任务
     */
    @RequestMapping(value = "/deleteSchedule")
    public int deleteSchedule(@RequestBody JSONObject id){
        return   deviceStatusService.delete(id.getString("id"));
    }

    /**
     * 执行跟踪命令
     */
    @RequestMapping(value = "/tracert")
    public String tracert(@RequestBody JSONObject ip){
        return PingAndPutIp.tracert(ip.getString("ip"));
    }

}
