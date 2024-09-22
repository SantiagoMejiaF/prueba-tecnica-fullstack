package com.litethinking.application.exceptions.enums;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum ErrorCodes implements ApiErrorCode {

    NOT_FOUND ("001", "com.litethinking.application.exceptions.enums.ErrorCodes.NOT_FOUND"),
    BAD_REQUEST ("002", "com.litethinking.application.exceptions.enums.ErrorCodes.BAD_REQUEST"),
    UNKNOWN ("003", "com.litethinking.application.exceptions.enums.ErrorCodes.UNKNOWN"),
    CONNECTION ("004", "com.litethinking.application.exceptions.enums.ErrorCodes.CONNECTION"),;

    private static final String ERROR_CODE = "ERROR";
    private final String code;
    private final String localizedMessage;

    ErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return ERROR_CODE;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }
}
