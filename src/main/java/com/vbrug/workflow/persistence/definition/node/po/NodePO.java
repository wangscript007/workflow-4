package com.vbrug.workflow.persistence.definition.node.po;

/**
 * 节点持久化对象
 *
 * @author vbrug
 * @since 1.0.0
 */
public class NodePO {

    private Integer id;                                // 节点编号
    private Integer processId;                         // 流程ID
    private String group;                              // 分组
    private String name;                               // 节点名称
    private String type;                               // 节点类型
    private Integer failHandle;                        // 失败处理（0-流程结束）
    private String rollNode;                           // 回滚节点
    private String remark;                             // 描述

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFailHandle() {
        return failHandle;
    }

    public void setFailHandle(Integer failHandle) {
        this.failHandle = failHandle;
    }

    public String getRollNode() {
        return rollNode;
    }

    public void setRollNode(String rollNode) {
        this.rollNode = rollNode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
