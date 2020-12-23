package com.vbrug.workflow;

import com.vbrug.fw4j.core.spring.SpringHelp;
import com.vbrug.workflow.bean.NodeBean;
import com.vbrug.workflow.bean.ResultBean;
import com.vbrug.workflow.service.WorkFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class WorkFlowEngine {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);

    private WorkFlowEngine() {
    }

    private static class WorkFlowEngineHolder {
        private static final WorkFlowEngine instance = new WorkFlowEngine();
    }

    public static final WorkFlowEngine getInstance() {
        return WorkFlowEngineHolder.instance;
    }

    private WorkFlowService service = SpringHelp.getBean(WorkFlowService.class);

    /**
     * 启动作业
     */
    public ResultBean<List<NodeBean>> startJob(Integer processId) {
        logger.info("启动工作流：{}", processId);
        return service.startJob(processId);
    }

    /**
     * 获取下一任务
     */
    public ResultBean<List<NodeBean>> getNextTask(Integer jobId, Integer processId, Integer nodeId) {
        logger.info("作业: {}, 流程:{}, 节点: {}", jobId, processId, nodeId);
        return service.getNextTask(jobId, processId, nodeId);
    }
}
