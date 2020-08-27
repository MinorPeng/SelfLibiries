package com.hesheng1024.base_java.network.exception;

/**
 * @author hesheng1024
 * @date 2019/5/22 16:30
 */
public class ApiException extends Exception {
    private final int mCode;
    private String mMessage;

    public ApiException(int code, String message) {
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
