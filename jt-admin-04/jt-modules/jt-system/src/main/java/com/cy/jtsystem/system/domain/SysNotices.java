package com.cy.jtsystem.system.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 公告领域对象
 * 公告領域對象,与表中字段有对应关系,可以基于此对象
 * 存储从数据库查询到的数据.
 * 记住:java中所有用于存储数据的对象都让它实现Serializable接口,
 * 并且添加序列化id
 * sys_notices
 * @author LMY
 */
@Data
public class SysNotices implements Serializable {
    private static final long serialVersionUID = 4750490924083941958L;
    /**
     * ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型（1 通知 2 公告）
     */
    private String type;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 状态（0 正常 1 关闭）
     */
    private String status;

    /**
     * 创建者
     */
    private String createdUser;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新者
     */
    private String modifiedUser;

    /**
     * 更新时间
     */
    private Date modifiedTime;

    /**
     * 备注
     */
    private String remark;

//    private static final long serialVersionUID = 1L;
}