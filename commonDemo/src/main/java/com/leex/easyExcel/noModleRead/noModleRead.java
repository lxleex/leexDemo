package com.leex.easyExcel.noModleRead;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Author : leex
 * @Description : noModleRead 2021/6/27 19:25 leex
 */
public class noModleRead {

    @Test
    public void test() throws FileNotFoundException {

        String fileName = "C:\\Users\\Administrator\\Desktop\\1111.xls";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        //EasyExcel.read(fileName, new NoModleDataListener()).sheet().doRead();

        ExcelReader excelReader = new ExcelReader(new FileInputStream(fileName), ExcelTypeEnum.XLS, new NoModleDataListener());

        ReadSheet readSheet = EasyExcel.readSheet(0).build();

        excelReader.read(readSheet);

        List<List<String>> head = readSheet.getHead();

        System.out.println("head:"+head);

        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

}
