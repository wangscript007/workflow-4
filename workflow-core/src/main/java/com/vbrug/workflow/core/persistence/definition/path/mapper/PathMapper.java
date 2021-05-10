package com.vbrug.workflow.core.persistence.definition.path.mapper;

import com.vbrug.workflow.core.persistence.definition.path.po.PathPO;

import java.util.List;

/**
 * 路径Mapper
 * @author vbrug
 * @since 1.0.0
 */
public interface PathMapper {

    /**
     * 查询节点路径
     * @param fromNode     出发节点
     * @param toNode       目标节点
     * @param precondition 前置条件
     * @return 路径List
     */
    List<PathPO> queryPath(Integer fromNode, Integer toNode, Integer precondition);

}
