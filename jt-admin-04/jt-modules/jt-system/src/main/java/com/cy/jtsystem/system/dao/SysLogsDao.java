package com.cy.jtsystem.system.dao;

import com.cy.jtcommonsbasics.common.domain.SysLogs;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tarena
 */
@Mapper
public interface SysLogsDao {
    int insert(SysLogs record);
}