package com.gavrilov.core.exception;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModelValidationException extends RuntimeException {
    private final String code;

    public ModelValidationException() {
        this("ModelValidationException", "");
    }

    public ModelValidationException(@Nonnull String code, @Nullable String message) {
        this(code, message, null /* cause */);
    }

    public ModelValidationException(@Nonnull String code, @Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ModelValidationException(@Nonnull String code, @Nullable Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
