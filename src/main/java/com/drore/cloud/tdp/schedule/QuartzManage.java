package com.drore.cloud.tdp.schedule;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 16:08 <br/>
 * @Author: zhangz
 */
public enum QuartzManage {
    INSTANCE;

    private Logger logger = Logger.getLogger(QuartzManage.class);

    private Scheduler scheduler;

    public boolean start() {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            if(!scheduler.isStarted()) scheduler.start();
            return true;
        } catch (Exception e) {
            if(logger.isDebugEnabled()) e.printStackTrace();
            logger.error("QuartzManage-->>start() error ", e);
            String execMethod = "QuartzManage-->>start()";
            String execResult = "QuartzManage-->>start() error ," + e.getMessage();
            //spark
            return false;
        }
    }

    public boolean stop() {
        try {
            if(scheduler.isStarted()) scheduler.shutdown(true);
            return true;
        } catch (SchedulerException e) {
            if(logger.isDebugEnabled()) e.printStackTrace();
            logger.error("QuartzManage-->>stop() error ", e);
            String execMethod = "QuartzManage-->>stop()";
            String execResult = "QuartzManage-->>stop() error ," + e.getMessage();
            //spark
            return false;
        }
    }

    public boolean add(QuartzJob job){
        try{
            if (isExists(job)){
                if(logger.isDebugEnabled())
                    logger.debug(" QuartzManage-->>add("+job.toString()+") exists ");
                String execMethod = "QuartzManage-->>add("+job.toString()+")";
                String execResult = "QuartzManage-->>add("+job.toString()+") exists  " ;
                //spark
                return false;
            }
            scheduler.scheduleJob(job.getJobDetail(),job.getTrigger());
            return true;
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("quartzManage add 添加时异常"+e.getMessage());
            //spark
            //throw new ErrorException("1001","quartzManage add 添加时异常",e.getMessage());
            return false;
        }

    }

    public boolean isExists(QuartzJob job) {
        try {
            return scheduler.checkExists(job.getJobDetail().getKey());
        } catch (SchedulerException e) {
            if(logger.isDebugEnabled()) e.printStackTrace();
            logger.error("QuartzManage-->>isExists("+job.toString()+") error ",e);
            //spark
            return false;
        }
    }

    public boolean delete(QuartzJob job){
        try {
            if(scheduler.checkExists(job.getJobDetail().getKey())){
                return scheduler.deleteJob(job.getJobDetail().getKey());
            }else {
                logger.error("QuartzManage-->>delete("+job.toString()+") the clientJob not exist ");
                String execMethod = "QuartzManage-->>delete("+ job.toString()+") ";
                String execResult = "QuartzManage-->>delete("+ job.toString()+") the clientJob not exist " ;
                //spark
                return false;
            }
        } catch (Exception e) {
            if(logger.isDebugEnabled()) e.printStackTrace();
            logger.error("QuartzManage-->>delete("+job.toString()+") error ",e);
            String execMethod = "QuartzManage-->>delete("+job.toString()+")";
            String execResult = "QuartzManage-->>delete("+job.toString()+") error ," + e.getMessage();
            //spark
            return false;
        }
    }

    public boolean update(QuartzJob job){
        try{
            delete(job);
            return  add(job);
        }catch (Exception e){
            if(logger.isDebugEnabled()) e.printStackTrace();
            logger.error("QuartzManage-->>update("+ job.toString()+") error ",e);
            String execMethod = "QuartzManage-->>update("+ job.toString()+")  ";
            String execResult = "QuartzManage-->>update("+ job.toString()+") error, " + e.getMessage();
            //spark
            return false;
        }
    }

    public boolean addJobListener(QuartzJobListener listener, QuartzJob job) {
        Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getJobDetail().getKey());
        try {
            scheduler.getListenerManager().addJobListener(listener, matcher);
            return true;
        } catch (SchedulerException e) {
            if(logger.isDebugEnabled()) e.printStackTrace();
            logger.error("MyScheduler-->>addJobListener("+ JSON.toJSONString(listener)+","+job.toString()+") error ",e);
            String execMethod = "MyScheduler-->>addJobListener("+ JSON.toJSONString(listener)+","+job.toString()+") ";
            String execResult = "MyScheduler-->>addJobListener("+ JSON.toJSONString(listener)+","+job.toString()+") error," + e.getMessage();
            //spark
            return false;
        }
    }
}
