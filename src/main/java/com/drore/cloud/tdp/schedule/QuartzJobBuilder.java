package com.drore.cloud.tdp.schedule;

import com.drore.cloud.tdp.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 15:32 <br/>
 * @Author: zhangz
 */
public class QuartzJobBuilder {
    /**
     * 根据Cron Expression快速创建LOCAL类别job
     *
     * @param jobName        job 名称
     * @param jobClass       自定义实现Job接口的
     * @param cronExpression cron表达式
     * @return QuartzJob
     */
    public static QuartzJob quickBuildLocalCronJob(String jobName,
                                                   Class<? extends Job> jobClass,
                                                   String cronExpression) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName).build()), (newTrigger()
                .withIdentity(jobName)
                .withSchedule(cronSchedule(cronExpression)).build()), QuartzJob.JobType.LOCAL, "", cronExpression).jobClass(jobClass)
                .executeType(QuartzJob.ExecuteType.ADD).build();
    }

    /**
     * 根据Cron Expression快速创建LOCAL类别job有参数型
     * @param jobName
     * @param jobDataMap
     * @param jobClass
     * @param cronExpression
     * @return
     */
    public static QuartzJob quickBuildLocalCronJob(String jobName,
                                                   JobDataMap jobDataMap,
                                                   Class<? extends Job> jobClass,
                                                   String cronExpression) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName).setJobData(jobDataMap).build()), (newTrigger()
                .withIdentity(jobName)
                .withSchedule(cronSchedule(cronExpression)).build()), QuartzJob.JobType.LOCAL, "", cronExpression).jobClass(jobClass)
                .executeType(QuartzJob.ExecuteType.ADD).build();
    }

    /**
     * 根据Cron Expression快速创建REMOTE类别job
     *
     * @param jobGroup       job 组
     * @param jobName        job 名称
     * @param jobClass       自定义实现Job接口的类
     * @param cronExpression cron表达式
     * @return QuartzJob
     */
    public static QuartzJob quickBuildRemoteCronJob(String jobGroup, String jobName,
                                                    Class<? extends Job> jobClass,
                                                    String cronExpression) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .withSchedule(cronSchedule(cronExpression)).build()), QuartzJob.JobType.REMOTE, "", cronExpression).jobClass(jobClass)
                .executeType(QuartzJob.ExecuteType.ADD).build();
    }

    /**
     * 根据job开始时间快速创建LOCAL类别job
     *
     * @param jobGroup  job 组
     * @param jobName   job 名称
     * @param jobClass  自定义实现Job接口的类
     * @param startTime 开始执行时间
     * @return
     */
    public static QuartzJob quickBuildLocalJobWithStartDate(String jobGroup, String jobName,
                                                            Class<? extends Job> jobClass,
                                                            Date startTime) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .startAt(startTime)).build(), QuartzJob.JobType.LOCAL, DateUtil.format(startTime), "")
                .jobClass(jobClass).executeType(QuartzJob.ExecuteType.ADD).build();
    }

    /**
     * 根据job开始时间快速创建REMOTE类别job
     *
     * @param jobGroup  job 组
     * @param jobName   job 名称
     * @param jobClass  自定义实现Job接口的类
     * @param startTime 开始执行时间
     * @return
     */
    public static QuartzJob quickBuildRemoteJobWithStartDate(String jobGroup, String jobName,
                                                             Class<? extends Job> jobClass,
                                                             Date startTime) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .startAt(startTime)).build(), QuartzJob.JobType.REMOTE, DateUtil.format(startTime), "")
                .jobClass(jobClass).executeType(QuartzJob.ExecuteType.ADD).build();
    }

    /**
     * 根据job开始时间快速创建LOCAL类别job
     *
     * @param jobGroup    job 组
     * @param jobName     job 名称
     * @param jobClass    自定义实现Job接口的类
     * @param executeType ADD,UPDATE,DELETE
     * @return
     */
    public static QuartzJob quickBuildLocalStartNowJobWithExecuteType(String jobGroup, String jobName,
                                                                      Class<? extends Job> jobClass,
                                                                      QuartzJob.ExecuteType executeType) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .startNow()).build(), QuartzJob.JobType.LOCAL, DateUtil.format(new Date()), "")
                .jobClass(jobClass).executeType(executeType).build();
    }

    /**
     * 根据job开始时间快速创建REMOTE类别job
     *
     * @param jobGroup    job 组
     * @param jobName     job 名称
     * @param jobClass    自定义实现Job接口的类
     * @param executeType ADD,UPDATE,DELETE
     * @return
     */
    public static QuartzJob quickBuildRemoteStartNowJobWithExecuteType(String jobGroup, String jobName,
                                                                       Class<? extends Job> jobClass,
                                                                       QuartzJob.ExecuteType executeType) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .startNow()).build(), QuartzJob.JobType.REMOTE, DateUtil.format(new Date()), "")
                .jobClass(jobClass).executeType(executeType).build();
    }

    /**
     * 根据job类型&执行类型&执行时间 快速创建job
     *
     * @param jobGroup    job 组
     * @param jobName     job 名称
     * @param jobClass    自定义实现Job接口的类
     * @param jobType     LOCAL,REMOTE
     * @param executeType ADD,UPDATE,DELETE
     * @param startTime   job 执行时间
     * @return QuartzJob
     */
    public static QuartzJob quickBuildJobWithJobTypeAndExecuteTypeAndStartDate(String jobGroup, String jobName,
                                                                               Class<? extends Job> jobClass,
                                                                               QuartzJob.JobType jobType,
                                                                               QuartzJob.ExecuteType executeType,
                                                                               Date startTime) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .startAt(startTime)).build(), jobType, executeType, DateUtil.format(startTime), "")
                .jobClass(jobClass).build();
    }

    /**
     * 根据job类型&执行类型&job策略&执行时间 快速创建job
     *
     * @param jobGroup    job 组
     * @param jobName     job 名称
     * @param jobClass    自定义实现Job接口的类
     * @param jobType     LOCAL,REMOTE
     * @param executeType ADD,UPDATE,DELETE
     * @param jobStrategy HASH,SYSTEM_CAPACITY
     * @param startTime   job 执行时间
     * @return QuartzJob
     */
    public static QuartzJob quickBuildJobWithJobTypeAndExecuteTypeAndJobStrategyAndStartDate(String jobGroup, String jobName,
                                                                                             Class<? extends Job> jobClass,
                                                                                             QuartzJob.JobType jobType,
                                                                                             QuartzJob.ExecuteType executeType,
                                                                                             QuartzJob.JobStrategy jobStrategy,
                                                                                             Date startTime) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .startAt(startTime)).build(), jobType, executeType,jobStrategy, DateUtil.format(startTime), "")
                .jobClass(jobClass).build();
    }

    /**
     * 根据job类型&执行类型&cron执行表达式 快速创建job
     *
     * @param jobGroup       job 组
     * @param jobName        job 名称
     * @param jobClass       自定义实现Job接口的类
     * @param jobType        LOCAL,REMOTE
     * @param executeType    ADD,UPDATE,DELETE
     * @param cronExpression job cron执行表达式
     * @return QuartzJob
     */
    public static QuartzJob quickBuildJobWithJobTypeAndExecuteTypeAndCronExpression(String jobGroup, String jobName,
                                                                                    Class<? extends Job> jobClass,
                                                                                    QuartzJob.JobType jobType,
                                                                                    QuartzJob.ExecuteType executeType,
                                                                                    String cronExpression) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .withSchedule(cronSchedule(cronExpression)).build()), jobType, executeType, "", cronExpression)
                .jobClass(jobClass).build();
    }

    /**
     * 根据job类型&执行类型&job策略&cron执行表达式 快速创建job
     *
     * @param jobGroup       job 组
     * @param jobName        job 名称
     * @param jobClass       自定义实现Job接口的类
     * @param jobType        LOCAL,REMOTE
     * @param executeType    ADD,UPDATE,DELETE
     * @param jobStrategy    HASH,SYSTEM_CAPACITY
     * @param cronExpression job cron执行表达式
     * @return QuartzJob
     */
    public static QuartzJob quickBuildJobWithJobTypeAndExecuteTypeAndJobStrategyAndCronExpression(String jobGroup, String jobName,
                                                                                                  Class<? extends Job> jobClass,
                                                                                                  QuartzJob.JobType jobType,
                                                                                                  QuartzJob.ExecuteType executeType,
                                                                                                  QuartzJob.JobStrategy jobStrategy,
                                                                                                  String cronExpression) {
        return QuartzJobFactory.builder((newJob(jobClass)
                .withIdentity(jobName, jobGroup).build()), (newTrigger()
                .withIdentity(jobName, jobGroup)
                .withSchedule(cronSchedule(cronExpression)).build()), jobType, executeType,jobStrategy, "", cronExpression)
                .jobClass(jobClass).build();
    }
}
