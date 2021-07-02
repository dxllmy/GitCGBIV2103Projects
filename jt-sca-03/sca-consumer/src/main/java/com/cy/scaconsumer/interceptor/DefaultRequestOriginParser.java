package com.cy.scaconsumer.interceptor;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tarena
 */
@Component
public class DefaultRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        String origin = request.getParameter("origin");
        return origin;
    }
}
