package com.vbrug.workflow.service;

import com.vbrug.fw4j.common.util.BeanUtils;
import com.vbrug.fw4j.common.util.CollectionUtils;
import com.vbrug.workflow.bean.NodeBean;
import com.vbrug.workflow.bean.ResultBean;
import com.vbrug.workflow.constants.Constants;
import com.vbrug.workflow.persistence.definition.node.po.NodePO;
import com.vbrug.workflow.persistence.definition.node.service.NodeService;
import com.vbrug.workflow.persistence.definition.path.po.PathPO;
import com.vbrug.workflow.persistence.definition.path.service.PathService;
import com.vbrug.workflow.persistence.definition.process.service.ProcessService;
import com.vbrug.workflow.persistence.instance.job.service.JobService;
import com.vbrug.workflow.persistence.instance.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author vbrug
 * @since 1.0.0
 */
@Component
public class WorkFlowService {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowService.class);

    @Resource
    private ProcessService processService;

    @Resource
    private NodeService nodeService;

    @Resource
    private PathService pathService;

    @Resource
    private JobService jobService;

    @Resource
    private TaskService taskService;

    /**
     * 启动作业
     */
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    public ResultBean<List<NodeBean>> startJob(Integer processId) {

        ResultBean<List<NodeBean>> result = new ResultBean<>();

        // 00-启动作业
        int jobId = jobService.startJob(processId);

        // 01-处理开始节点
        NodePO startNode = nodeService.findStartNode(processId);
        NodeBean startNodeBean = new NodeBean();
        BeanUtils.copyProperties(startNode, startNodeBean);
        taskService.startTask(jobId, processId, startNode.getId());
        taskService.finishTask(jobId, startNode.getId());

        // 02-获取下一任务节点
        List<NodeBean> todoNodeList = this.getTodoTask(jobId, processId, startNode.getId());


        if (!CollectionUtils.isEmpty(todoNodeList)) {
            // 03-判断任务是否完成
            if (todoNodeList.size() == 1 && todoNodeList.get(0).getType().equals(Constants.TASK_END))
                return finishJob(result, jobId);
            for (NodeBean x : todoNodeList) {
                taskService.startTask(jobId, processId, x.getId());
            }
        }

        logger.info("待执行任务数量{}", todoNodeList.size());

        // 处理结果
        Map<String, Object> dataMap = CollectionUtils.createValueMap()
                .add("jobId", jobId)
                .add("jobName", processService.find(processId).getName())
                .add("currentNodeBean", startNodeBean).build();
        result.setT(todoNodeList);
        result.setResult(dataMap);
        return successReturn(result);
    }


    /**
     * 获取下一任务
     *
     * @param jobId     作业ID
     * @param processId 流程ID
     * @param nodeId    当前节点ID
     * @return 结果
     */
    public synchronized ResultBean<List<NodeBean>> getNextTask(Integer jobId, Integer processId, Integer nodeId) {
        ResultBean<List<NodeBean>> result = new ResultBean<>();
        // 00-完成当前任务
        taskService.finishTask(jobId, nodeId);

        // 01-获取下一任务节点
        List<NodeBean> todoNodeList = this.getTodoTask(jobId, processId, nodeId);
        if (todoNodeList == null) {
            return successReturn(result);
        } else if (todoNodeList.size() == 1 && todoNodeList.get(0).getType().equals(Constants.TASK_END)) {
            return finishJob(result, jobId);
        }
        todoNodeList.forEach(x -> {
            taskService.startTask(jobId, processId, x.getId());
        });

        logger.info("待执行任务数量{}", todoNodeList.size());

        // 处理结果
        Map<String, Object> dataMap = CollectionUtils.createValueMap()
                .add(Constants.PARAM_JOB_ID, jobId).build();

        result.setResult(dataMap);
        result.setT(todoNodeList);
        return successReturn(result);
    }

    /**
     * 获取待执行任务
     *
     * @param jobId     作业ID
     * @param processId 流程ID
     * @param nodeId    节点ID
     * @return
     */
    private List<NodeBean> getTodoTask(Integer jobId, Integer processId, Integer nodeId) {
        List<NodeBean> todoNodeList = new ArrayList<>();

        // 查询指向节点
        List<PathPO> toNodeList = pathService.queryPath(processId, nodeId, null);
        if (toNodeList == null || toNodeList.size() == 0)
            return null;

        // 获取待执行节点
        toNodeList.forEach(x -> {

            // 查询前置任务节点
            List<Integer> fromNodeList = pathService.queryPath(processId, null, x.getToNode())
                    .stream().map(PathPO::getFromNode).collect(Collectors.toList());

            // 判断前置任务是否已执行完毕
            if (taskService.getFinishedPreTaskCount(jobId, fromNodeList) == fromNodeList.size()) {
                NodePO nodePO = nodeService.find(processId, x.getToNode());
                NodeBean nodeBean = new NodeBean();
                BeanUtils.copyProperties(nodePO, nodeBean);
                nodeBean.setFromNodeList(fromNodeList);
                todoNodeList.add(nodeBean);
            }

        });
        return todoNodeList;
    }

    /**
     * 完成作业
     *
     * @param jobId 作业ID
     * @return 结果Bean
     */
    private <T> ResultBean<T> finishJob(ResultBean<T> result, Integer jobId) {
        jobService.finishJob(jobId);
        result.setStatus(Constants.STATUS_CODE_5);
        return result;
    }

    /**
     * 成功返回方法
     *
     * @param dataMap 结果数据
     * @return 结果Bean
     */
    private <T> ResultBean<T> successReturn(ResultBean<T> result) {
        result.setStatus(Constants.STATUS_CODE_0);
        result.setMessage("ok");
        return result;
    }
}
