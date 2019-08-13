package com.cms.scaffold.common.response;


import com.cms.scaffold.common.base.BaseStatusCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangjiaheng
 */
@Getter
@Setter
@ToString
public class ResponseModel<T> implements Serializable {

    private int statusCode;
    private String message;
    private String title;
    private T data;

    public ResponseModel() {
        this(BaseStatusCode.SUCCESS);
    }

    public ResponseModel(T data) {
        this(BaseStatusCode.SUCCESS.getCode(), BaseStatusCode.SUCCESS.getMessage(), data);
    }

    public ResponseModel(BaseStatusCode status) {
        this.statusCode = status.getCode();
        this.message = status.getMessage();
    }

    public ResponseModel(BaseStatusCode status, T data) {
        this.statusCode = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public ResponseModel(BaseStatusCode status, String title, T data) {
        this.statusCode = status.getCode();
        this.message = status.getMessage();
        this.data = data;
        this.title = title;
    }

    public ResponseModel(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ResponseModel(int statusCode, String message, String title, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.title = title;
    }

    public static ResponseModel successData(Object data) {
        return new ResponseModel<>(data);
    }

    public static ResponseModel errorData(Object data) {
        return new ResponseModel<>(BaseStatusCode.FAIL, data);
    }
}
