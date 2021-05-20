package com.vbrug.workflow.core.persistence.instance.job.mapper;

import com.vbrug.workflow.core.persistence.instance.job.entity.JobPO;
import com.vbrug.workflow.core.persistence.instance.job.entity.JobVO;
import org.apache.ibatis.annotations.Select;

/**
 * 作业Mapper接口
 *
 * @author vbrug
 * @since 1.0.0
 */
public interface JobMapper {

    /**
     * 插入
     * @param po 实体对象
     * @return 影响记录数
     */
    int insert(JobPO po);

    /**
     * 根据主键查询作业信息
     * @param id 作业ID
     * @return 作业信息
     */
    @Select("select * from wf_act_job where id = #{0}")
    JobPO find(Long id);

    /**
     * 更新作业状态
     * @param jobVO 更新对象
     * @return 影响记录数
     */
    int update(JobVO jobVO);

    /**
     * 将作业完成的作业迁移到历史表
     * @param jobId 作业ID
     * @return 结果
     */
    int migrateCompleteJob(long jobId);
}
