package com.jiawa.train.business.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @SentinelResource("hello2")
    public void hello2() throws InterruptedException {
        Thread.sleep(500);
    }
}
