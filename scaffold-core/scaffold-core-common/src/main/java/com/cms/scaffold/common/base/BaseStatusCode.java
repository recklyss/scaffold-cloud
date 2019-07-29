package com.cms.scaffold.common.base;

public enum BaseStatusCode {
    /**
     * 操作失败
     */
    FAIL(-1, "操作失败"),

    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),

    /**
     * 未知异常
     */
    UNKNOW_ERROR(-99, "未知异常");

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
