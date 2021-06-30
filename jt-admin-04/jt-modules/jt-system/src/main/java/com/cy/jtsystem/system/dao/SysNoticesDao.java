package com.cy.jtsystem.system.dao;



import com.cy.jtsystem.system.domain.SysNotices;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tarena
 * mybatis写法，需要绑定xml文件
 */
@Mapper
public interface SysNoticesDao {

    /**
     *
     * @param id
     * @return
     */
    int deleteById(Long ...id);

    /**
     *
     * @param notice
     * @return
     */
    int insertNotice(SysNotices notice);

    /**
     *
     * @param notice
     * @return
     */
    int updateNotice(SysNotices notice);

    /**
     *
     * @param id
     * @return
     */
    SysNotices selectById(Long id);

    /**
     * 基于条件查询公告信息
     * @param notice 基于此对象封装查询参数
     * @return 查询到的公告列表数据
     */
  List<SysNotices> selectNotices(SysNotices notice);



    //    int deleteByPrimaryKey(Integer id);
//
//    int insert(SysNotices record);
//
//    int insertSelective(SysNotices record);
//
//    SysNotices selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(SysNotices record);
//
//    int updateByPrimaryKey(SysNotices record);
}