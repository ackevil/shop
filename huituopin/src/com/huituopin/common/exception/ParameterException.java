package com.huituopin.common.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParameterException extends Exception {

    private static final long serialVersionUID = -4482150289956980882L;

    private Map<String, String> errorParameters = new ConcurrentHashMap<String, String>();

    public Map<String, String> getErrorParameters() {
        return errorParameters;
    }

    public void setErrorParameters(Map<String, String> errorParameters) {
        this.errorParameters = errorParameters;
    }

    public void addErrorParameter(String fieldName, String errorMessage) {
        if (errorParameters == null) {
            errorParameters = new HashMap<String, String>();
        }
        errorParameters.put(fieldName, errorMessage);
    }

    public boolean hasErrorParameter() {
        if (errorParameters == null || errorParameters.isEmpty()) {
            return false;
        }
        return true;
    }
}
