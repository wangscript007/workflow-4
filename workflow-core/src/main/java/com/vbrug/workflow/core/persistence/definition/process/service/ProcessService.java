package com.vbrug.workflow.core.persistence.definition.process.service;

import com.vbrug.workflow.core.persistence.definition.process.mapper.ProcessMapper;
import com.vbrug.workflow.core.persistence.definition.process.po.ProcessPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 流程Service
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class ProcessService {

    @Resource
    private ProcessMapper mapper;

    @Resource
    private ProcessService processService;


    public int start(int processId) {

    }

    /**
     * 查询流程详情
     * @param processId 流程编号
     * @return 流程持久化对象
     */
    public ProcessPO find(Integer processId) {
        return mapper.find(processId);
    }

}
