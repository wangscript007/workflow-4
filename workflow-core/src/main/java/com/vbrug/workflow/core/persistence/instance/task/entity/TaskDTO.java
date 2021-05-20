package com.vbrug.workflow.core.persistence.instance.task.entity;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class TaskDTO {

    private Long    taskId;                            // 任务ID
    private Long    jobId;                             // 作业ID
    private Integer jobProcessId;                         // 作业工作流ID
    private Long    parentTaskId;                      // 父节点ID
    private Integer nodeId;                            // 节点ID
    private Integer nodeProcessId;                         // 作业工作流ID
    private String  nodeGroup;                         // 分组
    private String  nodeName;                          // 节点名称
    private String  nodeType;                          // 节点类型


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getJobProcessId() {
        return jobProcessId;
    }

    public void setJobProcessId(Integer jobProcessId) {
        this.jobProcessId = jobProcessId;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getNodeProcessId() {
        return nodeProcessId;
    }

    public void setNodeProcessId(Integer nodeProcessId) {
        this.nodeProcessId = nodeProcessId;
    }

    public String getNodeGroup() {
        return nodeGroup;
    }

    public void setNodeGroup(String nodeGroup) {
        this.nodeGroup = nodeGroup;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
