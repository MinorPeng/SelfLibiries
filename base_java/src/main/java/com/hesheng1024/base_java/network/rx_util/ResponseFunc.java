package com.hesheng1024.base_java.network.rx_util;

import com.hesheng1024.base_java.network.Result;
import com.hesheng1024.base_java.network.exception.ApiException;
import com.hesheng1024.base_java.network.exception.StatusCode;

import io.reactivex.rxjava3.functions.Function;

/**
 * Map 转换
 *
 * @author hesheng1024
 * @date 2019/5/22 16:59
 */
public class ResponseFunc<T> implements Function<Result<T>, T> {

    public ResponseFunc() { }

    @Override
    public T apply(Result<T> tResult) throws Exception {
        if (tResult.getCode() != StatusCode.SUCCESS) {
            throw new ApiException(tResult.getCode(), tResult.getMsg());
        }
        return tResult.getData();
    }
}
