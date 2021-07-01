package com.cy.jtcommonsbasics.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tarena
 */
@Data
@Accessors
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -362993439657980400L;
    /**
     * 响应状态码
     */
    private int code=1;
    /**
     * 具体消息
     */
    private String message="ok";
    /**
     * 一般对应查询结果
     */
    private Object data;

    public JsonResult(String message){
        this.message=message;
    }
    public JsonResult(Object data){
        this.data = data;
    }
    public JsonResult(Throwable e){
        this.code=0;
        this.message=e.getMessage();
    }
}
