package com.hesheng1024.base_java.network;

import com.google.gson.annotations.SerializedName;

/**
 * @author hesheng1024
 * @date 2019/5/22 16:23
 */
public class Result<T> {

    @SerializedName("code")
    private int mCode;
    @SerializedName("data")
    private T mData;
    @SerializedName("msg")
    private String mMsg;

    public T getData() {
        return mData;
    }

    public int getCode() {
        return mCode;
    }

    public String getMsg() {
        return mMsg;
    }
}
