package com.cy.jtsystem.system.service;

import com.cy.jtsystem.system.domain.SysNotices;

import java.util.List;

/**
 * @author tarena
 */
public interface SysNoticeService {
    List<SysNotices> selectNotices(SysNotices notices);
    SysNotices selectById(Long id);
    int insertNotice(SysNotices notices);
    int updateNotice(SysNotices notices);
    int deleteById(Long ...id);

}
