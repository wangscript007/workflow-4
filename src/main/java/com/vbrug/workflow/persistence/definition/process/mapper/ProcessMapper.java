package com.vbrug.workflow.persistence.definition.process.mapper;

import com.vbrug.workflow.persistence.definition.process.po.ProcessPO;
import org.apache.ibatis.annotations.Select;

/**
 * 流程Mapper
 *
 * @author vbrug
 * @since 1.0.0
 */
public interface ProcessMapper {

    /**
     * 查询流程详情
     *
     * @param processId 流程编号
     * @return 流程持久化对象
     */
    @Select("select * from wf_def_process where id = #{0}")
    ProcessPO find(Integer processId);
}
