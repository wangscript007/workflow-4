package com.vbrug.workflow.persistence.instance.job.mapper;

import com.vbrug.workflow.persistence.instance.job.po.JobPO;

/**
 * 作业Mapper接口
 *
 * @author vbrug
 * @since 1.0.0
 */
public interface JobMapper {

    /**
     * 获取下一作业ID
     *
     * @return 作业ID
     */
    int getNextId();

    /**
     * 插入
     *
     * @param po 实体对象
     * @return 影响记录数
     */
    int insert(JobPO po);

    /**
     * 更新作业状态
     *
     * @param id 作业ID
     * @param state 状态
     * @return 影响记录数
     */
    int updateState(Integer id, Integer state);

    /**
     * 完成作业
     *
     * @param id 作业Id
     * @return 影响记录数
     */
    int finishJob(Integer id);
}
