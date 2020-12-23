package com.vbrug.workflow.persistence.definition.node.service;

import com.vbrug.workflow.persistence.definition.node.po.NodePO;
import com.vbrug.workflow.persistence.definition.node.mapper.NodeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 节点Service
 *
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class NodeService {

    @Resource
    private NodeMapper mapper;

    /**
     * 查询流程开始节点
     *
     * @param processId 流程编号
     * @return 节点对象
     */
    public NodePO findStartNode(Integer processId) {
        return mapper.findStartNode(processId);
    }

    /**
     * 查询节点详情
     *
     * @param processId 流程编号
     * @param id 节点编号
     * @return 节点对象
     */
    public NodePO find(Integer processId, Integer id) {
        return mapper.find(processId, id);
    }
}
