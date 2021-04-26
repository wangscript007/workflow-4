package com.vbrug.workflow.core.persistence.instance.job.service;

import com.vbrug.workflow.core.constants.Constants;
import com.vbrug.workflow.core.persistence.definition.process.service.ProcessService;
import com.vbrug.workflow.core.persistence.instance.job.mapper.JobMapper;
import com.vbrug.workflow.core.persistence.instance.job.po.JobPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 作业Service
 *
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class JobService {

    @Resource
    private JobMapper mapper;

    @Resource
    private ProcessService processService;


    /**
     * 启动作业
     *
     * @param processId 流程ID
     * @return 作业ID
     */
    public int startJob(Integer processId){
        JobPO po = new JobPO();
        po.setId(mapper.getNextId());
        po.setProcessId(processId);
        po.setState(Constants.JOB_STATE_0);
        mapper.insert(po);
        return po.getId();
    }

    /**
     * 更新作业状态
     *
     * @param id 作业ID
     * @param state 状态
     * @return 影响记录数
     */
    public int updateState(Integer id, Integer state){
        return mapper.updateState(id, state);
    }

    /**
     * 完成作业
     *
     * @param id 作业Id
     * @return 影响记录数
     */
    public int finishJob(Integer id){
        return mapper.finishJob(id);
    }
}
