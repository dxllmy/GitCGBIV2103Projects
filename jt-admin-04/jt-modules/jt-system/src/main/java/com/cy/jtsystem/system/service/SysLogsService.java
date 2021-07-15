package com.cy.jtsystem.system.service;

import com.cy.jtcommonsbasics.common.domain.SysLogs;

import java.util.List;

/**
 * @author tarena
 */
public interface SysLogsService {
//    void insertLog(SysLogs record);

    /**
     * 基于条件查询日志信息
     * @param sysLog
     * @return
     */
    List<SysLogs> selectLogs(SysLogs sysLog);

    /**
     * 基于id查询日志信息
     * @param id
     * @return
     */
    SysLogs selectById(Long id);

    /**
     * 新增日志信息
     * @param sysLog
     */
    void insertLog(SysLogs sysLog);

    /**
     * 基于id删除日志记录
     * @param id
     * @return
     */
    int deleteById(Long... id);

}
