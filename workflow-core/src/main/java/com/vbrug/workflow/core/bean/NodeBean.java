package com.vbrug.workflow.core.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class NodeBean implements Serializable {

    private Integer id;                                // 节点编号
    private Integer processId;                         // 流程ID
    private String group;                              // 分组
    private String name;                               // 节点名称
    private String type;                               // 节点类型
    private String remark;                             // 描述
    private List<Integer> fromNodeList;                 // 前置节点

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Integer> getFromNodeList() {
        return fromNodeList;
    }

    public void setFromNodeList(List<Integer> fromNodeList) {
        this.fromNodeList = fromNodeList;
    }
}
