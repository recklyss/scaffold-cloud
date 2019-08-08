package com.cms.scaffold.common.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhang
 * 异常 基类
 */
@Getter
@Setter
@ToString
public class BaseException extends RuntimeException {
    private int code;

    private String message;

    public BaseException() {
    }

    public BaseException(BaseStatusCode code) {
        this(code.getCode(), code.getMessage());
    }

    public BaseException(BaseStatusCode code, String message) {
        this(code.getCode(), message);
    }

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
