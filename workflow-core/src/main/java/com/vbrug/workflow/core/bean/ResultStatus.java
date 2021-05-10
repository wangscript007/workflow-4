package com.vbrug.workflow.core.bean;

/**
 * @author vbrug
 * @since 1.0.0
 */
public enum ResultStatus {

    SUCCESS(1, "成功"),
    FINISH(100, "作业完成"),
    EXCEPTION(999, "发生异常");

    private final Integer status;                                        // 状态码
    private final String  message;                                        // 消息

    ResultStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
