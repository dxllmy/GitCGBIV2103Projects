package com.cy.jtsystem.system.service.impl;

import com.cy.jtcommonsbasics.common.domain.SysLogs;
import com.cy.jtsystem.system.dao.SysLogsDao;
import com.cy.jtsystem.system.service.SysLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author tarena
 */
@Service
public class SysLogsServiceImpl implements SysLogsService {

    @Autowired
    private SysLogsDao sysLogsDao;

    @Override
    @Async
    public void insert(SysLogs record) {
         sysLogsDao.insert(record);
    }
}
