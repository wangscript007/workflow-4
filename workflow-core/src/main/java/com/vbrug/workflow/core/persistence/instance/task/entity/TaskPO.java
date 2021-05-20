package com.vbrug.workflow.core.persistence.instance.task.entity;

import java.util.Date;

/**
 * 任务持久化对象
 * @author vbrug
 * @since 1.0.0
 */
public class TaskPO {

    private long      id;                                // 任务ID
    private long      jobId;                             // 作业ID
    private Integer   nodeId;                            // 节点ID
    private long      parentId;                            // 父任务编号
    private Integer   state;                             // 状态（0-执行中，1-成功，9-失败）
    private Date startTime;                          // 开始时间
    private Date endTime;                         // 结束时间
    private String    params;                             // 任务参数
    private Integer   retryTimes;                        // 重试次数
    private String    remark;                             // 备注
    private Date updateTime;                         // 更新时间

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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
