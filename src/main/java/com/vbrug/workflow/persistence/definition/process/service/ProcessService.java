package com.vbrug.workflow.persistence.definition.process.service;

import com.vbrug.workflow.persistence.definition.process.mapper.ProcessMapper;
import com.vbrug.workflow.persistence.definition.process.po.ProcessPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 流程Service
 *
 * @author vbrug
 * @since 1.0.0
 */
@Service
public class ProcessService {

    @Resource
    private ProcessMapper mapper;

    /**
     * 查询流程详情
     *
     * @param processId 流程编号
     * @return 流程持久化对象
     */
    public ProcessPO find(Integer processId){
        return mapper.find(processId);
    }

}
