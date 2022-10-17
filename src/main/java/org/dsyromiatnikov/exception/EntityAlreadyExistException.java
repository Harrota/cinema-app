package org.dsyromiatnikov.exception;

import static java.lang.String.format;

public class EntityAlreadyExistException extends RuntimeException {

    private static final String MESSAGE = "'%s' with %s already exists.";

    public EntityAlreadyExistException(String criteria) {
        super(format(MESSAGE, "Entity", criteria));
    }
}
