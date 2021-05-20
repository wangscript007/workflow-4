package com.vbrug.workflow.core.persistence.instance.job.entity;

/**
 * 作业值传递对象
 * @author vbrug
 * @since 1.0.0
 */
public class JobVO {

    private long    id;                                // 作业ID
    private int     processId;                         // 流程ID
    private int     state;                             // 状态（0-执行中，1-成功，9-失败）
    private boolean updStartTime;                       // 更新开始时间
    private boolean updEndTime;                       // 更新结束时间
    private String  params;                             // 参数
    private String  remark;                             // 备注

    private JobVO() {}

    public static JobVO newInstance() {
        return new JobVO();
    }

    public boolean isUpdStartTime() {
        return updStartTime;
    }

    public JobVO setUpdStartTime(boolean updStartTime) {
        this.updStartTime = updStartTime;
        return this;
    }

    public boolean isUpdEndTime() {
        return updEndTime;
    }

    public JobVO setUpdEndTime(boolean updEndTime) {
        this.updEndTime = updEndTime;
        return this;
    }

    public long getId() {
        return id;
    }

    public JobVO setId(long id) {
        this.id = id;
        return this;
    }

    public int getProcessId() {
        return processId;
    }

    public JobVO setProcessId(int processId) {
        this.processId = processId;
        return this;
    }

    public int getState() {
        return state;
    }

    public JobVO setState(int state) {
        this.state = state;
        return this;
    }

    public String getParams() {
        return params;
    }

    public JobVO setParams(String params) {
        this.params = params;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public JobVO setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
