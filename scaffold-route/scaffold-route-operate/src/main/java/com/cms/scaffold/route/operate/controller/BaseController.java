package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author zhangjiaheng
 * @Description 控制类基类
 **/
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * @return 操作成功
     */
    public ResponseModel success(){
        return new ResponseModel();
    }

    /**
     * @return 操作失败
     */
    public ResponseModel error(){
        return new ResponseModel(ResponseModel.STATUS_CODE.FAIL);
    }
}
