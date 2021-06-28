package com.cy.scaprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * @author tarena
 */
@SpringBootApplication
public class ScaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaProviderApplication.class, args);
    }



    @Value("${server.port}")
    private String server;

    @RestController
    public class ProviderController {
        @GetMapping(value = "/provider/echo/{string}")
        public String doEcho(@PathVariable String string) {
            return server+"say:Hello Nacos Discovery " + string;
        }
    }



}
