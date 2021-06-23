package com.cy.jtdemos.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tarena
 */
@Service
public class MemoryService {
        /**
         * 本地缓存(JVM内部的缓存)
         * ConcurrentHashMap 线程安全的HashMap
         */
//        private Map<String,Object> cache=new ConcurrentHashMap<>();


        @Cacheable(cacheNames = {"memoryCache"},key="#root.methodName")
        public List<Map<String,Object>> list(){
            //将来这个key为参数列表的组合
//            if(cache.containsKey("memoryKey")) {
//                return (List<Map<String, Object>>) cache.get("memoryKey");
//            }else{
                System.out.println("Get Data from Database");
                Map<String,Object> m1 = new HashMap<>();
                m1.put("id",100);
                m1.put("title","title-A");
                Map<String,Object> m2 = new HashMap<>();
                m2.put("id",100);
                m2.put("title","title-A");
                List<Map<String,Object>> list = new ArrayList<>();
                list.add(m1);
                list.add(m2);
//                cache.put("memoryKey",list);
                return list;
            }


}
