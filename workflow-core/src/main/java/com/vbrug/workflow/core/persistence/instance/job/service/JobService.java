package com.vbrug.workflow.core.persistence.instance.job.service;

import com.vbrug.workflow.core.persistence.instance.job.entity.JobPO;

import java.util.Map;

/**
 * 作业Service
 * @author vbrug
 * @since 1.0.0
 */
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
    int startExecuteJob(long id);

    /**
     * 查找作业信息
     * @param id 作业ID
     * @return 作业对象
     */
    JobPO find(long id);

    /**
     * 获取作业环境信息
     * @param jobId 作业编号
     * @return 结果
     */
    Map<String, Object> getJobContext(long jobId);

    /**
     * 完成作业
     * @param jobId 作业编号
     * @return 结果
     */
    int completeJob(long jobId);
}
