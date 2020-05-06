package com.hesheng1024.base_java.network.util;

import com.hesheng1024.base_java.network.Result;

import io.reactivex.rxjava3.functions.Function;

/**
 * Map 转换
 *
 * @author hesheng1024
 * @date 2019/5/22 16:59
 */
public class ResponseFunc<T> implements Function<Result<T>, T> {

    private ResponseFunc() { }

    @Override
    public T apply(Result<T> tResult) throws Exception {
        return tResult.getData();
    }
}
