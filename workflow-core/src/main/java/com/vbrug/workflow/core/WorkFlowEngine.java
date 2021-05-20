package com.vbrug.workflow.core;

import com.vbrug.fw4j.core.spring.SpringHelp;
import com.vbrug.workflow.core.entity.Result;
import com.vbrug.workflow.core.impl.WorkFlowServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 工作流引擎
 * @author vbrug
 * @since 1.0.0
 */
public class WorkFlowEngine {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);

    private final WorkFlowServiceImpl service = SpringHelp.getBean(WorkFlowServiceImpl.class);

    private WorkFlowEngine() {}

    /**
     * 静态内部类实现单例模式, 内部类在需要时才初始化
     */
    private static class WorkFlowEngineHolder {
        private static final WorkFlowEngine instance = new WorkFlowEngine();
    }

    public static WorkFlowEngine getInstance() {
        return WorkFlowEngineHolder.instance;
    }


    /**
     * 创建作业
     * @return 结果
     */
    public long newJob(int processId, Map<String, Object> params) {
        return service.newJob(processId, params);
    }

    /**
     * 开始执行作业开始任务
     * @param jobId 作业ID
     * @return 结果
     */
    public Result doStartTask(long jobId) {
        return service.doStartTask(jobId);
    }

    /**
     * 完成任务
     * @param taskId       待完成任务
     * @param resultParams 任务结果参数
     * @return 结果
     */
    public Result completeTask(long taskId, Map<String, Object> resultParams) {
        return service.completeTask(taskId, resultParams);
    }

    /**
     * 记录失败任务
     * @param taskId 任务ID
     * @return 结果
     */
    public Result recordFailTask(long taskId) {
        return service.recordFailTask(taskId);
    }

    /**
     * 重跑任务
     * @param taskId 待完成任务
     * @return 结果
     */
    public Result redoTask(long taskId) {
        return service.redoTask(taskId);
    }

    /**
     * 获取作业失败任务
     * @param jobId 作业ID
     * @return 结果
     */
    public Result redoFailTasks(long jobId) {
        return service.redoFailTasks(jobId);
    }

    /**
     * 获取下一待执行任务
     * @param lastTaskId   上一任务ID
     * @param precondition 前置判断
     * @return 结果
     */
    public Result gTodoTasks(long lastTaskId, int precondition) {
        return service.gTodoTasks(lastTaskId, precondition);
    }

    /**
     * 获取作业环境变量
     * @param jobId 作业ID
     * @return 结果
     */
    public Result gJobContext(long jobId) {
        return service.gJobContext(jobId);
    }

    /**
     * 完成作业
     * @param jobId 作业ID
     * @return 结果
     */
    public Result completeJob(long jobId) {
        return service.completeJob(jobId);
    }

}
