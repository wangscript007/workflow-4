package com.vbrug.workflow.core.persistence.instance.task.dto;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class TaskDTO {

    private long    taskId;                                // 任务ID
    private long    jobId;                             // 作业ID
    private Integer processId;                         // 流程ID
    private Integer nodeId;                            // 节点ID
    private Integer childNodeId;                            // 节点ID
    private String  nodeGroup;                         // 分组
    private String  nodeName;                          // 节点名称
    private String  nodeType;                          // 节点类型

    public Integer getChildNodeId() {
        return childNodeId;
    }

    public void setChildNodeId(Integer childNodeId) {
        this.childNodeId = childNodeId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
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
