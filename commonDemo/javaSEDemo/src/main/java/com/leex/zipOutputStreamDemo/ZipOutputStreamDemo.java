package com.leex.zipOutputStreamDemo;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author : leex
 * @Description : ZipOutputStreamDemo 2022/5/18 22:12 leex
 */
public class ZipOutputStreamDemo {

    public static void main(String[] args) throws IOException {

        ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(new File("C:\\Users\\86167\\Desktop\\1.zip").toPath()));


        File file1 = new File("C:\\Users\\Administrator\\Desktop\\重装备份\\leex\\Java 基础核心总结_副本.pdf");
        File file2 = new File("C:\\Users\\Administrator\\Desktop\\重装备份\\leex\\JAVA核心知识点整理.pdf");

        wietw(zipOutputStream, file1, "Java 基础核心总结_副本.pdf");
        wietw(zipOutputStream, file2, "JAVA核心知识点整理.pdf");

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
