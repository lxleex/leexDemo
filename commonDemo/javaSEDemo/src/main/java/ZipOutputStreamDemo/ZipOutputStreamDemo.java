package ZipOutputStreamDemo;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author : leex
 * @Description : ZipOutputStreamDemo 2022/5/18 22:12 leex
 */
public class ZipOutputStreamDemo {

    public static void main(String[] args) throws IOException {

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File("C:\\Users\\86167\\Desktop\\1.zip")));


        File file1 = new File("C:\\Users\\86167\\Desktop\\蛋糕装饰\\蛋糕图片\\0ebf0292a917923565b37e734a3632a.jpg");
        File file2 = new File("C:\\Users\\86167\\Desktop\\蛋糕装饰\\蛋糕图片\\2d143f916b664c7dab035b2c459b824.jpg");

        wietw(zipOutputStream, file1, "第一张.jpg");
        wietw(zipOutputStream, file2, "第2张.jpg");

        zipOutputStream.close();

    }

    private static void wietw(ZipOutputStream zipOutputStream, File file1, String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(file1)) {
            zipOutputStream.putNextEntry(new ZipEntry(fileName));
            byte[] b = new byte[1024];
            while ((fileInputStream.read(b)) != -1){
                zipOutputStream.write(b);
            }
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
