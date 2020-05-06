package com.hesheng1024.base_java.network.util;

import androidx.annotation.NonNull;

/**
 * @author hesheng1024
 * @date 2019/5/22 16:30
 */
public class ApiException extends Exception {
    private final int mCode;
    private final String mMessage;

    public ApiException(int code, @NonNull String message) {
        this.mCode = code;
        this.mMessage = message;
    }

    public int getCode() {
        return mCode;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
