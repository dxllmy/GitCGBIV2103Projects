package com.cy.scaprovider.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tarena
 */
@RestController
@RequestMapping("/provider/")
public class RestProviderController {
    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Long id){
        System.out.println("provider.id="+id);
        return id +" is deleted";
    }
    //添加操作
    @PostMapping
    public Map<String,Object> doCreate(@RequestBody  Map<String,Object> map){
        //....后续要将请求数据写入到数据库
        System.out.println("provider.map="+map);
        Map<String,Object> responseMap=new HashMap<>();
        responseMap.put("status",200);
        responseMap.put("message","insert ok");
        return responseMap;
    }

}
