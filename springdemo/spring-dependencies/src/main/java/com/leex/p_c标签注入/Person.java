package com.leex.p_c标签注入;

/**
 * @Author : 86167
 * @Description : Person 2021/3/21 22:15 86167
 */
public class Person {

    private String name;
    private int age;
    private Person spouse;

    public Person() {
    }

    public Person(String name, int age, Person spouse) {
        this.name = name;
        this.age = age;
        this.spouse = spouse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }
}
