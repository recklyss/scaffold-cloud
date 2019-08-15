package com.cms.scaffold.code.mq.base;

/**
 * @Author zhangjiaheng
 * @Description mq基础接口
 **/
public interface MqBaseInterface {

    /**
     * 获取类名
     * @return
     */
    public String getSpringBean();

    /**
     * 获取方法名
     * @return
     */
    public String getMethodName();

    /**
     * 获取参数
     * @return
     */
    public Object[] getObjs();
}
