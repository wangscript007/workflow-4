package com.vbrug.workflow.core.persistence.instance.job.entity;

import java.util.Date;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class JobPO {

    private long   id;                                // 作业ID
    private int    processId;                         // 流程ID
    private int    state;                             // 状态（0-执行中，1-成功，9-失败）
    private Date   createTime;                         // 创建时间
    private Date   startTime;                          // 执行开始时间
    private Date   endTime;                            // 结束时间
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
