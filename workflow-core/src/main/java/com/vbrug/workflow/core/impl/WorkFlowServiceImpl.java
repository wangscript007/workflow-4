package com.vbrug.workflow.core.impl;

import com.vbrug.workflow.core.WorkFlowService;
import com.vbrug.workflow.core.bean.Result;
import com.vbrug.workflow.core.persistence.instance.job.po.JobPO;
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
    public Result completeStartTask(int jobId) {
        // 开始执行作业
        jobService.startExecuteJob(jobId);

        // 查询作业信息
        JobPO jobPO = jobService.find(jobId);

        //
        return null;
    }

    @Override
    public Result completeTask(int taskId, Map<String, Object> resultParams) {
        return null;
    }

    @Override
    public Result redoTask(int taskId) {
        return null;
    }

    @Override
    public Result gFailTasks(int jobId) {
        return null;
    }

    @Override
    public Result gTodoTasks(int lastTaskId, int precondition) {
        return null;
    }

    @Override
    public Result gJobContext(int jobId) {
        return null;
    }

    @Override
    public Result completeJob(int jobId) {
        return null;
    }
}
