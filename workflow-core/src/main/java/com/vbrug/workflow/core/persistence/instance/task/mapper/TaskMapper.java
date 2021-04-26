package com.vbrug.workflow.core.persistence.instance.task.mapper;

import com.vbrug.workflow.core.persistence.instance.task.po.TaskPO;

import java.util.List;

/**
 * 任务Mapper接口
 *
 * @author vbrug
 * @since 1.0.0
 */
public interface TaskMapper {

    /**
     * 插入
     *
     * @param po 实体对象
     * @return 影响记录数
     */
    int insert(TaskPO po);

    /**
     * 更新作业状态
     *
     * @param id 作业ID
     * @param state 状态
     * @return 影响记录数
     */
    int updateState(Integer jobId, Integer nodeId, Integer state);

    /**
     * 完成作业
     *
     * @param id 作业Id
     * @return 影响记录数
     */
    int finishTask(Integer jobId, Integer nodeId);

    /**
     * 查询前置完成的任务数量
     *
     * @param jobId 作业ID
     * @param preNodeList 前置节点Id 集合
     * @return 完成任务数量
     */
    int getFinishedPreTaskCount(Integer jobId, List<Integer> preNodeList);

}
