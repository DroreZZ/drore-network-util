package com.drore.cloud.tdp.schedule;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import com.drore.cloud.tdp.schedule.QuartzJobFactory.Builder;
import java.io.Serializable;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 15:34 <br/>
 * @Author: zhangz
 */
public class QuartzJob implements Serializable {

    private static final long serialVersionUID = -1774577970624467182L;

    private Trigger trigger;
    private JobDetail jobDetail;
    private ExecuteType executeType;
    private JobType jobType;
    private JobStrategy jobStrategy;
    private String startTime;
    private String cronExpression;
    private String[] fixedClientIps;
    private String[] fixedServerIps;
    private Class<? extends Job> jobClass;
    private String clientIp;
    private String serverIp;
    private String jobClassName;
    private boolean isRecoverJobFromDB;
    private String jobClassPath;

    public QuartzJob(Builder builder){
        this.executeType = builder.getExecuteType();
        this.jobType = builder.getJobType();
        this.jobStrategy = builder.getJobStrategy();
        this.startTime = builder.getStartTime();
        this.cronExpression = builder.getCronExpression();
        this.fixedClientIps = builder.getFixedClientIps();
        this.fixedServerIps = builder.getFixedServerIps();
        this.trigger = builder.getTrigger();
        this.jobDetail = builder.getJobDetail();
        this.jobClass = builder.getJobClass();
        this.clientIp = builder.getClientIp();
        this.jobClassPath = builder.getClientIp();
    }

    public static enum ExecuteType {
        ADD, UPDATE, DELETE
    }

    public static enum JobType {
        LOCAL, REMOTE, BROADCAST
    }

    public static enum JobStrategy{
        HASH,SYSTEM_CAPACITY
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public ExecuteType getExecuteType() {
        return executeType;
    }

    public void setExecuteType(ExecuteType executeType) {
        this.executeType = executeType;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public JobStrategy getJobStrategy() {
        return jobStrategy;
    }

    public void setJobStrategy(JobStrategy jobStrategy) {
        this.jobStrategy = jobStrategy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String[] getFixedClientIps() {
        return fixedClientIps;
    }

    public void setFixedClientIps(String[] fixedClientIps) {
        this.fixedClientIps = fixedClientIps;
    }

    public String[] getFixedServerIps() {
        return fixedServerIps;
    }

    public void setFixedServerIps(String[] fixedServerIps) {
        this.fixedServerIps = fixedServerIps;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public boolean isRecoverJobFromDB() {
        return isRecoverJobFromDB;
    }

    public void setRecoverJobFromDB(boolean recoverJobFromDB) {
        isRecoverJobFromDB = recoverJobFromDB;
    }

    public String getJobClassPath() {
        return jobClassPath;
    }

    public void setJobClassPath(String jobClassPath) {
        this.jobClassPath = jobClassPath;
    }
}