package com.hesheng1024.base_kotlin.network

import com.hesheng1024.base.utils.ContextHolder
import com.hesheng1024.base.utils.isNetConnected
import com.hesheng1024.base_kotlin.BuildConfig
import com.hesheng1024.base_kotlin.getFilePath
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *
 * @author hesheng1024
 * @date 2020/6/18 10:12
 */
object RetrofitManager {

    val HTTP_CLIENT = initClient()
    val API_SERVICE = build(IApiService.BASE_URL, HTTP_CLIENT).create(IApiService::class.java)
    val NET_SERVICE = NetService(API_SERVICE)

    fun getNetService(): NetService? {
        return NET_SERVICE
    }

    fun <T> createService(url: String, tClass: Class<T>?): T {
        return build(url, HTTP_CLIENT).create(tClass)
    }

    fun <T> createService(url: String, okHttpClient: OkHttpClient, tClass: Class<T>?): T {
        return build(url, okHttpClient).create(tClass)
    }

    private fun build(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        synchronized(Retrofit::class.java) {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    private fun initClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val cacheFile =
            File(getFilePath() + File.separator + "NetCache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50)
        val cacheInterceptor =
            Interceptor { chain: Interceptor.Chain ->
                var request = chain.request()
                val isConnect = isNetConnected(ContextHolder.getMainContext())
                if (!isConnect) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
                }
                val response = chain.proceed(request)
                val newBuilder = response.newBuilder()
                if (!isConnect) {
                    val maxAge = 0
                    // 有网络时 设置缓存超时时间0个小时
                    newBuilder.header("Cache-Control", "public, max-age=$maxAge")
                } else {
                    val maxStale = 60 * 60 * 24 * 28
                    // 无网络时，设置超时为4周
                    newBuilder.header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$maxStale"
                    )
                }
                newBuilder.build()
            }
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC)
        builder.cache(cache).addInterceptor(cacheInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }
}