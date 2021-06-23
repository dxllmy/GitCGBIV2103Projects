package com.cy.jtmember;

import com.cy.jt.common.basic.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author tarena
 */
@SpringBootApplication
public class JtMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtMemberApplication.class, args);

        System.out.println(StringUtils.isEmpty("hello"));
    }

}
