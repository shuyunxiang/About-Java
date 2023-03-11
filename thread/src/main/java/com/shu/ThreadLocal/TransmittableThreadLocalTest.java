package com.shu.ThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalTest {
    public static void main(String[] args) {
        // 简单使用
//        simpleUse();

        // 修饰runnable
//        useForRunnable();

        // 修饰线程池
//        useForThreadPool();



    }

    /**
     * 修饰线程池
     */
    private static void useForThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        // =====================================================
        // 在父线程中设置
        context.set("value-set-in-parent");

        Runnable task = new Runnable() {

            @Override
            public void run() {
                System.out.println(context.get());
            }
        };
        Callable call = new Callable() {

            @Override
            public Object call() throws Exception {
                String s = context.get();
                System.out.println(s);
                return s;
            }
        };
        executorService.submit(task);
        executorService.submit(call);
    }

    /**
     * 修饰runnable使用
     */
    private static void useForRunnable() {

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        // =====================================================
        // 在父线程中设置
        context.set("value-set-in-parent");

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(context.get());
            }
        };
        // 额外的处理，生成修饰了的对象ttlRunnable
        Runnable ttlRunnable = TtlRunnable.get(task);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(ttlRunnable);

        executorService.shutdown();

        // =====================================================
        // Task中可以读取，值是"value-set-in-parent"
        String value = context.get();
    }

    /**
     * 简单使用
     */
    private static void simpleUse() {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        // =====================================================
        // 在父线程中设置
        context.set("value-set-in-parent");

        // =====================================================
        // 在子线程中可以读取，值是"value-set-in-parent"
        String value = context.get();
    }

}