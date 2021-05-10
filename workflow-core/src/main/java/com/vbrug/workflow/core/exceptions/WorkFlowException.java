package com.vbrug.workflow.core.exceptions;

import com.vbrug.fw4j.common.util.StringUtils;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class WorkFlowException extends RuntimeException {

    public WorkFlowException() {
        super();
    }

    public WorkFlowException(String message) {
        super(message);
    }

    public WorkFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkFlowException(Throwable cause) {
        super(cause);
    }

    public WorkFlowException(Throwable cause, String message, String... params) {
        super(StringUtils.replaceZW(message, "{}", params), cause);
    }

    public WorkFlowException(String message, String... params) {
        super(StringUtils.replaceZW(message, "{}", params));
    }
}
