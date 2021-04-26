package com.vbrug.workflow.core.exceptions;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class WorkFlowException extends RuntimeException{

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
}
