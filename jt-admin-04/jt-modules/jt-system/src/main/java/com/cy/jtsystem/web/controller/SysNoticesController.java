package com.cy.jtsystem.web.controller;

import com.cy.jtsystem.system.domain.SysNotices;
import com.cy.jtsystem.system.service.SysNoticeService;
import com.cy.jtsystem.web.util.PageUtils;
import com.cy.jtsystem.web.vo.JsonResult;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tarena
 */
@RestController
@RequestMapping("/notice")
public class SysNoticesController {

    @Autowired
    private SysNoticeService sysNoticeService;

//    @GetMapping("/doSelectNotices")
//    public JsonResult doSelectNotices(SysNotices sysNotices){
//        return new JsonResult(sysNoticeService.selectNotices(sysNotices));
//    }

    @GetMapping("/doSelectNotices")
    public JsonResult doSelectNotices(SysNotices sysNotices){
        //原来的写法
        //        return new JsonResult(sysNoticeService.selectNotices(sysNotices));

        //普通分页写法，但是因为要在每个需要用分页的方法里写，很麻烦，所以封装
//        return new JsonResult(PageHelper.startPage(1,2).doSelectPageInfo(new ISelect() {
//             @Override
//             public void doSelect() {
//                 sysNoticeService.selectNotices(sysNotice);
//             }
//         }));

        //上面分页的简化写法就是如下方式
        return new JsonResult(PageUtils.startPage()//启动分页拦截器
                .doSelectPageInfo(()->{//执行查询业务
                    sysNoticeService.selectNotices(sysNotices); }));
    }

    @GetMapping("/doSelectById/{id}")
    public JsonResult doSelectById(@PathVariable Long id){
        return new JsonResult(sysNoticeService.selectById(id));
    }

    /**
     * 利用service层的返回值，就要这么写，返回1代表成功，返回0代表错误
     * @param sysNotices
     * @return
     */
    @PostMapping("/insertNotices")
    public JsonResult insertNotices(@RequestBody SysNotices sysNotices){
        if(sysNoticeService.insertNotice(sysNotices)==1){
            return new JsonResult("insert of");
        }
        return new JsonResult("添加失败");


    }

    @PutMapping("/updateNotices")
    public JsonResult updateNotices(@RequestBody SysNotices sysNotices){
        sysNoticeService.updateNotice(sysNotices);
        return new JsonResult("update ok");
    }

    @DeleteMapping("/deleteNotices/{id}")
    public JsonResult deleteNotices(@PathVariable Long ...id){
        sysNoticeService.deleteById(id);
        return new JsonResult("delete ok");
    }
}