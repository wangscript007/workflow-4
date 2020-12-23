package com.vbrug.workflow.persistence.definition.node.mapper;

import com.vbrug.workflow.persistence.definition.node.po.NodePO;
import org.apache.ibatis.annotations.Select;

/**
 * 节点查询Mapper
 *
 * @author vbrug
 * @since 1.0.0
 */
public interface NodeMapper {

    /**
     * 查询流程开始节点
     *
     * @param processId 流程编号
     * @return 节点对象
     */
    @Select("select * from wf_def_node where process_id = #{0} and type = 'START'")
    NodePO findStartNode(Integer processId);

    /**
     * 查询节点详情
     *
     * @param processId 流程编号
     * @param id 节点编号
     * @return 节点对象
     */
    @Select("select * from wf_def_node where process_id = #{processId} and id = #{id}")
    NodePO find(Integer processId, Integer id);



}
