package com.vbrug.workflow.core.persistence.instance.task.entity;

/**
 * 任务值传递对象
 * @author vbrug
 * @since 1.0.0
 */
public class TaskVO {

    private Long      id;                                // 任务ID
    private Long      jobId;                             // 作业ID
    private Integer   nodeId;                            // 节点ID
    private Long      parentId;                          // 父任务编号
    private Integer   state;                             // 状态（0-执行中，1-成功，9-失败）
    private boolean   unfinishedTask;                    // 状态（0-执行中，1-成功，9-失败）
    private String    params;                            // 任务参数
    private Integer   retryTimes;                        // 重试次数
    private Integer[] nodeIds;                           // 节点数组
    private boolean   updEndTime;                        // 更新结束时间

    private TaskVO() {}

    public static TaskVO newInstance() {
        return new TaskVO();
    }

    public boolean isUpdEndTime() {
        return updEndTime;
    }

    public TaskVO setUpdEndTime(boolean updEndTime) {
        this.updEndTime = updEndTime;
        return this;
    }

    public boolean isUnfinishedTask() {
        return unfinishedTask;
    }

    public TaskVO setUnfinishedTask(boolean unfinishedTask) {
        this.unfinishedTask = unfinishedTask;
        return this;
    }

    public Integer[] getNodeIds() {
        return nodeIds;
    }

    public TaskVO setNodeIds(Integer[] nodeIds) {
        this.nodeIds = nodeIds;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TaskVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getJobId() {
        return jobId;
    }

    public TaskVO setJobId(Long jobId) {
        this.jobId = jobId;
        return this;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public TaskVO setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public TaskVO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public TaskVO setState(Integer state) {
        this.state = state;
        return this;
    }

    public String getParams() {
        return params;
    }

    public TaskVO setParams(String params) {
        this.params = params;
        return this;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public TaskVO setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }
}
