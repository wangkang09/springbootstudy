package com.wangkang.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 22:00 2018/12/25
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends Exception {

    private Integer code;
    private String message;

}
