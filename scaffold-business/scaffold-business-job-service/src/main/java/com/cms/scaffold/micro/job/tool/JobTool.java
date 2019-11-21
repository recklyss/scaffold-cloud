package com.cms.scaffold.micro.job.tool;

import com.cms.scaffold.micro.executor.TaskAllowConcurrentExecutor;
import com.cms.scaffold.micro.executor.TaskDisallowConcurrentExecutor;
import com.cms.scaffold.micro.job.domain.JobInfo;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author zhangjiaheng@jianbing.com
 * @Description 整合Quartz的任务处理
 **/
@Component
public class JobTool {

    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private Scheduler scheduler;

    /**
     * 支持并发执行定时任务
     */
    private static final Integer IS_CONCURRENT = 1;

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    public void addOrUpdateJob(JobInfo job) throws SchedulerException {
        if (job == null) {
            return;
        }

        logger.info("add scheduler:{}", scheduler.toString());

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if (trigger == null) {
            Class clazz = IS_CONCURRENT.equals(job.getCouldConcurrent()) ?
                    TaskAllowConcurrentExecutor.class : TaskDisallowConcurrentExecutor.class;

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

            jobDetail.getJobDataMap().put("JobInfo", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger =
                    TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            logger.info("rescheduleJob : {}", job.getJobName());
            // Trigger已存在，那么更新相应的定时设置
            // 增加：withMisfireHandlingInstructionDoNothing()方法
            // 1，不触发立即执行
            // 2，等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
            CronScheduleBuilder scheduleBuilder =
                    CronScheduleBuilder.cronSchedule(job.getCronExpression()).withMisfireHandlingInstructionDoNothing();

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<JobInfo> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<JobInfo> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobInfo job = new JobInfo();
                addToJobList(jobList, jobKey, trigger, job);
            }
        }
        return jobList;
    }

    private void addToJobList(List<JobInfo> jobList, JobKey jobKey, Trigger trigger, JobInfo job) throws SchedulerException {
        job.setJobName(jobKey.getName());
        job.setJobGroup(jobKey.getGroup());
        job.setJobDescription("触发器:" + trigger.getKey());
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            job.setCronExpression(cronExpression);
        }
        jobList.add(job);
    }

    /**
     * 所有正在执行的job（已经开始调用还没有执行完成的）
     *
     * @return
     * @throws SchedulerException
     */
    public List<JobInfo> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<JobInfo> jobList = new ArrayList<JobInfo>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            JobInfo job = new JobInfo();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            addToJobList(jobList, jobKey, trigger, job);
        }
        return jobList;
    }

    /**
     * 暂停一个job (可能导致堆积，重新开始会执行多个)
     *
     * @param jobInfo
     * @throws SchedulerException
     */
    public void pauseJob(JobInfo jobInfo) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param jobInfo
     * @throws SchedulerException
     */
    public void resumeJob(JobInfo jobInfo) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @throws SchedulerException
     */
    public void deleteJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 删除job根据list
     *
     * @param jobs
     * @throws SchedulerException
     */
    public void deleteJobs(List<JobInfo> jobs) throws SchedulerException {
        List<JobKey> keys = new ArrayList<>();
        for (JobInfo job : jobs) {
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            keys.add(jobKey);
        }

        scheduler.deleteJobs(keys);
    }

    /**
     * 立即执行job
     *
     * @param jobInfo
     * @throws SchedulerException
     */
    public void runAJobNow(JobInfo jobInfo) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
        scheduler.triggerJob(jobKey);
    }
}
