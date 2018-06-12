package com.drore.cloud.tdp.schedule;
import com.drore.cloud.tdp.schedule.QuartzJob.ExecuteType;
import com.drore.cloud.tdp.schedule.QuartzJob.JobStrategy;
import com.drore.cloud.tdp.schedule.QuartzJob.JobType;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: <br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 15:35 <br/>
 * @Author: zhangz
 */
public class QuartzJobFactory {

    public static Builder builder(JobDetail jobDetail, Trigger trigger,String startTime,String cronExpression,String jobClassPath) {
        return new Builder(jobDetail, trigger,startTime,cronExpression,jobClassPath);
    }
    public static Builder builder(JobDetail jobDetail, Trigger trigger, JobType jobType,String startTime,String cronExpression) {
        return new Builder(jobDetail, trigger, jobType,startTime,cronExpression);
    }
    public static Builder builder(JobDetail jobDetail, Trigger trigger, JobType jobType,ExecuteType executeType,
                                  String startTime,String cronExpression) {
        return new Builder(jobDetail, trigger, jobType,executeType,startTime,cronExpression);
    }
    public static Builder builder(JobDetail jobDetail, Trigger trigger, JobType jobType,ExecuteType executeType,
                                  JobStrategy jobStrategy,String startTime,String cronExpression) {
        return new Builder(jobDetail, trigger, jobType,executeType,jobStrategy,startTime,cronExpression);
    }

    public static class Builder {

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
        private String jobClassPath;

        Builder(JobDetail jobDetail, Trigger trigger,String startTime,String cronExpression,String jobClassPath) {
            this.jobDetail = jobDetail;
            this.trigger = trigger;
            this.jobType = JobType.LOCAL;
            this.executeType = ExecuteType.ADD;
            this.jobStrategy = JobStrategy.HASH;
            this.startTime = startTime;
            this.cronExpression = cronExpression;
            this.jobClassPath = jobClassPath;
        }
        Builder(JobDetail jobDetail, Trigger trigger, JobType jobType,String startTime,String cronExpression) {
            this.jobDetail = jobDetail;
            this.trigger = trigger;
            this.jobType = jobType;
            this.executeType = ExecuteType.ADD;
            this.jobStrategy = JobStrategy.HASH;
            this.startTime = startTime;
            this.cronExpression = cronExpression;
        }
        Builder(JobDetail jobDetail, Trigger trigger, JobType jobType,ExecuteType executeType,
                String startTime,String cronExpression) {
            this.jobDetail = jobDetail;
            this.trigger = trigger;
            this.jobType = jobType;
            this.executeType = executeType;
            this.jobStrategy = JobStrategy.HASH;
            this.startTime = startTime;
            this.cronExpression = cronExpression;
        }

        Builder(JobDetail jobDetail, Trigger trigger, JobType jobType,ExecuteType executeType,JobStrategy jobStrategy,
                String startTime,String cronExpression) {
            this.jobDetail = jobDetail;
            this.trigger = trigger;
            this.jobType = jobType;
            this.executeType = executeType;
            this.jobStrategy = jobStrategy;
            this.startTime = startTime;
            this.cronExpression = cronExpression;
        }

        public Builder jobClass(Class<? extends Job> jobClass) {
            this.jobClass = jobClass;
            return this;
        }

        public Builder executeType(ExecuteType executeType) {
            this.executeType = executeType;
            return this;
        }
        public Builder jobStrategy(JobStrategy jobStrategy) {
            this.jobStrategy = jobStrategy;
            return this;
        }

        public Builder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder cronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
            return this;
        }

        public Builder fixedClientIps(String... fixedClientIps) {
            this.fixedClientIps = fixedClientIps;
            return this;
        }

        public Builder fixedServerIps(String... fixedServerIps) {
            this.fixedServerIps = fixedServerIps;
            return this;
        }

        public Builder clientIp(String ip) {
            this.clientIp = ip;
            return this;
        }

        public QuartzJob build() {
            if (jobType != JobType.LOCAL  && jobClass == null)
                throw new ExceptionInInitializerError("ClientJobFactory-->>build() jobClass can not null");
            return new QuartzJob(this);
        }

        public Trigger getTrigger() {
            return trigger;
        }

        public JobDetail getJobDetail() {
            return jobDetail;
        }

        public ExecuteType getExecuteType() {
            return executeType;
        }

        public JobType getJobType() {
            return jobType;
        }

        public JobStrategy getJobStrategy() {
            return jobStrategy;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getCronExpression() {
            return cronExpression;
        }

        public String[] getFixedClientIps() {
            return fixedClientIps;
        }

        public String[] getFixedServerIps() {
            return fixedServerIps;
        }

        public Class<? extends Job> getJobClass() {
            return jobClass;
        }

        public String getClientIp() {
            return clientIp;
        }

    }
}
