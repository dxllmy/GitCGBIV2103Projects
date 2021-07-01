package com.cy.jtportals;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tarena
 */
@EnableFeignClients
@SpringBootApplication
public class JtPortalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtPortalsApplication.class, args);
    }

    /**
     * 问题：发现通过feign从消费者调用生产者的业务后，分页查询功能不能实现
     * Feign 方式调用时的请求拦截器,通过此拦截器拦截到请求数据以后,可以
     * 结合业务对请求数据进行预处理
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            /**在此方法可以通过requestTemplate对象向新的请求中写数据*/
            @Override
            public void apply(RequestTemplate requestTemplate) {
                //1.获取原有请求中的数据
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = requestAttributes.getRequest();
                //2.将原有请求中数据添加到新的请求中
                //requestTemplate.header()//向请求头添加数据
                requestTemplate.query("pageCurrent", request.getParameter("pageCurrent"));
                requestTemplate.query("pageSize",request.getParameter("pageSize"));
            }
        };
    }
}


