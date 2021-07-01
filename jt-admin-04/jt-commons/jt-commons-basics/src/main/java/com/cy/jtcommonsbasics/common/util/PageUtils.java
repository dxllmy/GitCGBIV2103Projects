package com.cy.jtcommonsbasics.common.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tarena
 * 分页功能封装
 */
public class PageUtils {
    public static <T>Page<T> startPage(){
        //spring中获取request对象
        //路径中我们输入的所有参数都会封装在RequestContextHolder里
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();

        //基于请求对象获取请求中的参数(....)
        String pageCurrentStr = request.getParameter("pageCurrent");
        String pageSizeStr = request.getParameter("pageSize");

        Integer pageCurrent =Integer.parseInt(pageCurrentStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);

        return PageHelper.startPage(pageCurrent,pageSize);
    }
}
