package com.shu.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class UseForCompletableFuture {
    public static void main(String[] args) throws Exception {
        CompletableFuture futrue = new CompletableFuture();
        futrue.complete("complete1");
        System.out.println(futrue.get());
    }
}
