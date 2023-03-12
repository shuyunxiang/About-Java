package com.shu.Base.Static;

public class StaticTest {

    static StaticUse staticUse = new StaticUse();

    public static void main(String[] args) {
//        String str = StaticUse.str;
//        System.out.println(str);

        // 变量唯一性确认
//        StaticUse staticUse0 = new StaticUse();
//        StaticUse staticUse1 = new StaticUse();

//        StaticUse.getString();

        // 不初始化也能执行
//        StaticUse staticUse = new StaticUse();

        // 静态内部类
//        StaticUse.InnerClass innerClass = new StaticUse.InnerClass();
//        System.out.println(innerClass.getStr());

        // 静态变量this的确认
        StaticUse own0 = staticUse.getOwn();
        StaticUse own1 = staticUse.getOwn();
        Thread thread = new Thread(()->{
            staticUse.getOwn();
        });
        thread.start();

    }
}
