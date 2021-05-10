package com.vbrug.workflow.core.constants;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class Constants {
    /*
     * 共用方法返回码
     */
    public final static Integer STATUS_CODE_0 = 0;                   // 成功
    public final static Integer STATUS_CODE_5 = 5;                   // 成功
    public final static Integer STATUS_CODE_9 = 9;                   // 异常


    /*
     * 作业状态（0-待执行，1-执行中，2-执行成功，9-失败）
     *
     */
    public final static Integer JOB_STATE_TODO      = 0;
    public final static Integer JOB_STATE_EXECUTING = 1;
    public final static Integer JOB_STATE_FINISH    = 2;
    public final static Integer JOB_STATE_FAILURE   = 9;

    /*
     * 前置条件状态码（1-正常，2-判断条件，9-失败）
     */

    public final static Integer TASK_PRECONDITION_NORMAL  = 1;
    public final static Integer TASK_PRECONDITION_TWO     = 2;
    public final static Integer TASK_PRECONDITION_FAILURE = 9;

    /*
     * 任务状态（1-执行中，2-成功，9-失败）
     */
    public final static Integer TASK_STATE_EXECUTING = 1;
    public final static Integer TASK_STATE_FINISH    = 2;
    public final static Integer TASK_STATE_FAILURE   = 9;

    /*
     * 判断
     */
    public final static String BOOLEAN_0 = "0";
    public final static String BOOLEAN_1 = "1";

    /**
     * TRUE、FALSE
     */
    public final static String TRUE  = "TRUE";
    public final static String FALSE = "FALSE";

    /**
     * 任务类型
     */
    public final static String TASK_START   = "STAT";
    public final static String TASK_EXTRACT = "EXTRACT";
    public final static String TASK_BSQL    = "BSQL";
    public final static String TASK_EXPORT  = "EXPORT";
    public final static String TASK_FILE_UD = "FILE_UD";
    public final static String TASK_HTTP    = "HTTP";
    public final static String TASK_IMPORT  = "IMPORT";
    public final static String TASK_END     = "END";
    public final static String TASK_PROCESS = "PROCESS";

    /**
     * 共用变量名
     */
    public final static String PARAM_JOB_ID         = "jobId";
    public final static String PARAM_PROCESS_ID     = "processId";
    public final static String PARAM_NODE_ID        = "nodeId";
    public final static String PARAM_NODE_TYPE      = "nodeType";
    public final static String PARAM_NODE_NAME      = "nodeName";
    public final static String PARAM_NODE_GROUP     = "nodeGroup";
    public final static String PARAM_NODE_LIST      = "nodeList";
    public final static String PARAM_FROM_NODE_LIST = "fromNodeList";
}
