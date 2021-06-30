package com.cy.scaprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author tarena
 * nacos注册中心生产者
 */
@SpringBootApplication
public class ScaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaProviderApplication.class, args);
    }


    /**
     *     读取application.yml中的server.port
     */
    @Value("${server.port}")
    private String server;

    /**
     * RefreshScope注解：动态刷新配置
     */
    @RefreshScope
    @RestController
    public class ProviderController {

        @Value("${logging.level.com.cy:error}")
        private String logLevel;
        @GetMapping("/provider/doGetLogLevel")
        public String doGetLogLevel(){
            return "log level is "+logLevel;
        }


        @GetMapping(value = "/provider/echo/{string}")
        public String doEcho(@PathVariable String string) {
            return server+"say:Hello Nacos Discovery " + string;
        }
    }



}
