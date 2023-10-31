package edu.whu.demo.exception;

import lombok.Data;

@Data
public class ProductAdminException extends Exception{
    // 定义各种错误代码常量
    public final static int INPUT_ERROR = 100;
    public final static int EXIST_ERROR = 101;
    public final static int DEFAULT_ERROR = 101;

    int code; // 自定义的错误代码

    public ProductAdminException(int code, String message){
        super(message);
        this.code = code;
    }
}
