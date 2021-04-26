package com.vbrug.workflow.core;

import com.vbrug.fw4j.common.util.IdGenerator;
import com.vbrug.fw4j.core.spring.SpringHelp;
import com.vbrug.workflow.core.bean.NodeBean;
import com.vbrug.workflow.core.bean.ResultBean;
import com.vbrug.workflow.core.service.WorkFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 工作流引擎
 * @author vbrug
 * @since 1.0.0
 */
public class WorkFlowEngine {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);

    private final WorkFlowService service = SpringHelp.getBean(WorkFlowService.class);

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
     * 启动作业
     */
    public ResultBean<List<NodeBean>> startJob(Integer processId) {
        IdGenerator.nextMillis();
        logger.info("WF->> {} 工作流启动", processId);
        return service.startJob(processId);
    }

    /**
     * 获取下一任务
     */
    public ResultBean<List<NodeBean>> executeTask(Integer jobId, Integer nodeId) {
        logger.info("WF->>{}, 执行作业任务-{}", processId, jobId + "-" + nodeId);
        return service.execute(jobId, processId, nodeId);
    }
}
