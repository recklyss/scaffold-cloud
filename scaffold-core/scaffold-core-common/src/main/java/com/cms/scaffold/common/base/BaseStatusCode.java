package com.cms.scaffold.common.base;

public enum BaseStatusCode {
    /**
     * 操作失败
     */
    FAIL(300, "操作失败"),

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 未知异常
     */
    UNKNOW_ERROR(-100, "未知异常"),

    /**
     * 参数异常（参数不正确、不符合格式要求等）
     */
    PARAM_ERROR(-101, "参数异常"),

    /**
     * 父级ID不一致
     */
    DICT_PID_ERROR(-102, "父级ID不一致");

    BaseStatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param code 根据code返回合适的错误信息枚举
     * @return 枚举
     */
    public BaseStatusCode getStatusCodeInfo(Integer code) {
        for (BaseStatusCode statusCode : BaseStatusCode.values()) {
            if (statusCode.getCode().equals(code)) {
                return statusCode;
            }
        }
        return UNKNOW_ERROR;
    }
}
