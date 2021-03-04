package com.leex.算法.求任意数的平方根;

import org.junit.Test;

/**
 * 求任意数的平方根
 * @Author : 86167
 * @Description : SquareRoot 2021/3/3 22:24 86167
 */
public class SquareRoot {

    @Test
    public void calculateSquareRoot(){

        // 任意数
        double number = 3d;

        // java算法
        System.out.println("Math.sqrt: " + Math.sqrt(number));
        // 我的算法
        System.out.println("MySqrt: " + mySqrt(number));
        // 牛顿迭代法 https://www.cnblogs.com/hezhiyao/p/7544593.html
        System.out.println("SqrtNet: " + newtonIterativeMethod(number));
        // 网络2 https://www.cnblogs.com/wanghongsen/p/9236071.html
        System.out.println("SqrtNet2: " + binSearchSqrt(number, 1d, number));

    }

    @Test
    public void a(){
        for (int i = 1; i <= 100; i++) {
            System.out.println("Math.sqrt: "+i+"的平方根" + Math.sqrt(i));
            System.out.println("MySqrt: "+i+"的平方根" + mySqrt(i));
        }
    }

    private double mySqrt(double number) {
        for (double j = 1; true; j++) {
            if ( j * j == number){
                return j;
            } else if ((j * j < number) && ((j + 1) * (j + 1) > number)) {
                return dichotomy(number, j , j+1);
            }
        }
    }

    private double dichotomy(double number, double left, double right) {

        double average = (left + right)/2;

        if(average * average == number){
            return average;
        } else if (average * average > number){
            if(Math.abs(average * average - number) < 0.00001){
                return average;
            }
            return dichotomy(number, left, average);
        } else {
            if(Math.abs(average * average - number) < 0.00001){
                return average;
            }
            return dichotomy(number, average, right);
        }
    }


    public static double newtonIterativeMethod(double a) {
        double x1 = 1, x2;
        //牛顿迭代公式
        x2 = x1 / 2.0 + a / (2 * x1);
        while (Math.abs(x2 - x1) > 1e-4) {
            x1 = x2;
            x2 = x1 / 2.0 + a / (2 * x1);
        }
        return x2;
    }

    private static double binSearchSqrt(Double num, Double left, Double right) {
        if (num == null) {
            throw new IllegalArgumentException("input invalid");
        }

        while (left < right) {
            double middle = (left + right) / 2.0;
            if (Math.abs(middle * middle - num) < 0.00001) {
                return middle;
            }
            if (middle * middle < num ) {
                left = middle;
                right = num / middle;
            } else if (middle * middle > num ) {
                right = middle;
                left = num / middle;
            }
        }
        return left;
    }
}
