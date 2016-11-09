package com.huituopin.common.exception;

public class BusinessException extends Exception {

    private static final long serialVersionUID = 7059719413326484571L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
