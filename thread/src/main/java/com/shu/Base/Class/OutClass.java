package com.shu.Base.Class;

public class OutClass {

    // 属性
    public String outClass = "我是外部类";

    // 方法
    public String getOutClass() {
        return this.outClass;
    }

    // 内部类
    public class InnerClass {
        public String innerName = "我是内部类";

        public String getInnerName() {
            return this.innerName;
        }
    }

    // 内部类
    public static class InnerStaticClass {
        public String innerName = "我是静态内部类";

        public String getInnerName() {
            return this.innerName;
        }
    }
}
