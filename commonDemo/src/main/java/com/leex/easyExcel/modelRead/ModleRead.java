package com.leex.easyExcel.modelRead;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.leex.easyExcel.modelRead.model.ExcelMode;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Author : leex
 * @Description : ModleRead 2021/6/27 21:04 leex
 */
public class ModleRead {

    @Test
    public void test() throws FileNotFoundException {

        String fileName = "C:\\Users\\86167\\Desktop\\test.xlsx";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        //EasyExcel.read(fileName, new ModleDataListener()).sheet().doRead();


        EasyExcel.read(new FileInputStream(fileName), ExcelMode.class, new ModleDataListener()).sheet(0).headRowNumber(0).doRead();
        /*ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);

        readSheet.;
        List<List<String>> head = readSheet.getHead();

        System.out.println("head:"+head);

        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();*/

    }


}
