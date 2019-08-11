package com.cms.scaffold.common.response;


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

    @Getter
    public enum STATUS_CODE {
        /**
         * 操作失败
         */
        FAIL(300, "操作失败", "系统错误"),
        /**
         * 操作成功
         */
        SUCCESS(200, "操作成功", "操作成功");

        private Integer statusCode;
        private String title;
        private String message;

        STATUS_CODE(int statusCode, String title, String message) {
            this.statusCode = statusCode;
            this.message = message;
            this.title = title;
        }
    }

    private int statusCode;
    private String message;
    private String title;
    private T data;

    public ResponseModel() {
        this(STATUS_CODE.SUCCESS);
    }

    public ResponseModel(T data) {
        this(STATUS_CODE.SUCCESS.statusCode, STATUS_CODE.SUCCESS.message, data);
    }

    public ResponseModel(STATUS_CODE status) {
        this.statusCode = status.statusCode;
        this.message = status.message;
    }

    public ResponseModel(STATUS_CODE status, T data) {
        this.statusCode = status.statusCode;
        this.message = status.message;
        this.data = data;
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
        return new ResponseModel<>(STATUS_CODE.FAIL, data);
    }
}
