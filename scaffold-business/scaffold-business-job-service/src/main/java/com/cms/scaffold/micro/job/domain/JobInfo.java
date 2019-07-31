package com.cms.scaffold.micro.job.domain;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author zhangjiaheng
 * @Description 任务信息表
 **/
@Getter
@Setter
@ToString
public class JobInfo extends BaseEntity {
    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 可以并行执行
     */
    private Integer couldConcurrent;

    /**
     * 任务组
     */
    private String jobGroup;

    /**
     * corn表达式
     */
    private String cronExpression;

    /**
     * 任务描述
     */
    private String jobDescription;
}
