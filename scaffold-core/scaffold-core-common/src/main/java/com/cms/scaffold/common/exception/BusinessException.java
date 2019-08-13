package com.cms.scaffold.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.cms.scaffold.common.base.BaseException;
import com.cms.scaffold.common.base.BaseStatusCode;

/**
 * 业务异常类
 *
 * @author zhang
 */
public class BusinessException extends BaseException {
    public BusinessException(BaseStatusCode code) {
        super(code);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this.getStackTrace()[0]);
    }
}
