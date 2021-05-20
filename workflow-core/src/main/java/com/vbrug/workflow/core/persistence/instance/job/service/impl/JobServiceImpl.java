package com.vbrug.workflow.core.persistence.instance.job.service.impl;

import com.vbrug.fw4j.common.util.IdGenerator;
import com.vbrug.fw4j.common.util.JacksonUtils;
import com.vbrug.fw4j.common.util.ObjectUtils;
import com.vbrug.workflow.core.constants.Constants;
import com.vbrug.workflow.core.exceptions.ProcessNotFoundException;
import com.vbrug.workflow.core.persistence.definition.process.service.ProcessService;
import com.vbrug.workflow.core.persistence.instance.job.entity.JobPO;
import com.vbrug.workflow.core.persistence.instance.job.entity.JobVO;
import com.vbrug.workflow.core.persistence.instance.job.mapper.JobMapper;
import com.vbrug.workflow.core.persistence.instance.job.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 作业Service实现类
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class JobServiceImpl implements JobService {

    @Resource
    private JobMapper mapper;

    @Resource
    private ProcessService processService;

    @Override
    public long newJob(int processId, Map<String, Object> params) {
        if (ObjectUtils.isNull(processService.find(processId)))
            throw new ProcessNotFoundException("流程：{} 不存在", processId);
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
    public int startExecuteJob(long id) {
        return mapper.update(JobVO.newInstance().setId(id).setState(Constants.JOB_STATE_EXECUTING).setUpdStartTime(true));
    }

    @Override
    public JobPO find(long id) {
        return mapper.find(id);
    }


    @Override
    public Map<String, Object> getJobContext(long jobId) {
        return null;
    }

    @Override
    public int completeJob(long jobId) {
        mapper.update(JobVO.newInstance().setId(jobId).setState(Constants.JOB_STATE_FINISH).setUpdEndTime(true));
        return mapper.migrateCompleteJob(jobId);
    }
}
