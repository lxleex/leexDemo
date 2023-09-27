package com.leex.screenShotDemo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

/**
 * @Author : Administrator
 * @Description : ScreenShot 2022/8/27 18:28 Administrator
 */
public class ScreenShot {


    public static void main(String[] args) throws AWTException {
        String fileName = "javaSEDemo/src/main/java/com/leex/screenShotDemo";    //是个文件夹名字
        System.out.println(screenShot(fileName));
    }

    public static final Logger logger = LogManager.getLogger(ScreenShot.class);


    public static String screenShot(String fileName) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕大小
        Rectangle screenRectangle = new Rectangle(1135, 110, 521, 129);//根据屏幕大小创建一个矩形
        String randomName = UUID.randomUUID().toString().replace("-", "");
        String name = randomName + ".png";//jpg等也可
        // 截图保存的路径
        File screenFile = new File(fileName);
        if (!screenFile.exists()) {
            screenFile.mkdirs();//创建文件路径
        }
        Robot robot;
        String path = "";
        boolean b = false;
        try {
            robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRectangle);//使用Robot类提供的截屏方法，
            File f = new File(screenFile, name);
            b = ImageIO.write(image, "png", f);
        } catch (Exception e) {
            logger.error("error : ", e);
        }

        if (b) {
            path = fileName + File.separator + name;
        }
        return path;
    }

}
