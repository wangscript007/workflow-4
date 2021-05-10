package com.vbrug.workflow.core.persistence.instance.task.service.impl;

import com.vbrug.fw4j.common.util.IdGenerator;
import com.vbrug.fw4j.common.util.JacksonUtils;
import com.vbrug.workflow.core.constants.Constants;
import com.vbrug.workflow.core.exceptions.NodeNotFoundException;
import com.vbrug.workflow.core.persistence.definition.node.po.NodePO;
import com.vbrug.workflow.core.persistence.definition.node.service.NodeService;
import com.vbrug.workflow.core.persistence.definition.path.po.PathPO;
import com.vbrug.workflow.core.persistence.definition.path.service.PathService;
import com.vbrug.workflow.core.persistence.instance.job.po.JobPO;
import com.vbrug.workflow.core.persistence.instance.task.dto.TaskDTO;
import com.vbrug.workflow.core.persistence.instance.task.mapper.TaskMapper;
import com.vbrug.workflow.core.persistence.instance.task.po.TaskPO;
import com.vbrug.workflow.core.persistence.instance.task.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper mapper;

    @Resource
    private NodeService nodeService;

    @Resource
    private PathService pathService;

    @Override
    public long doStartTask(JobPO jobPO) {
        return this.doStartTask_(jobPO, jobPO.getProcessId());
    }

    @Override
    public long doStartTask(JobPO jobPO, Integer childProcessId) {
        return this.doStartTask_(jobPO, childProcessId);
    }

    private long doStartTask_(JobPO jobPO, Integer childProcessId) {
        // 查询流程开始节点
        NodePO startNode = nodeService.findStartNode(jobPO.getProcessId());
        if (Objects.isNull(startNode))
            throw new NodeNotFoundException("流程{} 开始节点不存在", jobPO.getProcessId());

        // 插入开始任务
        TaskPO po = new TaskPO();
        po.setId(IdGenerator.nextId());
        po.setJobId(jobPO.getId());
        po.setNodeId(startNode.getId());
        po.setRetryTimes(0);
        po.setState(Constants.TASK_STATE_EXECUTING);
        mapper.insert(po);
        return po.getId();
    }

    @Override
    public int completeTask(long taskId, Map<String, Object> resultParams) {
        return mapper.completeTask(taskId, JacksonUtils.bean2Json(resultParams), Constants.TASK_STATE_FINISH);
    }

    @Override
    public TaskDTO redoTask(long taskId) {
        return mapper.gTaskDTO(taskId);
    }

    @Override
    public List<TaskDTO> gTodoTasks(long lastTaskId, int precondition) {
        // 根据工作流查询任务下一步节点信息
        TaskPO lastTaskPO = mapper.find(lastTaskId);
        List<PathPO> pathPOS = pathService.queryPath(lastTaskPO.getNodeId(), null, precondition);
        return null;
    }

    @Override
    public List<TaskDTO> gFailTasks(long jobId) {
        return mapper.gTaskDTO(jobId, Constants.TASK_STATE_FAILURE);
    }


    /**
     * 查询前置完成的任务数量
     * @param jobId       作业ID
     * @param preNodeList 前置节点Id 集合
     * @return 完成任务数量
     */
    public int getFinishedPreTaskCount(Integer jobId, List<Integer> preNodeList) {
        return mapper.getFinishedPreTaskCount(jobId, preNodeList);
    }

}
