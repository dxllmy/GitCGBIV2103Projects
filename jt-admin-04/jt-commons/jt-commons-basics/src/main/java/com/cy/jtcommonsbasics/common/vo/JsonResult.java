package com.cy.jtcommonsbasics.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tarena
 * 通了Feign之后需要调用无参构造，所以需要以后最好把@Data，
 * //@Accessors(chain = true)，//@AllArgsConstructor，
 * //@NoArgsConstructor
 * 这些注解都写上
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
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
