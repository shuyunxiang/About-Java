package com.shu.Base.Static;

public class StaticUse {

    // 静态变量
    public static String str = "静态变量";

    // 私有静态变量
    // 关于TransmittableThreadLocal的holder保存线程唯一标记的方式
    private static String strPrivate = "私有静态变量";

    // 静态方法
    public static void getString() {
        System.out.println("方法获取" + str);
    }

    // 静态代码块
    static {
        System.out.println("初始化执行");
    }

    // 静态内部类
    static class InnerClass {
        String getStr() {
            return "静态内部类的方法";
        }
    }

    public StaticUse() {
        int i = System.identityHashCode(strPrivate);
        System.out.println(i);
    }

    public StaticUse getOwn() {
        System.out.println(System.identityHashCode(this));
        return this;
    }
}
