package com.cms.scaffold.common.base;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhangjiaheng
 */
@Getter
@Setter
public class ResponseModel<T> implements Serializable {

    public enum STATUS_CODE{
        /**
         * 操作失败
         */
        FAIL(-1, "操作失败"),
        /**
         * 操作成功
         */
        SUCCESS(0, "操作成功");

        private Integer code;
        private String message;

        STATUS_CODE(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    private int code;
    private String message;
    private T data;

    public ResponseModel() {
        this(STATUS_CODE.SUCCESS);
    }

    public ResponseModel(T data) {
        this(STATUS_CODE.SUCCESS.code, STATUS_CODE.SUCCESS.message, data);
    }

    public ResponseModel(STATUS_CODE status) {
        this.code = status.code;
        this.message = status.message;
    }

    public ResponseModel(STATUS_CODE status, T data) {
        this.code = status.code;
        this.message = status.message;
        this.data = data;
    }

    public ResponseModel(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseModel successData(Object data) {
        return new ResponseModel<>(data);
    }

    public static ResponseModel errorData(Object data) {
        return new ResponseModel<>(STATUS_CODE.FAIL, data);
    }
}
