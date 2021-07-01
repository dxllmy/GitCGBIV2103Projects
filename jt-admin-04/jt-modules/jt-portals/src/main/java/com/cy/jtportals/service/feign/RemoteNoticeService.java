package com.cy.jtportals.service.feign;

import com.cy.jtcommonsbasics.common.domain.SysNotices;
import com.cy.jtcommonsbasics.common.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tarena
 *
 */
@FeignClient(value = "jt-system",contextId = "remoteNoticeService")
public interface RemoteNoticeService {

    @GetMapping("/notice/doSelectNotices")
    JsonResult doSelectNotices(SysNotices notices);
}
