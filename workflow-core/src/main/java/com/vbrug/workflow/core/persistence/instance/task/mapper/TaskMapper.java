package com.vbrug.workflow.core.persistence.instance.task.mapper;

import com.vbrug.workflow.core.persistence.instance.task.entity.TaskDTO;
import com.vbrug.workflow.core.persistence.instance.task.entity.TaskPO;
import com.vbrug.workflow.core.persistence.instance.task.entity.TaskVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 任务Mapper接口
 * @author vbrug
 * @since 1.0.0
 */
public interface TaskMapper {


    /**
     * 插入
     * @param po 实体对象
     * @return 影响记录数
     */
    int insert(TaskPO po);

    /**
     * 更新任务
     * @param vo 更新实体对象
     * @return 影响记录数
     */
    int update(TaskVO vo);

    /**
     * 更新任务重跑次数
     * @param taskIds 任务ID
     * @return 影响记录数
     */
    int updateRetryTimes(Long... taskIds);

    /**
     * 获取任务信息
     * @param taskIds 任务ID
     * @return 任务信息
     */
    List<TaskDTO> gTaskDTO(Long... taskIds);

    /**
     * 查询作业任务信息集合
     * @param jobId 作业ID
     * @return 任务信息集合
     */
    List<TaskDTO> gTaskDTOByJob(Long jobId, Integer state);

    /**
     * 查询任务
     * @param vo 更新实体对象
     * @return 任务数量
     */
    List<TaskPO> query(TaskVO vo);

    /**
     * 查询任务信息
     * @param taskId 任务ID
     * @return 任务info
     */
    @Select("select * from wf_act_task where id = #{0}")
    TaskPO find(Long taskId);

    /**
     * 将作业完成的任务迁移到历史表
     * @param jobId 作业ID
     * @return 结果
     */
    int migrateCompleteTask(long jobId);
}
