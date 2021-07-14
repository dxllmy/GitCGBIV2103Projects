package com.cy.jtsystem.system.service.aspect;

import com.cy.jtcommonsbasics.common.annotaion.RequiredLog;
import com.cy.jtcommonsbasics.common.domain.SysLogs;
import com.cy.jtsystem.system.service.SysLogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


/**
 * @author tarena
 * @EnableAsync 开启异步
 * Spring AOP 中基于@Aspect描述切面类型，切面类型中可以定义多个
 * 切入点和通知方法
 * （1）切入点：要执行扩展业务的一些方法集合（这些方法通常会认为是切入点方法）
 * （2）通知方法：用于封装扩展业务逻辑的方法
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
@EnableAsync
public class SysLogAspect {

    @Autowired
    private SysLogsService sysLogsService;

    private static final Logger log =
            LoggerFactory.getLogger(SysLogAspect.class);

    /**
     * @Pointcut 注解用于定义切入点,@annotation为一个切入点表达式,表达式中注解
     * 描述的方法为切入点方法
     */
    @Pointcut("@annotation(com.cy.jtcommonsbasics.common.annotaion.RequiredLog)")
    public void doLog(){}//doLog方法没有意义,只是用于承载上面的注解

    /**
     * Around 注解 描述的方法为通知方法,此方法内部可以直接调用目标方法(就是要织入
     * 扩展功能的方法),并且可以在目标方法执行前后添加扩展业务
     */
    @Around("doLog()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
//        System.out.println("around.before");
//        //去执行目标方法
//        Object result=joinPoint.proceed();
//        System.out.println("around.after");
//        return result;

        //获取方法签名(名片)
        MethodSignature methodSignature =
                (MethodSignature)joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        String targetMethodName = className+"."+methodName;
        long t1 = System.currentTimeMillis();
        log.debug("around.before {}",t1);

        try {
            //执行目标方法(切点方法中的某个方法)
            Object result = joinPoint.proceed();
            long t2 = System.currentTimeMillis();
            log.debug("around.after {}", t2);
            log.debug("opertime:{}",t2-t1);
            doLogInfo(joinPoint,t2-t1,null);
            //目标业务方法的执行结果
            return result;
        }catch(Throwable e){
            e.printStackTrace();
            long t2 = System.currentTimeMillis();
            log.info("exception:{}",e.getMessage());
            doLogInfo(joinPoint,t2-t1,e);
            throw e;
        }

    }

    //记录用户行为日志
    private void doLogInfo(ProceedingJoinPoint joinPoint,
                           long time,Throwable e)
        throws Exception{
        //1.获取用户行为日志
        //1.1获取登录用户名(没做登录时，可以先给个固定值)
        String username="cgb";
        //1.2 获取ip地址
        String ip = "202.106.0.20";
        //1.3 获取操作名（operation）-@RequiredLog注解中value属性的值
        //1.3.1获取目标对象类型
        Class<?> targetCls = joinPoint.getTarget().getClass();
        //1.3.2获取目标方法
        //方法签名
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method targetMethod =
                targetCls.getMethod(ms.getName(),ms.getParameterTypes());
        //1.3.3 获取方法上RequiredLog 注解
        RequiredLog annotation = targetMethod.getAnnotation(RequiredLog.class);
        //1.3.4 获取注解中定义的操作名
        String operation = annotation.operation();
        //1.4 获取方法声明（类全名+方法名）
        String classMethodName = targetCls.getName()+"."+targetMethod.getName();
        //1.5 获取方法实际参数信息
        Object[] args = joinPoint.getArgs();
        String params = new ObjectMapper().writeValueAsString(args);
        //2.封装用户行为日志
        SysLogs sysLogs=new SysLogs();
        sysLogs.setUsername(username);
        sysLogs.setIp(ip);
        sysLogs.setOperation(operation);
        sysLogs.setMethod(classMethodName);
        sysLogs.setParams(params);
        sysLogs.setTime(time);
        sysLogs.setStatus(1);
        if(e!=null) {
            sysLogs.setStatus(0);
            sysLogs.setError(e.getMessage());
        }

        //3.打印日志
        String userLog = new ObjectMapper().writeValueAsString(sysLogs);
        log.info("user.oper {}" , userLog);

        sysLogsService.insert(sysLogs);


    }
}
