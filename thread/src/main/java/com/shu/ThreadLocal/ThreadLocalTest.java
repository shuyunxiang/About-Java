package com.shu.ThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("1");
        String s = stringThreadLocal.get();
        System.out.println(s);
        stringThreadLocal.remove();
    }

}