package com.hesheng1024.base_java;

/**
 * @author hesheng1024
 * @date 2020/5/6 18:46
 */
public final class Config {

    private Config() {}

    private static String sFilePath = null;

    public static String getFilePath() {
        if (sFilePath != null) {
            return sFilePath;
        }
        if (ContextHolder.getContext().getExternalCacheDir() != null) {
            sFilePath = ContextHolder.getContext().getExternalCacheDir().toString();
        } else {
            sFilePath = ContextHolder.getContext().getCacheDir().toString();
        }
        return sFilePath;
    }
}
