package com.gavrilov.core.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

public class ExceptionData {
    private final String error;
    private final String message;

    @JsonCreator
    public ExceptionData(@Nonnull @JsonProperty("error") String error, @Nonnull @JsonProperty("message") String message) {
        this.error = error;
        this.message = message;
    }

    @Nonnull
    public String getError() {
        return error;
    }

    @Nonnull
    public String getMessage() {
        return message;
    }
}
