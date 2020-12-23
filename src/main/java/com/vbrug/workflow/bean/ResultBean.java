package com.vbrug.workflow.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 返回数据Bean
 *
 * @author vbrug
 * @since 1.0.0
 */
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 4932690413041014964L;
    private Integer status;						                // 状态码
    private String message;						                // 消息
    private Map<String, Object> result;                         // 结果数据
    private T t;                                                // 泛化
    private String remark;						                // 备注

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
