package com.cms.scaffold.code.mq.model.sys;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.code.mq.model.MqBaseModel;
import com.cms.scaffold.common.constant.MqConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhang
 * 系统操作日志记录mq model
 */
@Getter
@Setter
public class SysOperateLogMqModel extends MqBaseModel {
    private SysOperateLogModel sysOperateLogModel;
    public static final String SPRING_BEAN = "sysOperateLogService";
    public static final String METHOD_NAME = "insert";

    public SysOperateLogMqModel(SysOperateLogModel sysOperateLogModel) {
        super(MqConstant.ROCKETMQ_TAG_OPERATE_LOG, SPRING_BEAN, METHOD_NAME);
        this.sysOperateLogModel = sysOperateLogModel;
        this.requestNo = StrUtil.uuid();
    }

    @Override
    public Object[] getObjs() {
        return new Object[]{sysOperateLogModel};
    }
}
