package com.vbrug.workflow.core.impl;

import com.vbrug.workflow.core.WorkFlowService;
import com.vbrug.workflow.core.entity.Result;
import com.vbrug.workflow.core.persistence.instance.job.service.JobService;
import com.vbrug.workflow.core.persistence.instance.task.service.TaskService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 工作流Service实现类
 * @author vbrug
 * @since 1.0.0
 */
@Component
public class WorkFlowServiceImpl implements WorkFlowService {

    @Resource
    private JobService jobService;

    @Resource
    private TaskService taskService;


    @Override
    public long newJob(int processId, Map<String, Object> params) {
        return jobService.newJob(processId, params);
    }

    @Override
    public Result doStartTask(long jobId) {
        // 开始执行作业
        jobService.startExecuteJob(jobId);
        // 执行作业开始任务
        long startTaskId = taskService.doStartTask(jobService.find(jobId));
        return Result.success(startTaskId);
    }

    @Override
    public Result completeTask(long taskId, Map<String, Object> resultParams) {
        int resultNum = taskService.completeTask(taskId, null);
        return Result.success(resultNum);
    }

    @Override
    public Result recordFailTask(long taskId) {
        return Result.success(taskService.recordFailTask(taskId));
    }

    @Override
    public Result redoTask(long taskId) {
        return Result.success(taskService.redoTask(taskId));
    }

    @Override
    public Result redoFailTasks(long jobId) {
        return Result.success(taskService.redoFailTasks(jobId));
    }

    @Override
    public Result gTodoTasks(long lastTaskId, int precondition) {
        return Result.success(taskService.gTodoTasks(lastTaskId, precondition));
    }

    @Override
    public Result gJobContext(long jobId) {
        return null;
    }

    @Override
    public Result completeJob(long jobId) {
        return Result.success(jobService.completeJob(jobId));
    }
}
