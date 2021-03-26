package com.leex.构造函数注入;

/**
 * @Author : 86167
 * @Description : MyClass 2021/3/21 15:03 86167
 */
public class MyClass {

    private int anInt;

    private String str;

    private AnAttribute anAttribute;

    public MyClass(int anInt, String str, AnAttribute anAttribute) {
        this.anInt = anInt;
        this.str = str;
        this.anAttribute = anAttribute;
    }


    @Override
    public String toString() {
        return "MyClass{" +
                "anInt=" + anInt +
                ", str='" + str + '\'' +
                ", anAttribute=" + anAttribute.process() +
                '}';
    }
}
