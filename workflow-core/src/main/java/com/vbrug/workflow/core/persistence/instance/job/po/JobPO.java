package com.vbrug.workflow.core.persistence.instance.job.po;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class JobPO {

    private long   id;                                // 作业ID
    private int    processId;                         // 流程ID
    private int    state;                             // 状态（0-执行中，1-成功，9-失败）
    private String createTime;                         // 创建时间
    private String startTime;                          // 执行开始时间
    private String endTime;                            // 结束时间
    private String params;                             // 参数
    private String remark;                             // 备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
