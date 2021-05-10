package com.vbrug.workflow.core.bean;

import java.io.Serializable;

/**
 * 返回数据Bean
 * @author vbrug
 * @since 1.0.0
 */
public class Result implements Serializable {

    private static final long    serialVersionUID = 4932690413041014964L;
    private              Integer status;                                         // 状态码
    private              String  message;                                        // 消息
    private              Object  data;                                           // 数据
    private              String  remark;                                         // 备注

    public static Result success() {
        Result result = new Result();
        result.setResultStatus(ResultStatus.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setResultStatus(ResultStatus.SUCCESS);
        return result;
    }

    public static Result failure(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setResultStatus(ResultStatus.SUCCESS);
        return result;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.status = resultStatus.getStatus();
        this.message = resultStatus.getMessage();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
