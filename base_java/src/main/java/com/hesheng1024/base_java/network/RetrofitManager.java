package com.hesheng1024.base_java.network;

import androidx.annotation.NonNull;

import com.hesheng1024.base_java.BuildConfig;
import com.hesheng1024.base_java.Config;
import com.hesheng1024.base_java.ContextHolder;
import com.hesheng1024.base_java.utils.NetWorkUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit管理类 负责构建Retrofit和配置Retrofit
 *
 * @author hesheng1024
 * @date 2019/5/22 15:22
 */
public class RetrofitManager {

    private RetrofitManager() {
    }

    /**
     * 获取具体网络请求 NetService
     *
     * @return NetService
     */
    public static NetService getNetService() {
        return Singleton.NET_SERVICE;
    }

    public static <T> T createService(@NonNull String url, Class<T> tClass) {
        return build(url, Singleton.HTTP_CLIENT).create(tClass);
    }

    public static <T> T createService(@NonNull String url, @NonNull OkHttpClient okHttpClient, Class<T> tClass) {
        return build(url, okHttpClient).create(tClass);
    }

    private static Retrofit build(@NonNull String baseUrl, @NonNull OkHttpClient okHttpClient) {
        synchronized (Retrofit.class) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private static OkHttpClient initClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        final File cacheFile = new File(Config.getFilePath() + File.separator + "NetCache");
        final Cache cache = new Cache(cacheFile, (1024 * 1024 * 50));
        final Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            final boolean isConnect = NetWorkUtil.isNetWorkConnected(ContextHolder.getContext());
            if (!isConnect) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            final Response response = chain.proceed(request);
            final Response.Builder newBuilder = response.newBuilder();
            if (!isConnect) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
            } else {
                int maxStale = 60 * 60 * 24 * 28;
                // 无网络时，设置超时为4周
                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }
            return newBuilder.build();
        };
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);
        builder.cache(cache).addInterceptor(cacheInterceptor).addNetworkInterceptor(loggingInterceptor);
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    private static class Singleton {
        private static final OkHttpClient HTTP_CLIENT = initClient();
        private static final IApiService API_SERVICE = build(IApiService.BASE_URL, HTTP_CLIENT).create(IApiService.class);
        private static final NetService NET_SERVICE = new NetService(API_SERVICE);
    }
}
