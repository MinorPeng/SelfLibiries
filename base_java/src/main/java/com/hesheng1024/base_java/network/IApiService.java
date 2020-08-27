package com.hesheng1024.base_java.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author hesheng1024
 * @date 2019/5/22 15:25
 */
public interface IApiService {
    public static final String BASE_URL = "";

    /**
     * demo for get
     *
     * @param name
     * @return
     */
    @GET("/")
    Observable<Result<String>> demo(@Query("nam") String name);
}
