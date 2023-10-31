package edu.whu.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Ryann
 * @create 2023/10/31 - 12:32
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductAdminException.class)  // 表示处理特定异常TodoException
    public ResponseEntity<ExceptionResponse> handleExceptions(ProductAdminException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(exception.getCode());  // 将TodoException的错误代码设置到ExceptionResponse对象
        response.setMessage(exception.getMessage());// 将TodoException的错误消息设置到ExceptionResponse对象
        ResponseEntity<ExceptionResponse> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);  // 将ExceptionResponse对象包装成HTTP响应。将response作为响应体，HTTP状态码设置为HttpStatus.BAD_REQUEST，意味着客户端会收到一个HTTP 400 Bad Request响应。
        return entity;
    }
}
