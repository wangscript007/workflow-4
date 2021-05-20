package com.vbrug.workflow.core;

import com.vbrug.workflow.core.entity.Result;

import java.util.Map;

/**
 * 工作流Service
 * @author vbrug
 * @since 1.0.0
 */
public interface WorkFlowService {

    /**
     * 创建作业
     * @return 结果
     */
    long newJob(int processId, Map<String, Object> params);

    /**
     * 开始执行作业开始任务
     * @param jobId 作业ID
     * @return 结果
     */
    Result doStartTask(long jobId);

    /**
     * 完成任务
     * @param taskId       待完成任务
     * @param resultParams 任务结果参数
     * @return 结果
     */
    Result completeTask(long taskId, Map<String, Object> resultParams);

    /**
     * 记录失败任务
     * @param taskId 任务ID
     * @return 结果
     */
    Result recordFailTask(long taskId);

    /**
     * 重跑任务
     * @param taskId 待完成任务
     * @return 结果
     */
    Result redoTask(long taskId);

    /**
     * 获取作业失败任务
     * @param jobId 作业ID
     * @return 结果
     */
    Result redoFailTasks(long jobId);

    /**
     * 获取下一待执行任务
     * @param lastTaskId   上一任务ID
     * @param precondition 前置判断
     * @return 结果
     */
    Result gTodoTasks(long lastTaskId, int precondition);

    /**
     * 获取作业环境变量
     * @param jobId 作业ID
     * @return 结果
     */
    Result gJobContext(long jobId);

    /**
     * 完成作业
     * @param jobId 作业ID
     * @return 结果
     */
    Result completeJob(long jobId);
}
