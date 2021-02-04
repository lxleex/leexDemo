package com.leex.reflection;

import com.leex.java8.lambda.Lambda;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @Author : 86167
 * @Description : reflectTest-2020/12/27 22:25-86167
 */

public class reflectTest {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<Lambda> stringBufferClass = Lambda.class;
        Annotation[] annotations = stringBufferClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());

            Resource resource = (Resource) annotation;
            System.out.println("resource.mappedName：" + resource.mappedName());
        }
        System.out.println("Arrays.toString(annotations):"+Arrays.toString(annotations));
        System.out.println("annotations.length:"+annotations.length);


        // 类信息相关
        System.out.println("getCanonicalName:" + stringBufferClass.getCanonicalName());
        System.out.println("getSimpleName:" + stringBufferClass.getSimpleName());


        System.out.println("desiredAssertionStatus:"+stringBufferClass.desiredAssertionStatus());



        StringBuffer stringBuffer = new StringBuffer();
        System.out.println();
        System.out.println(stringBuffer instanceof StringBuffer);

        // 创建实例
        /*Class<String> c = String.class;
        String str = c.newInstance();
        System.out.println("c.newInstance() :" + str);
        System.out.println(str instanceof String);*/

        //获取String所对应的Class对象
        Class<?> c = String.class;
        //获取String类带一个String参数的构造器
        Constructor constructor = c.getConstructor(String.class);
        //根据构造器创建实例
        Object obj = constructor.newInstance("23333");
        System.out.println(obj);
    }

}
