package com.vbrug.workflow.core.persistence.definition.path.po;

/**
 * 路径持久化对象
 *
 * @author vbrug
 * @since 1.0.0
 */
public class PathPO {

    private Integer id;                                // 路径编号
    private Integer processId;                         // 流程编号
    private Integer fromNode;                          // 起始节点
    private Integer toNode;                            // 目标节点

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

    public Integer getFromNode() {
        return fromNode;
    }

    public void setFromNode(Integer fromNode) {
        this.fromNode = fromNode;
    }

    public Integer getToNode() {
        return toNode;
    }

    public void setToNode(Integer toNode) {
        this.toNode = toNode;
    }
}
