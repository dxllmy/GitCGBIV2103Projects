package com.cy.scaconsumer.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author tarena
 */
public class TimeInterceptor implements HandlerInterceptor {
    /**此方法是在@Controller对象方法执行之前执行*/
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        LocalDateTime time=LocalDateTime.now();
        int hour=time.getHour();
        System.out.println("hour="+hour);
        if(hour<6||hour>23)
            throw new RuntimeException("请在指定时间访问");
        return true;//true表示放行
    }
}
