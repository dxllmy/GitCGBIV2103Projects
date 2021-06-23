package com.cy.jtdemos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author tarena
 */
@EnableCaching
@SpringBootApplication
public class JtDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtDemosApplication.class, args);
    }

}
