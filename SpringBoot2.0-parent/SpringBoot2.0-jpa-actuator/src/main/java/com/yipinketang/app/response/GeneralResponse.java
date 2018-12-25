package com.yipinketang.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the template return of restful api
 * @create by zuo 20181127
 */
@Data//getter setter toString noArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {

    private Integer code;
    private String message;
    private Object data;

}
