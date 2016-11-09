package com.huituopin.common.exception;

public class DbRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8244484898835375196L;

    public DbRuntimeException() {
        super();
    }

    public DbRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbRuntimeException(String message) {
        super(message);
    }

    public DbRuntimeException(Throwable cause) {
        super(cause);
    }
}
