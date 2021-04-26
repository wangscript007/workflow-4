package com.vbrug.workflow.core.persistence.instance.job.po;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class JobPO {

    private Integer id;                                // 作业ID
    private Integer processId;                         // 流程ID
    private Integer state;                             // 状态（0-执行中，1-成功，9-失败）
    private String startTime;                          // 开始时间
    private String endTime;                            // 结束时间
    private Integer retryTimes;                        // 重试次数
    private String remark;                             // 备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
