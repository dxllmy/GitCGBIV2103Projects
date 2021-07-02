package com.cy.scaconsumer.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author tarena
 * //启动类的在doRestEcho03()中流控规则中的链路限流
 */
@Service
public class ConsumerService{
    @SentinelResource("doConsumerService")
    public String doConsumerService(){
        return "do consumer service";
    }
}
