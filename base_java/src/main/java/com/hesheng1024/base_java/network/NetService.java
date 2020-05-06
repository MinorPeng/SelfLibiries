package com.hesheng1024.base_java.network;

/**
 * 具体网络请求类
 * @author hesheng1024
 * @date 2019/5/22 16:08
 */
public class NetService {
    private final IApiService mIApiService;

    NetService(IApiService apiService) {
        this.mIApiService = apiService;
    }

    public IApiService getApiService() {
        return mIApiService;
    }
}
