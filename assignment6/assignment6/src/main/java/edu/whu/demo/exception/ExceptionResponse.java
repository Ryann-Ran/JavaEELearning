package edu.whu.demo.exception;

import lombok.Data;

/**
 * @author Ryann
 * @create 2023/10/31 - 12:33
 */
@Data
public class ExceptionResponse {
    private Integer code;
    private String message;
}
