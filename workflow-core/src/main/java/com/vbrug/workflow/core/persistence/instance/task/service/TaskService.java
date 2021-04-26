package com.vbrug.workflow.core.persistence.instance.task.service;

import com.vbrug.workflow.core.constants.Constants;
import com.vbrug.workflow.core.persistence.instance.task.po.TaskPO;
import com.vbrug.workflow.core.persistence.instance.task.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务Service
 *
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class TaskService {

    @Resource
    private TaskMapper mapper;

    /**
     * 启动任务
     */
    public int startTask(Integer jobId, Integer processId, Integer nodeId){
        TaskPO po = new TaskPO();
        po.setJobId(jobId);
        po.setProcessId(processId);
        po.setNodeId(nodeId);
        po.setState(Constants.TASK_STATE_0);
        return mapper.insert(po);
    }


    /**
     * 更新任务状态
     */
    public int updateState(Integer jobId, Integer nodeId, Integer state){
        return mapper.updateState(jobId, nodeId, state);
    }

    /**
     * 完成作业
     */
    public int finishTask(Integer jobId, Integer nodeId){
        return mapper.finishTask(jobId, nodeId);
    }

    /**
     * 查询前置完成的任务数量
     *
     * @param jobId 作业ID
     * @param preNodeList 前置节点Id 集合
     * @return 完成任务数量
     */
    public int getFinishedPreTaskCount(Integer jobId, List<Integer> preNodeList){
        return mapper.getFinishedPreTaskCount(jobId, preNodeList);
    }
}
