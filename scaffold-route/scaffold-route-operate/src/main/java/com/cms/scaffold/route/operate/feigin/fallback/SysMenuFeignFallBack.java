package com.cms.scaffold.route.operate.feigin.fallback;

import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.route.operate.feigin.SysMenuFeign;
import org.springframework.stereotype.Component;

@Component
public class SysMenuFeignFallBack implements SysMenuFeign {
    @Override
    public ResponseModel listMenuByPid(Long id) {
        return new ResponseModel<>(ResponseModel.STATUS_CODE.FAIL);
    }
}
