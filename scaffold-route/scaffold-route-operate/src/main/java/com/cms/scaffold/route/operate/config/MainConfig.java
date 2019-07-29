package com.cms.scaffold.route.operate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author zhangjiaheng
 * @Description 一些主要的系统配置
 **/
@Configuration
public class MainConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //此方法返回可用处理器的虚拟机的最大数量; 不小于1
        int core = Runtime.getRuntime().availableProcessors();
        //设置核心线程数
        executor.setCorePoolSize(core);
        //设置最大线程数
        executor.setMaxPoolSize(core * 2 + 1);
        //除核心线程外的线程存活时间
        executor.setKeepAliveSeconds(3);
        //如果传入值大于0，底层队列使用的是LinkedBlockingQueue,否则默认使用SynchronousQueue
        executor.setQueueCapacity(40);
        //线程名称前缀
        executor.setThreadNamePrefix("taskExecutor-");
        //设置拒绝策略 调用者执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
