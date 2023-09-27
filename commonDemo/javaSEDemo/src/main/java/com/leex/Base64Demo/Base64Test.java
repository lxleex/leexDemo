package com.leex.Base64Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author : Administrator
 * @Description : Base64Test 2022/8/27 19:15 Administrator
 */
public class Base64Test {


    public static void main(String[] args) throws FileNotFoundException {


        File file = new File("javaSEDemo/src/main/java/com/leex/screenShotDemo/100dff9e98704109bbd0191a0a38f16d.png");

        System.out.println(Base64Util.imgInputStreamToBase64Str(new FileInputStream(file)));


    }

}
