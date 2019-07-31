package com.cms.scaffold.micro.job.tool;

import com.cms.scaffold.micro.job.domain.JobInfo;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author zhangjiaheng
 * @Description
 **/
public class InvokeTool {
    private static final Logger logger = LoggerFactory.getLogger(InvokeTool.class);

    /**
     * 执行job指定的方法
     *
     * @param job
     * @return
     */
    public boolean invokMethod(JobInfo job) throws JobExecutionException {

        return false;
    }
}
