package com.cy.jtsystem.system.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author tarena
 */
@Aspect
@Component
public class SysTimeAspect {

    @Pointcut("@annotation(com.cy.jtcommonsbasics.common.annotaion.RequiredTime)")
    public void doTime(){}

    @Before("doTime()")
    public void doBefore(){//方法参数可以为JoinPoint joinPoint
        System.out.println("@Before");
    }
    @After("doTime()")
    public void doAfter(){
        System.out.println("@After");
    }
    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("@AfterReturning");
    }
    @AfterThrowing("doTime()")
    public void doAfterThrowing(){
        System.out.println("@AfterThrowing");
    }
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint joinPoint)
            throws Throwable{
        System.out.println("@Around.Before");
        try {
         /*执行proceed()方法时
           1)首先检测本类中是否由@Before通知,有则先执行@Before
           2)其次,假如没有@Before,会执行下一个切面的通知方法(@Around->...)
           3)最后,执行目标方法
         */
            Object result = joinPoint.proceed();//-->Other Advice-->Aspect-->target Method
            System.out.println("@Around.AfterReturning");
            return result;
        }catch (Throwable e){
            System.out.println("@Around.AfterThrowing");
            throw e;
        }finally {
            System.out.println("@Around.after");
        }
    }
}
