package com.cms.scaffold.common.exception;

import com.cms.scaffold.common.base.BaseException;
import com.cms.scaffold.common.base.BaseStatusCode;
import lombok.ToString;

/**
 * 业务异常类
 *
 * @author zhang
 */
@ToString
public class BusinessException extends BaseException {
    public BusinessException(BaseStatusCode code) {
        super(code);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }
}
