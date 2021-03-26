package com.leex.内部类注入;

/**
 * @Author : 86167
 * @Description : OuterClass 2021/3/21 21:46 86167
 */
public class OuterClass {

    private String someValue;

    private InnerClass innerClass;

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }

    class InnerClass{

        private String name;

        private int age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
