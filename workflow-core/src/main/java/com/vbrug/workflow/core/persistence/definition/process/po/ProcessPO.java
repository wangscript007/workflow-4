package com.vbrug.workflow.core.persistence.definition.process.po;

import java.io.Serializable;


/**
 * 流程持久化对象
 *
 * @author vbrug
 * @since 1.0.0
 */
public class ProcessPO implements Serializable {

    private static final long serialVersionUID = -762553837279635224L;

    private Integer id;                                // id
    private String name;                               // 名称
    private Integer deployFlag;                        // 发布标志（0-否，1-是）
    private String remark;                             // 描述
    private String createUser;                         // 创建用户
    private String createTime;                         // 创建时间
    private String updateUser;                         // 修改用户
    private String updateTime;                         // 修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeployFlag() {
        return deployFlag;
    }

    public void setDeployFlag(Integer deployFlag) {
        this.deployFlag = deployFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
