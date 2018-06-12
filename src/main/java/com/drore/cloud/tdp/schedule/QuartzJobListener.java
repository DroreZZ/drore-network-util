package com.drore.cloud.tdp.schedule;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 16:08 <br/>
 * @Author: zhangz
 */
public class QuartzJobListener implements JobListener {

    private Logger logger = Logger.getLogger(QuartzJobListener.class);

    @Override
    public String getName() {
        return QuartzJobListener.class.getSimpleName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
    }
    //logger.info("job [{}] is about to be executed.", getJobKey(context).toString());
    //System.err.println("jobToBeExecuted:"+context.getJobDetail().getKey());
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
//        logger.info("job [{}] execution was vetoed.", getJobKey(context));
        System.err.println("jobExecutionVetoed:"+context.getJobDetail().getKey());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        //logger.info("job [{}] was executed.", getJobKey(context).toString());
        //System.err.println("jobWasExecuted:"+context.getJobDetail().getKey());
    }

    private JobKey getJobKey(JobExecutionContext context) {
        return context.getJobDetail().getKey();
    }
}
