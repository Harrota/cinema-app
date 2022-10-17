package org.dsyromiatnikov.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionWrapper {
    private String message;
    private Timestamp timestamp;

    public ExceptionWrapper(String message) {
        this.message = message;
        this.timestamp = Timestamp.from(Instant.now());
    }
}
