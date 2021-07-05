package com.cy.scagateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.awt.image.DataBufferFloat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tarena
 * 认证过滤器，主要用于检查请求是否已认证
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    /**
     * 通过filter方法处理客户端的请求
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //获取请求参数
        MultiValueMap<String,String> querryParams = request.getQueryParams();
        String tokenValue=querryParams.getFirst("token");
        System.out.println("tokenValue="+tokenValue);
        //检查请求参数中是否有token,token的值是否为admin
        if(tokenValue==null||!"admin".equals(tokenValue)){
            ServerHttpResponse response = exchange.getResponse();
            //设置还没有认证的状态吗
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //return response.setComplete();
            //如下方式可以输出更加详细的响应信息
            Map<String,String> result = new HashMap<>();
            result.put("code", "401");
            result.put("msg","please login");
            return response.writeWith(Mono.fromSupplier(()->{
                DataBufferFactory bufferFactory = response.bufferFactory();
               try{
                   return bufferFactory.wrap(new ObjectMapper().writeValueAsBytes(result));
               }catch (JsonProcessingException e){
                   e.printStackTrace();
                   return null;
               }

            }));

        }
        //假如已认证则执行下一步操作
        System.out.println("===AuthGlobalFilter.filter===");
        //执行过滤链中的下一个过滤器
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
