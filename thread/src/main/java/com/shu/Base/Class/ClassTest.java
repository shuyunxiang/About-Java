package com.shu.Base.Class;

public class ClassTest {
    public static void main(String[] args) {

        // 匿名内部类
        AnonymousInnerClass doSomeThing = new AnonymousInnerClass() {
            @Override
            public void doSth() {
                System.out.println("匿名内部类");
            }
        };
        doSomeThing.doSth();

        // 内部类
        // 需要通过外部类，去实例化内部类
        OutClass outClass = new OutClass();
        OutClass.InnerClass innerClass = outClass.new InnerClass();
        System.out.println(innerClass.innerName);
        System.out.println(innerClass.getInnerName());

        // 静态内部类可以直接实例化
        OutClass.InnerStaticClass innerStaticClass = new OutClass.InnerStaticClass();
        System.out.println(innerStaticClass.getInnerName());

        // 局部内部类（相当于局部变量）
        class LocalClass {
            final String LocalClassName = "局部内部类";

            String getLocalClassName() {
                return this.LocalClassName;
            }
        }
        LocalClass localClass = new LocalClass();
        String localClassName = localClass.LocalClassName;
        System.out.println(localClassName);
        String localClassName1 = localClass.getLocalClassName();
        System.out.println(localClassName1);

    }
}
