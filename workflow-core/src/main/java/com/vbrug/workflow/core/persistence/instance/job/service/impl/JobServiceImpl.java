package com.vbrug.workflow.core.persistence.instance.job.service.impl;

import com.vbrug.fw4j.common.util.IdGenerator;
import com.vbrug.fw4j.common.util.JacksonUtils;
import com.vbrug.fw4j.common.util.ObjectUtils;
import com.vbrug.workflow.core.constants.Constants;
import com.vbrug.workflow.core.exceptions.ProcessNotFoundException;
import com.vbrug.workflow.core.persistence.definition.process.service.ProcessService;
import com.vbrug.workflow.core.persistence.instance.job.mapper.JobMapper;
import com.vbrug.workflow.core.persistence.instance.job.po.JobPO;
import com.vbrug.workflow.core.persistence.instance.job.service.JobService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class JobServiceImpl implements JobService {

    @Resource
    private JobMapper mapper;

    @Resource
    private ProcessService processService;


    /**
     * 更新作业状态
     * @param id    作业ID
     * @param state 状态
     * @return 影响记录数
     */
    public int updateState(Integer id, Integer state) {
        return mapper.updateState(id, state);
    }

    /**
     * 完成作业
     * @param id 作业Id
     * @return 影响记录数
     */
    public int finishJob(Integer id) {
        return mapper.finishJob(id);
    }

    @Override
    public long newJob(int processId, Map<String, Object> params) {
        if (ObjectUtils.isNull(processService.find(processId)))
            throw new ProcessNotFoundException("流程：{} 不存在", String.valueOf(processId));
        long  jobId = IdGenerator.nextMillis2();
        JobPO po    = new JobPO();
        po.setId(jobId);
        po.setProcessId(processId);
        po.setState(Constants.JOB_STATE_TODO);
        po.setParams(JacksonUtils.bean2Json(params));
        mapper.insert(po);
        return jobId;
    }

    @Override
    public int startExecuteJob(int id) {
        return mapper.startExecuteJob(id, Constants.JOB_STATE_EXECUTING);
    }

    @Override
    public JobPO find(int id) {
        return mapper.find(id);
    }


    @Override
    public Map<String, Object> getJobContext(int jobId) {
        return null;
    }

    @Override
    public int completeJob(int jobId) {
        return 0;
    }
}
