package com.cy.scagateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tarena
 * 定义配置类，设计流控返回值
 */
@Configuration
public class GatewayConfig {
    public GatewayConfig(){
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(
                    ServerWebExchange serverWebExchange,
                    Throwable throwable) {
                //String json="{\"code\":429,\"msg\":\"too many request\"}";
                Map<String,Object> map=new HashMap<>();
                map.put("code",429);
                map.put("msg","too many request");
                String json= null;
//                try {
//                    json = new ObjectMapper().writeValueAsString(map);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                    json="{\"code\":429,\"msg\":\"too many request\"}";
//                }
                //实际项目中将对象转换为json时,通常有三种方案:jackson,fastjson,Gson

                // json= JSON.toJSONString(map);//alibaba->fastjson

                json=new Gson().toJson(map);//google-Gson

                return ServerResponse.ok()
                        .body(Mono.just(json),String.class);
            }
        });
    }
}

