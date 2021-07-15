package com.cy.jtsystem.web.controller;

import com.cy.jtcommonsbasics.common.annotaion.RequiredTime;
import com.cy.jtcommonsbasics.common.domain.SysLogs;
import com.cy.jtsystem.system.service.SysLogsService;
import com.cy.jtsystem.web.util.PageUtils;
import com.cy.jtsystem.web.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tarena
 */
@RestController
@RequestMapping("/log/")
public class SysLogController {
    @Autowired
    private SysLogsService sysLogsService;

    @RequiredTime
    @GetMapping("{id}")
    public JsonResult doSelectById (@PathVariable  Long id) {
        return new JsonResult(sysLogsService.selectById(id));
    }

    @DeleteMapping("{ids}")
    public JsonResult doDeleteById (Long...ids) {
        sysLogsService.deleteById(ids);
        return new JsonResult("delete ok");
    }

    @GetMapping
    public JsonResult doSelectLogs(SysLogs sysLog){
        return new JsonResult(
                //启动分页查询拦截
                PageUtils.startPage()
                        //分页查询日志信息
                        .doSelectPageInfo(()->
                                sysLogsService.selectLogs(sysLog)
                        ));
    }

}
