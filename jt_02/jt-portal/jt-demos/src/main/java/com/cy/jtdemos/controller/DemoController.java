package com.cy.jtdemos.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author tarena
 * RestController=@Controller+@RequsetBody
 * Integer ...id 可变参数
 * 网页显示404：路径找不到。显示405，类型不匹配。
 *
 * PatchMapping注释 更新部分数据
 */
@RestController
@RequestMapping("/demo/")
public class DemoController {

    Integer value;

    @PutMapping
    public String doUpdate(@RequestBody Map<String,Object> map){
        value++;
        System.out.println(value);
        return map.toString()+" is updated";
    }

    @PostMapping
    public String doCreate(@RequestBody Map<String,Object> map){
        return map.toString()+" is created";
    }

    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Integer ...id){
        return Arrays.toString(id)+"is deleted";
    }

    private int count;
    @GetMapping("{id}")
    public String doFindById(@PathVariable Integer id){
        String tName = Thread.currentThread().getName();
        return tName+" find result by "+ id;
    }
}
