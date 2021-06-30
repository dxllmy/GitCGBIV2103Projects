package com.cy.jtsystem;

import com.cy.jtsystem.system.dao.SysNoticesDao;
import com.cy.jtsystem.system.domain.SysNotices;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class SysNoticeDaoTests {

    @Autowired
    private SysNoticesDao sysNoticesDao;

    /**
     * 测试所有数据一共多少个
     */
    @Test
    void testSelectNotices(){
        SysNotices notice = new SysNotices();
        List<SysNotices> noticeList = sysNoticesDao.selectNotices(notice);
        System.out.println(noticeList.size());
    }

    /**
     * 查询指定内容
     */
    @Test
    void testSelectNotices2(){
        SysNotices notice = new SysNotices();
        notice.setTitle("title");
        notice.setType("1");
        List<SysNotices> noticeList = sysNoticesDao.selectNotices(notice);
        System.out.println(noticeList.size());
        for(SysNotices n: noticeList){
            System.out.println(n);
        }
        //JDK8
        //noticeList.forEach(item-> System.out.println(item));
    }

    @Test
    void testSelectId(){
        SysNotices notices = sysNoticesDao.selectById(41L);
        log.info("notices ->{}",notices);
    }

    @Test
    void testInsertNotice(){
        SysNotices notices = new SysNotices();
//        notices.setId(1);
        notices.setTitle("123");
        notices.setType("1");
        int number = sysNoticesDao.insertNotice(notices);
        log.info(" insert notices for {}",number);
    }

    @Test
    void testDelectById(){
        int rows = sysNoticesDao.deleteById(47L);
        log.debug("delete.rows {}",rows);
    }


}
