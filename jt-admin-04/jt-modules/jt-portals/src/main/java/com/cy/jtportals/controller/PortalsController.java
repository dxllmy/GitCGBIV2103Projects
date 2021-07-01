package com.cy.jtportals.controller;

import com.cy.jtcommonsbasics.common.domain.SysNotices;
import com.cy.jtportals.service.feign.RemoteNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tarena
 */
@RestController

public class PortalsController {

    @Autowired
    private RemoteNoticeService remoteNoticeService;

    @GetMapping("/portal/doSelectNotices")
    public Object doSelectNotices(SysNotices notice){
        return remoteNoticeService.doSelectNotices(notice);
    }
}
