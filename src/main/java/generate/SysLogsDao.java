package generate;

import generate.SysLogs;

public interface SysLogsDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysLogs record);

    int insertSelective(SysLogs record);

    SysLogs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLogs record);

    int updateByPrimaryKey(SysLogs record);
}