package com.cy.jtsystem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootTest
class JtSystemApplicationTests {

    @Test
    void contextLoads() {
    }


    //@Slf4j 当类上添加了这个注解,下面这句话就不需要写了
    //private static final Logger log=
    // LoggerFactory.getLogger(DataSourceTests.class);
    @Autowired
    private DataSource dataSource;//此对象在springboot启动时已自动配置
    @Test
    void testGetConnection()throws Exception{
        Connection conn=dataSource.getConnection();
        System.out.println("conn="+conn);
        log.debug("conn is {}",conn);
    }
}
