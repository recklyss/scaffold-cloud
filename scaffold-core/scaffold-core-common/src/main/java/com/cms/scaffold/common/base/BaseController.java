package com.cms.scaffold.common.base;

import com.cms.scaffold.common.response.ResponseModel;

import java.util.List;

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
        return new ResponseModel<>(BaseStatusCode.FAIL);
    }

    /**
     * 返回错误信息
     *
     * @param message
     * @return
     */
    public ResponseModel<T> errorMessage(String message, String title) {
        return new ResponseModel<>(BaseStatusCode.FAIL.getCode(), message, title, null);
    }

    public ResponseModel<T> errorMessage(String message) {
        return new ResponseModel<>(BaseStatusCode.FAIL.getCode(), message, null);
    }

    /**
     * @param data 返回的数据
     * @return 成功
     */
    public ResponseModel<T> successData(T data) {
        return new ResponseModel<>(data);
    }

    /**
     * 返回列表数据
     *
     * @param data
     * @return
     */
    public ResponseModel<List<T>> successData(List<T> data) {
        return new ResponseModel<>(data);
    }
}
