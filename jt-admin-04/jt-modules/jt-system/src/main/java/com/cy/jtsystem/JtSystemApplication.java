package com.cy.jtsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author tarena
 * 生产者端
 * EnableCaching注解 ：项目初始化时，初始化缓存。（将redis放到项目中实战的时候开始用的）
 */
@EnableCaching
@SpringBootApplication
public class JtSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtSystemApplication.class, args);
    }

}
