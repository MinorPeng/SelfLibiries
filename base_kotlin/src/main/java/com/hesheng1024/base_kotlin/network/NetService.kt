package com.hesheng1024.base_kotlin.network

/**
 *
 * @author hesheng1024
 * @date 2020/6/18 10:41
 */
class NetService(apiService: IApiService) {
    private val mIApiService: IApiService = apiService

    fun getApiService(): IApiService {
        return mIApiService
    }

}