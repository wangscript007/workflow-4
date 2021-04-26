package com.vbrug.workflow.core.persistence.instance.task.po;

/**
 * 任务持久化对象
 * @author vbrug
 * @since 1.0.0
 */
public class TaskPO {

    private Integer jobId;                             // 作业ID
    private Integer processId;                         // 流程ID
    private Integer nodeId;                            // 节点ID
    private Integer state;                             // 状态（0-执行中，1-成功，9-失败）
    private String  startTime;                         // 开始时间
    private String  updateTime;                        // 更新时间
    private Integer retryTimes;                        // 重试次数
    private String  remark;                            // 备注

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
