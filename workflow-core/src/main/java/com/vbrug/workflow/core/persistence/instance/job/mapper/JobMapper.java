package com.vbrug.workflow.core.persistence.instance.job.mapper;

import com.vbrug.workflow.core.persistence.instance.job.po.JobPO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 作业Mapper接口
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
     * 作业开始执行
     * @param id 作业ID
     * @return 结果
     */
    @Update("update wf_act_job set start_time = now(), state = #{1} where id = #{0}")
    int startExecuteJob(Integer id, Integer state);

    /**
     * 根据主键查询作业信息
     * @param id 作业ID
     * @return 作业信息
     */
    @Select("select * from wf_act_job where id = #{0}")
    JobPO find(int id);

    /**
     * 更新作业状态
     * @param id    作业ID
     * @param state 状态
     * @return 影响记录数
     */
    int updateState(Integer id, Integer state);

    /**
     * 完成作业
     * @param id 作业Id
     * @return 影响记录数
     */
    int finishJob(Integer id);
}
