package com.vbrug.workflow.core;

import com.vbrug.workflow.core.bean.Result;

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
     * 完成作业开始任务
     * @param jobId 作业ID
     * @return 结果
     */
    Result completeStartTask(int jobId);

    /**
     * 完成任务
     * @param taskId 待完成任务
     * @param resultParams 任务结果参数
     * @return 结果
     */
    Result completeTask(int taskId, Map<String, Object> resultParams);

    /**
     * 重跑任务
     * @param taskId 待完成任务
     * @return 结果
     */
    Result redoTask(int taskId);

    /**
     * 获取作业失败任务
     * @param jobId 作业ID
     * @return 结果
     */
    Result gFailTasks(int jobId);

    /**
     * 获取下一待执行任务
     * @param lastTaskId 上一任务ID
     * @param precondition 前置判断
     * @return 结果
     */
    Result gTodoTasks(int lastTaskId, int precondition);

    /**
     * 获取作业环境变量
     * @param jobId 作业ID
     * @return 结果
     */
    Result gJobContext(int jobId);

    /**
     * 完成作业
     * @param jobId 作业ID
     * @return 结果
     */
    Result completeJob(int jobId);
}
