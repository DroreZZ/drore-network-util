package com.drore.cloud.tdp.schedule;

import com.drore.cloud.tdp.pingip.PingAndPutIp;
import com.drore.cloud.tdp.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 18:58 <br/>
 * @Author: zhangz
 */
public class QuartzActionJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        Logger logger = LoggerFactory.getLogger(QuartzActionJob.class);
        JobDataMap jobDataMap = jec.getMergedJobDataMap();

        if(logger.isDebugEnabled()){
            logger.debug(DateUtil.format(new Date())+"QuartzActionJob驱动业务——————————"+jobDataMap.get("parms").toString());
        }

        PingAndPutIp.isConnect(jobDataMap.getString("ip"));
    }
}
