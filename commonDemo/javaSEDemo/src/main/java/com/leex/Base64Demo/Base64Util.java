package com.leex.Base64Demo;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author : Administrator
 * @Description : Base64Demo 2022/8/27 19:13 Administrator
 */
public class Base64Util {

    public static String imgInputStreamToBase64Str(InputStream in){

        byte[] data = null;
        // 读取图片字节数组
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.encodeBase64String(data);
    }

}
