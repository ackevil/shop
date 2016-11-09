package com.huituopin.common.exception;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbRuntimeException extends RuntimeException {

    @Id
	@GeneratedValue
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
