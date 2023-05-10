package com.shu.ThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;

public class ThreadLocalTest {
    public static void main(String[] args) {
        // 测试同一个实验set两次
//        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
//        stringThreadLocal.set("1");
//        stringThreadLocal.set("2");
//
//        String s = stringThreadLocal.get();
//        System.out.println(s);
//
//        stringThreadLocal.remove();

        // 测试两个ThreadLocal同一个线程，ThreadLocalMap的大小
        ThreadLocal<String> stringThreadLocal0 = new ThreadLocal<>();
        stringThreadLocal0.set("0");
        ThreadLocal<String> stringThreadLocal1 = new ThreadLocal<>();
        stringThreadLocal1.set("1");
        ThreadLocal<String> stringThreadLocal2 = new ThreadLocal<>();
        stringThreadLocal2.set("2");

        // 线程内部，threadLocals(ThreadLocalMap)变量中table的大小和元素，证明了多了ThreadLocal都是存在同一个线程中
        Thread t = Thread.currentThread();
        System.out.println(t);


    }



}