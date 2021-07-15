package com.cy.jtsystem.system.dao;

import com.cy.jtcommonsbasics.common.domain.SysLogs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tarena
 */
@Mapper
public interface SysLogsDao {
//    int insert(SysLogs record);
    /**
     * 基于条件查询用户行为日志
     * @param sysLog 封装了查询条件的对象
     * @return 返回查询到的日志信息
     */
    List<SysLogs> selectLogs(SysLogs sysLog);

    /**
     * 基于id查询日志记录详情
     * @param id 日志记录id
     * @return 查询到的日志
     */
    SysLogs selectById(Long id);

    /**
     * 新增用户行为日志
     * @param sysLog 封装了用户日志信息的对象
     * @return 新增的行数
     */
    int insertLog(SysLogs sysLog);

    /**
     * 基于id删除日志记录
     * @param id
     * @return
     */
    int deleteById(Long... id);//array

}