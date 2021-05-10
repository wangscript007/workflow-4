package com.vbrug.workflow.core.exceptions;

import com.vbrug.fw4j.common.util.StringUtils;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class ProcessNotFoundException extends WorkFlowException{

    public ProcessNotFoundException() {
        super();
    }

    public ProcessNotFoundException(String message, Object... params) {
        super(StringUtils.replaceZW(message, "{}", params));
    }
}
