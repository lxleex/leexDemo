package com.leex.easyExcel.noModleRead;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.io.File;

/**
 * @Author : leex
 * @Description : noModleRead 2021/6/27 19:25 leex
 */
public class noModleRead {

    @Test
    public void test(){

        String fileName = "C:\\Users\\86167\\Desktop\\test.xlsx";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read(fileName, new NoModleDataListener()).sheet().doRead();

    }

}
