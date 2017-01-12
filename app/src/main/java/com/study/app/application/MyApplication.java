package com.study.app.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 芮靖林
 * on 2017/1/10 21:47.
 */

public class MyApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static ExecutorService threadPool;
    public static boolean isLogin = false;//判定是否登录
    public static boolean mediaIsPalying = false;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        handler = new Handler();//创建Handle
        mainThreadId = Process.myTid();//得到主线程id
        threadPool = Executors.newFixedThreadPool(5);//创建线程池

    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
