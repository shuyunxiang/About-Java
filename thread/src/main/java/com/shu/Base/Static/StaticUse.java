package com.shu.Base.Static;

public class StaticUse {

    // 静态变量
    public static String str = "静态变量";

    // 静态方法
    public static void getString() {
        System.out.println("方法获取" + str);
    }

    // 静态代码块
    static {
        System.out.println("初始化执行");
    }

    // 内部类
    static class InnerClass {
        String getStr() {
            return "静态内部类的方法";
        }
    }
}
