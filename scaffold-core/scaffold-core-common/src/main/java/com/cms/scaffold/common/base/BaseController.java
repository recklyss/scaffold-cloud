package com.cms.scaffold.common.base;

import com.cms.scaffold.common.response.ResponseModel;

/**
 * @Author zhangjiaheng
 * @Description 控制类基类
 **/
public class BaseController<T> {
    /**
     * @return 操作成功
     */
    public ResponseModel<T> success() {
        return new ResponseModel<>();
    }

    /**
     * @return 操作失败
     */
    public ResponseModel<T> error() {
        return new ResponseModel<>(ResponseModel.STATUS_CODE.FAIL);
    }

    /**
     * 返回错误信息
     *
     * @param message
     * @return
     */
    public ResponseModel<T> errorMessage(String message) {
        return new ResponseModel<>(ResponseModel.STATUS_CODE.FAIL.getStatusCode(), message, null);
    }

    /**
     * @param data 返回的数据
     * @return 成功
     */
    public ResponseModel<T> successData(T data) {
        return new ResponseModel<>(data);
    }
}
