package com.leex.screenShotDemo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author : Administrator
 * @Description : ScreenShotUtil 2022/8/27 18:58 Administrator
 */
public class ScreenShotUtil {

    public static byte[] screenShot(int x, int y, int width, int height){

        Rectangle screenRectangle = new Rectangle(x, y, width, height);

        Robot robot = null;
        byte[] bytes = new byte[1024 * 1024];
        try {
            robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRectangle);//使用Robot类提供的截屏方法，
            ByteArrayOutputStream outStream =new ByteArrayOutputStream();
            ImageIO.write(image, "png", outStream);
            //把outStream里的数据写入内存
            return outStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
