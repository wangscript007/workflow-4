package com.vbrug.workflow.core.exceptions;

import com.vbrug.fw4j.common.util.StringUtils;

/**
 * @author vbrug
 * @since 1.0.0
 */
public class JobNotFoundException extends WorkFlowException{

    public JobNotFoundException() {
        super();
    }

    public JobNotFoundException(String message, Object... params) {
        super(StringUtils.replaceZW(message, "{}", params));
    }
}
