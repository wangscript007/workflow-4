package com.vbrug.workflow.core.persistence.instance.task.service.impl;

import com.vbrug.fw4j.common.util.CollectionUtils;
import com.vbrug.fw4j.common.util.IdGenerator;
import com.vbrug.fw4j.common.util.JacksonUtils;
import com.vbrug.fw4j.common.util.ObjectUtils;
import com.vbrug.workflow.core.constants.Constants;
import com.vbrug.workflow.core.exceptions.NodeNotFoundException;
import com.vbrug.workflow.core.exceptions.TaskNotFoundException;
import com.vbrug.workflow.core.exceptions.WorkFlowException;
import com.vbrug.workflow.core.persistence.definition.node.po.NodePO;
import com.vbrug.workflow.core.persistence.definition.node.service.NodeService;
import com.vbrug.workflow.core.persistence.definition.path.po.PathPO;
import com.vbrug.workflow.core.persistence.definition.path.service.PathService;
import com.vbrug.workflow.core.persistence.instance.job.entity.JobPO;
import com.vbrug.workflow.core.persistence.instance.task.entity.TaskDTO;
import com.vbrug.workflow.core.persistence.instance.task.entity.TaskPO;
import com.vbrug.workflow.core.persistence.instance.task.entity.TaskVO;
import com.vbrug.workflow.core.persistence.instance.task.mapper.TaskMapper;
import com.vbrug.workflow.core.persistence.instance.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务实现类
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Resource
    private TaskMapper mapper;

    @Resource
    private NodeService nodeService;

    @Resource
    private PathService pathService;

    @Override
    public long doStartTask(JobPO jobPO) {
        return this.doStartTask_(jobPO.getId(), jobPO.getProcessId(), null);
    }


    @Override
    public int completeTask(long taskId, Map<String, Object> resultParams) {
        return mapper.update(TaskVO.newInstance().setId(taskId)
                .setParams(JacksonUtils.bean2Json(resultParams))
                .setState(Constants.TASK_STATE_FINISH));
    }

    @Override
    public TaskDTO redoTask(long taskId) {
        TaskDTO redoTask = mapper.gTaskDTO(taskId).get(0);
        if (ObjectUtils.isNull(redoTask))
            throw new TaskNotFoundException("任务{}不存在", taskId);
        mapper.updateRetryTimes(taskId);
        return redoTask;
    }

    @Override
    public int recordFailTask(long taskId) {
        return mapper.update(TaskVO.newInstance().setId(taskId).setState(Constants.TASK_STATE_FAILURE));
    }

    @Override
    public List<TaskDTO> gTodoTasks(long lastTaskId, int precondition) {
        return mapper.gTaskDTO(Objects.requireNonNull(this.gTodoTasks_(lastTaskId, precondition)).toArray(new Long[0]));
    }

    @Override
    public List<TaskDTO> redoFailTasks(long jobId) {
        List<TaskDTO> dtoList = mapper.gTaskDTOByJob(jobId, Constants.TASK_STATE_FAILURE);
        if (!CollectionUtils.isEmpty(dtoList))
            mapper.updateRetryTimes(dtoList.stream().map(TaskDTO::getTaskId).toArray(Long[]::new));
        return dtoList;
    }

    @Override
    public int migrateFinishTask(long jobId) {
        List<TaskPO> unfinishedTasks = mapper.query(TaskVO.newInstance().setJobId(jobId).setUnfinishedTask(true));
        if (!CollectionUtils.isEmpty(unfinishedTasks))
            throw new WorkFlowException("作业 {} 尚有任务 {} 未执行完成", jobId,
                    unfinishedTasks.stream().map(TaskPO::getId).collect(Collectors.toList()));
        return mapper.migrateCompleteTask(jobId);
    }

    /**
     * 获取待执行任务
     * @param lastTaskId   上一任务编号
     * @param precondition 前置判断
     * @return 待执行任务ID
     */
    private List<Long> gTodoTasks_(long lastTaskId, int precondition) {
        // 01-根据工作流查询任务下一步节点信息
        TaskPO       lastTaskPO = mapper.find(lastTaskId);
        Long         jobId      = lastTaskPO.getJobId();
        List<PathPO> pathPOS    = pathService.queryPath(lastTaskPO.getNodeId(), null, precondition);

        if (CollectionUtils.isEmpty(pathPOS))
            throw new WorkFlowException("流程定义异常，节点{}，非结束节点但无下级节点", lastTaskPO.getNodeId());

        // 02-判断待执行节点
        List<Integer> todoNodeList = new ArrayList<>();
        for (PathPO pathPO : pathPOS) {
            Integer currentNodeId = pathPO.getToNode();
            if (this.isTodoNode(jobId, currentNodeId)) {
                todoNodeList.add(currentNodeId);
            }
        }
        if (CollectionUtils.isEmpty(todoNodeList))
            return null;

        // 03-处理待执行节点
        List<Long> todoTaskList = new ArrayList<>();
        for (Integer todoNodeId : todoNodeList) {
            todoTaskList.addAll(this.handleTodoNode(jobId, lastTaskId, todoNodeId));
        }

        return todoTaskList;
    }

    /**
     * 判断当前节点是否为作业待执行任务节点
     * @param currentNodeId 当前节点ID
     * @param jobId         作业ID
     * @return 判断结果
     */
    private boolean isTodoNode(Long jobId, Integer currentNodeId) {
        List<PathPO> fromPathPOS = pathService.queryPath(null, currentNodeId, null);
        Integer[]    fromNodeIds = fromPathPOS.stream().map(PathPO::getFromNode).toArray(Integer[]::new);
        List<TaskPO> finishTaskList = mapper.query(TaskVO.newInstance()
                .setJobId(jobId).setState(Constants.TASK_STATE_FINISH).setNodeIds(fromNodeIds));
        if (!CollectionUtils.isEmpty(finishTaskList) && finishTaskList.size() == fromPathPOS.size()) {
            return true;
        }
        if (logger.isDebugEnabled()) {
            String unFinishNodeIds = CollectionUtils.isEmpty(finishTaskList)
                    ? fromPathPOS.stream().map(x -> x.getFromNode().toString()).collect(Collectors.joining(","))
                    : fromPathPOS.stream().filter(x -> finishTaskList.stream().noneMatch(y -> y.getNodeId() == x.getFromNode()))
                    .map(x -> x.getFromNode().toString()).collect(Collectors.joining(","));
            logger.debug("当前作业{}中，节点 {} 尚有未完成节点 {}", jobId, currentNodeId, unFinishNodeIds);
        }
        return false;
    }

    /**
     * 处理待执行节点
     * @param jobId      作业ID
     * @param lastTaskId 上次任务ID
     * @param nodeId     节点ID
     * @return 待执行作业ID
     */
    private List<Long> handleTodoNode(Long jobId, Long lastTaskId, Integer nodeId) {
        TaskDTO lastTaskDTO = mapper.gTaskDTO(lastTaskId).get(0);
        NodePO  nodePO      = nodeService.find(nodeId);

        // 01-子流程节点处理
        if (Objects.equals(Constants.NODE_TYPE_PROCESS, nodePO.getType())) {
            long childProcessTaskId = this.doTask_(jobId, nodeId, null);
            long startTaskId        = this.doStartTask_(jobId, nodePO.getChildProcessId(), childProcessTaskId);
            this.completeTask(startTaskId, null);
            return this.gTodoTasks_(startTaskId, Constants.TASK_PRECONDITION_TRUE);
        }

        // 02-结束节点处理
        if (Objects.equals(Constants.NODE_TYPE_END, nodePO.getType())) {
            if (Objects.equals(nodePO.getProcessId(), lastTaskDTO.getJobProcessId())) {
                return Collections.singletonList(this.doTask_(jobId, nodeId, null));
            } else {
                long endTaskId = this.doTask_(jobId, nodeId, lastTaskDTO.getParentTaskId());
                this.completeTask(endTaskId, null);
                this.completeTask(lastTaskDTO.getParentTaskId(), null);
                return this.gTodoTasks_(lastTaskDTO.getParentTaskId(), Constants.TASK_PRECONDITION_TRUE);
            }
        }

        // 03-其他节点
        return Collections.singletonList(this.doTask_(jobId, nodeId, lastTaskDTO.getParentTaskId()));
    }

    /**
     * 执行流程的开始节点
     * @param jobId        作业ID
     * @param nodeId       流程Id
     * @param parentTaskId 父任务编号
     * @return 当前任务ID
     */
    private long doTask_(Long jobId, Integer nodeId, Long parentTaskId) {
        TaskPO po = new TaskPO();
        po.setId(IdGenerator.nextId());
        po.setJobId(jobId);
        po.setNodeId(nodeId);
        po.setParentId(parentTaskId);
        po.setState(Constants.TASK_STATE_EXECUTING);
        mapper.insert(po);
        return po.getId();
    }

    /**
     * 执行流程开始任务
     * @param jobId     作业ID
     * @param processId 流程ID
     * @return 返回开始任务
     */
    private long doStartTask_(Long jobId, Integer processId, Long parentTaskId) {
        NodePO startNode = nodeService.findStartNode(processId);
        if (Objects.isNull(startNode))
            throw new NodeNotFoundException("流程 {} 开始节点不存在", processId);
        return this.doTask_(jobId, startNode.getId(), parentTaskId);
    }

}
