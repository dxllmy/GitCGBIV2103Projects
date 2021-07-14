package com.cy.jtsystem.system.service.impl;

import com.cy.jtsystem.system.dao.SysNoticesDao;
import com.cy.jtsystem.system.domain.SysNotices;
import com.cy.jtsystem.system.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tarena
 */
@Service
public class SysNoticesServiceImpl implements SysNoticeService {
    @Autowired
    private SysNoticesDao sysNoticesDao;
    @Override
    public List<SysNotices> selectNotices(SysNotices notices) {
        System.out.println("SysNoticeServiceImpl.selectNotices(SysNotice notice)");
        return sysNoticesDao.selectNotices(notices);
    }

    @Override
    public SysNotices selectById(Long id) {
        return sysNoticesDao.selectById(id);
    }

    @Override
    public int insertNotice(SysNotices notices) {
        return sysNoticesDao.insertNotice(notices);
    }

    @Override
    public int updateNotice(SysNotices notices) {
        return sysNoticesDao.updateNotice(notices);
    }

    @Override
    public int deleteById(Long... id) {
        return sysNoticesDao.deleteById(id);
    }

}
