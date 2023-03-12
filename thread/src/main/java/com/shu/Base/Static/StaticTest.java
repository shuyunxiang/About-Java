package com.shu.Base.Static;

public class StaticTest {
    public static void main(String[] args) {
        String str = StaticUse.str;
        System.out.println(str);

        StaticUse.getString();

        // 不初始化也能执行
//        StaticUse staticUse = new StaticUse();

        // 静态内部类
        StaticUse.InnerClass innerClass = new StaticUse.InnerClass();
        System.out.println(innerClass.getStr());
    }
}
