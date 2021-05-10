package com.vbrug.workflow.core.persistence.instance.task.po;

/**
 * 任务持久化对象
 * @author vbrug
 * @since 1.0.0
 */
public class TaskPO {

    private long    id;                                // 任务ID
    private long    jobId;                             // 作业ID
    private Integer nodeId;                            // 节点ID
    private Integer childNodeId;                            // 子节点
    private Integer state;                             // 状态（0-执行中，1-成功，9-失败）
    private String  startTime;                          // 开始时间
    private String  endTime;                         // 结束时间
    private String  params;                             // 任务参数
    private Integer retryTimes;                        // 重试次数
    private String  remark;                             // 备注

    public Integer getChildNodeId() {
        return childNodeId;
    }

    public void setChildNodeId(Integer childNodeId) {
        this.childNodeId = childNodeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
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
