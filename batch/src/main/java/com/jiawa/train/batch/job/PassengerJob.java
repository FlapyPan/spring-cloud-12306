package com.jiawa.train.batch.job;

import cn.hutool.core.util.RandomUtil;
import com.jiawa.train.batch.feign.MemberFeign;
import jakarta.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@DisallowConcurrentExecution
public class PassengerJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(PassengerJob.class);

    @Resource
    MemberFeign memberFeign;

    /**
     * 初始化乘客，如果没有张三，就增加乘客张三，李四、王五同理，防止线上体验时乘客被删光
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 增加日志流水号
        MDC.put("LOG_ID", System.currentTimeMillis() + RandomUtil.randomString(3));
        LOG.info("初始化乘客数据开始");
        memberFeign.init();
        LOG.info("初始化乘客数据结束");
    }
}
