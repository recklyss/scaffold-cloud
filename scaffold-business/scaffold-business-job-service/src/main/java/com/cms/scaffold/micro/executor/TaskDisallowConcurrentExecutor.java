package com.cms.scaffold.micro.executor;

import com.cms.scaffold.code.config.commonly.SpringContextHolder;
import com.cms.scaffold.micro.job.domain.JobInfo;
import com.cms.scaffold.micro.job.tool.InvokeTool;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author zhangjiaheng@jianbing.com
 * @Description 执行方法 不可并发
 **/
@DisallowConcurrentExecution
public class TaskDisallowConcurrentExecutor implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobInfo jobInfo = (JobInfo) context.getMergedJobDataMap().get("JobInfo");
        InvokeTool invokeJobUtils = SpringContextHolder.getBean(InvokeTool.class);
        invokeJobUtils.invokMethod(jobInfo);
    }
}
