package com.xiaoteng.blog.utils;

import javassist.bytecode.analysis.Executor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private final static Integer THREAD_NUM = 10;

    private static ExecutorService service;

    private static ExecutorService getService() {
        if (ThreadPool.service == null) {
            ThreadPool.service = Executors.newFixedThreadPool(ThreadPool.THREAD_NUM);
        }
        return ThreadPool.service;
    }

    public static void push(Runnable runnable) {
        ThreadPool.getService().submit(runnable);
    }

}
