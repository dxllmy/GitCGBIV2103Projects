package com.cy.jtsystem.system.service.impl;

import com.cy.jtcommonsbasics.common.domain.SysLogs;
import com.cy.jtsystem.system.dao.SysLogsDao;
import com.cy.jtsystem.system.service.SysLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tarena
 */
@Service
public class SysLogsServiceImpl implements SysLogsService {

    @Autowired
    private SysLogsDao sysLogsDao;

//    @Override
//    @Async
//    public void insertLog(SysLogs record) {
//         sysLogsDao.insertLog(record);
//    }


    @Override
    public List<SysLogs> selectLogs(SysLogs sysLog) {
        return sysLogsDao.selectLogs(sysLog);
    }

    @Override
    public SysLogs selectById(Long id) {
        return sysLogsDao.selectById(id);
    }


    @Async
    @Override
    public void insertLog(SysLogs sysLog) {
//        try{
//            Thread.sleep(10000);
            sysLogsDao.insertLog(sysLog);
//        }catch(Exception e){
//            e.printStackTrace();
//        }

    }

    @Override
    public int deleteById(Long... id) {
        return sysLogsDao.deleteById(id);
    }

}
