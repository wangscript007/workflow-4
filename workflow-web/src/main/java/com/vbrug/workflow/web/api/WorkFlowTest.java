package com.vbrug.workflow.web.api;

import com.vbrug.fw4j.common.util.CollectionUtils;
import com.vbrug.workflow.core.WorkFlowEngine;
import com.vbrug.workflow.core.constants.Constants;
import com.vbrug.workflow.core.entity.Result;
import com.vbrug.workflow.core.persistence.instance.task.entity.TaskDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * @author vbrug
 * @since 1.0.0
 */
@RestController
@RequestMapping("wftest")
public class WorkFlowTest {


    @RequestMapping("doProcess")
    public String doProcess(int processId) {
        WorkFlowEngine flowEngine = WorkFlowEngine.getInstance();
        // 创建任务
        long jobId = flowEngine.newJob(processId,
                CollectionUtils.createValueMap().add("jobData1", "111").add("jobData2", "TWO").build());
        // 执行开始节点
        Result result      = flowEngine.doStartTask(jobId);
        Long   startTaskId = result.dataWrap().getLong();

        // 完成节点
        Queue<Long> todoTaskQueue = new LinkedList<>();
        todoTaskQueue.add(startTaskId);
        while (!todoTaskQueue.isEmpty()) {
            Long poll = todoTaskQueue.poll();
            flowEngine.completeTask(poll,
                    CollectionUtils.createValueMap().add("status", poll).add("任务参数测试", "000" + poll).build());
            // 获取下一待执行任务
            Result        result1  = flowEngine.gTodoTasks(startTaskId, Constants.TASK_PRECONDITION_TRUE);
            List<TaskDTO> taskDTOS = result1.dataWrap().getTaskDTOS();
            if (!CollectionUtils.isEmpty(taskDTOS)) {
                if (taskDTOS.size() == 1 && Objects.equals(Constants.NODE_TYPE_END, taskDTOS.get(0).getNodeType())) {
                    flowEngine.completeTask(taskDTOS.get(0).getTaskId(), null);
                    flowEngine.completeJob(jobId);
                    System.out.println("流程结束");
                } else {
                    taskDTOS.forEach(x -> todoTaskQueue.add(x.getTaskId()));
                }
            }
        }

        return "执行成功";

    }
}
