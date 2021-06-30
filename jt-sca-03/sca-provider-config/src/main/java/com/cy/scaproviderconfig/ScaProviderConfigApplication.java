package com.cy.scaproviderconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tarena
 * nacos配置中心
 */
@SpringBootApplication
public class ScaProviderConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaProviderConfigApplication.class, args);
    }

}
