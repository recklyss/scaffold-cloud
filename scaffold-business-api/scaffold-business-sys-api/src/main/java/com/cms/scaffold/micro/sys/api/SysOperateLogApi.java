package com.cms.scaffold.micro.sys.api;

import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author zhangjiaheng
*/
public interface SysOperateLogApi{


    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @RequestMapping(value = "/sysOperateLog/selectById", method = RequestMethod.GET)
    ResponseModel selectById(@RequestParam("id") Long id);
}
