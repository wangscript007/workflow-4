package com.vbrug.workflow.core.persistence.instance.task.service;

import com.vbrug.workflow.core.persistence.instance.job.po.JobPO;
import com.vbrug.workflow.core.persistence.instance.task.dto.TaskDTO;

import java.util.List;
import java.util.Map;

/**
 * 任务Service
 * @author vbrug
 * @since 1.0.0
 */
public interface TaskService {

    /**
     * 执行流程开始任务
     * @param jobPO 作业对象
     * @return 开始任务ID
     */
    long doStartTask(JobPO jobPO);

    /**
     * 执行子流程开始任务
     * @param jobPO          作业对象
     * @param childProcessId 子流程ID
     * @return 开始任务ID
     */
    long doStartTask(JobPO jobPO, Integer childProcessId);

    /**
     * 完成任务
     * @param taskId       待完成的任务ID
     * @param resultParams 任务结果参数
     * @return 执行结果
     */
    int completeTask(long taskId, Map<String, Object> resultParams);

    /**
     * 重跑任务
     * @param taskId 待重跑任务ID
     * @return 任务信息
     */
    TaskDTO redoTask(long taskId);

    /**
     * 获取待执行任务
     * @param lastTaskId   上一完成任务ID
     * @param precondition 前置判断条件
     * @return 待执行任务信息数组
     */
    List<TaskDTO> gTodoTasks(long lastTaskId, int precondition);

    /**
     * 获取作业失败任务
     * @param jobId 作业ID
     * @return 失败任务信息数组
     */
    List<TaskDTO> gFailTasks(long jobId);

}
