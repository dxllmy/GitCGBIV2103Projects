package com.cy.scaproviderconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tarena
 */
@RefreshScope //支持配置动态刷新
@RestController
@RequestMapping("/config")
public class NacosConfigController {

    @Value("${logging.level.com.cy:error}")
//    @Value("${user.id}")
    private String logLevel;

    @RequestMapping("/doGetLogLevel")
    public String doGetLogLevel() {
        return "Log level is " + logLevel;
    }


    @Value("${server.tomcat.threads.max:200}")
    private String maxThread;

    @GetMapping("/doGetMaxThread")
    public String doGetMaxThread(){
        return "tomcat max thread is"+maxThread;
    }


    @Value("${page.pageSize:10}")
    private Integer pageSize;

    @GetMapping("/doGetPageSize")
    public String doGetPageSize(){
        return "page size is "+pageSize;
    }

}
