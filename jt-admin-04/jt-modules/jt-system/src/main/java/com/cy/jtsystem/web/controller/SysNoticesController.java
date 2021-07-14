package com.cy.jtsystem.web.controller;

import com.cy.jtcommonsbasics.common.annotaion.RequiredLog;
import com.cy.jtsystem.system.domain.SysNotices;
import com.cy.jtsystem.system.service.SysNoticeService;
import com.cy.jtsystem.web.util.PageUtils;
import com.cy.jtsystem.web.vo.JsonResult;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author tarena
 */
@RestController
@RequestMapping("/notice")
public class SysNoticesController {

    @Autowired
    private SysNoticeService sysNoticeService;

    /**
     * 查询所有数据
     * @Cacheable 这个注解描述的方法,在执行时,系统底层会先去查询缓存，
     * 这里notices表示缓存的名称，不是key
     */
    @RequiredLog(operation="查询通告信息")
    @Cacheable(value = "notices0")
    @GetMapping("/doSelectNotices0")
    public JsonResult doSelectNotices0(SysNotices sysNotices){
        return new JsonResult(sysNoticeService.selectNotices(sysNotices));
    }

    /**
     * 分页查询数据，和查询所有数据用的同一个service层和Dao层
     * Cacheable 这个注解描述的方法,在执行时,系统底层会先去查询缓存，
     * 这里notices表示缓存的名称，不是key
     */
    @RequiredLog(operation="分页查询通告信息")
    @Cacheable(value = "notices")
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

    @RequiredLog(operation="根据id查询通告信息")
    @Cacheable(value = "notices1")
    @GetMapping("/doSelectById/{id}")
    public JsonResult doSelectById(@PathVariable Long id){
        return new JsonResult(sysNoticeService.selectById(id));
    }

    /**
     * 新增数据
     * 利用service层的返回值，就要这么写，返回1代表成功，返回0代表错误
     */
    @RequiredLog(operation="新增通告信息")
    @PostMapping("/insertNotices")
    public JsonResult insertNotices(@RequestBody SysNotices sysNotices){
        if(sysNoticeService.insertNotice(sysNotices)==1){
            return new JsonResult("insert of");
        }
        return new JsonResult("添加失败");


    }

    /**
     * 更新数据
     * @CacheEvict 注解表示清缓存，value的值为缓存名称，
     * allEntries表示清除所有
     * beforeInvocation是在方法执行之前还是之后执行
     */
    @RequiredLog(operation="更新通告信息")
    @CacheEvict(value = "notices2",allEntries = true,beforeInvocation = false)
    @PutMapping("/updateNotices")
    public JsonResult updateNotices(@RequestBody SysNotices sysNotices){
        sysNoticeService.updateNotice(sysNotices);
        return new JsonResult("update ok");
    }

    /**
     * 删除数据
     */
    @RequiredLog(operation="删除通告信息")
    @DeleteMapping("/deleteNotices/{id}")
    public JsonResult deleteNotices(@PathVariable Long ...id){
        sysNoticeService.deleteById(id);
        return new JsonResult("delete ok");
    }
}
