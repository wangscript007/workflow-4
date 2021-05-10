package com.vbrug.workflow.core.persistence.instance.task.mapper;

import com.vbrug.workflow.core.persistence.instance.task.dto.TaskDTO;
import com.vbrug.workflow.core.persistence.instance.task.po.TaskPO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * 完成任务
     * @param id     任务ID
     * @param params 结果参数
     * @param state  状态
     * @return 影响记录数
     */
    @Update("update wf_act_task set state = #{state}, params = #{resultParams}, end_time = now() where id = #{id} ")
    int completeTask(Long id, String resultParams, int state);

    TaskDTO gTaskDTO(Long taskId);

    List<TaskDTO> gTaskDTO(List<Long> taskIdList);

    List<TaskDTO> gTaskDTO(Long jobId, Integer state);

    @Select("select * from wf_act_task where id = #{0}")
    TaskPO find(Long taskId);

    /**
     * 更新作业状态
     * @param id    作业ID
     * @param state 状态
     * @return 影响记录数
     */
    int updateState(Integer jobId, Integer nodeId, Integer state);

    /**
     * 完成作业
     * @param id 作业Id
     * @return 影响记录数
     */
    int finishTask(Integer jobId, Integer nodeId);

    /**
     * 查询前置完成的任务数量
     * @param jobId       作业ID
     * @param preNodeList 前置节点Id 集合
     * @return 完成任务数量
     */
    int getFinishedPreTaskCount(Integer jobId, List<Integer> preNodeList);

}
