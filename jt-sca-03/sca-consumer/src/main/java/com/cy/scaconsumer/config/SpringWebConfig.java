package com.cy.scaconsumer.config;

import com.cy.scaconsumer.interceptor.TimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tarena
 */
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeInterceptor())
                .addPathPatterns("/consumer/*");
    }
}
