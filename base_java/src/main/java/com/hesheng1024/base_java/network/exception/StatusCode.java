package com.hesheng1024.base_java.network.exception;

/**
 * 对应HTTP的状态码
 *
 * @author hesheng1024
 * @date 2020/5/29 9:28
 */
public final class StatusCode {
    public static final int UNKONWN = -1;
    public static final int SUCCESS = 200;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;
}
