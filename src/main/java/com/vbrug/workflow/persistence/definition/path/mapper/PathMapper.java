package com.vbrug.workflow.persistence.definition.path.mapper;

import com.vbrug.workflow.persistence.definition.path.po.PathPO;

import java.util.List;

/**
 * 路径Mapper
 *
 * @author vbrug
 * @since 1.0.0
 */
public interface PathMapper {

    /**
     * 查询节点路径
     *
     * @param processId流程ID
     * @param fromNode 出发节点
     * @param toNode 目标节点
     * @return 路径List
     */
    List<PathPO> queryPath(Integer processId, Integer fromNode, Integer toNode);

}
