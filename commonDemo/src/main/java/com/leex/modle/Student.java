package com.leex.modle;

import com.leex.modle.basemodel.BaseModel;

/**
 * @Author : leex
 * @Description : Student 2022/1/2 20:00 leex
 */
public class Student extends BaseModel {


    private String name;
    private int number;
    private int score;

    public Student (){
    }
    public Student (String name,int number,int score){
        //super();
        this.name = name;
        this.number = number;
        this.score = score;
    }

    public void show(){
        System.out.println("姓名：" + name + "学号： " + number + "成绩" + score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
