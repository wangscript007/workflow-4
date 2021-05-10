package com.vbrug.workflow.core.persistence.instance.job.service;

import com.vbrug.workflow.core.persistence.instance.job.po.JobPO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 作业Service
 * @author vbrug
 * @since 1.0.0
 */
@Service
public interface JobService {

    /**
     * 创建作业
     * @return 返回作业ID
     */
    long newJob(int processId, Map<String, Object> params);

    /**
     * 开始执行作业
     * @param id 作业ID
     * @return 结果
     */
    int startExecuteJob(int id);

    JobPO find(int id);

    Map<String, Object> getJobContext(int jobId);

    int completeJob(int jobId);
}
