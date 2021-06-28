package com.cy.scaconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tarena
 * //@FeignClient 描述接口时,用于告诉spring要为此接口创建实现类
 */
@FeignClient(value="sca-provider",contextId = "remoteProviderService")
public interface RemoteProviderService {

    @GetMapping("/provider/echo/{msg}")
    String echoMessage(@PathVariable("msg") String msg);
}
