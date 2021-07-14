package com.cy.jtcommonsbasics.common.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tarena
 *  定义一个用于描述方法执行时记录日志的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredLog {
    //基于此属性定义操作名
    String operation() default "";
}
