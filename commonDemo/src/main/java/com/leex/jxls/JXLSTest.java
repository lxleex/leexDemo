package com.leex.jxls;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @Author : leex
 * @Description : JXLSTest 2021/11/3 23:45 leex
 */
public class JXLSTest {

    @Test
    public void test() throws Exception {
        InputStream in = new FileInputStream("C:\\Users\\86167\\Desktop\\test.xls");
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("C:\\Users\\86167\\Desktop\\test.xls");

        Map<String, Object> context = Maps.newHashMap();
        context.put("test", "第一次使用jxls");

        OutputStream outputStream = ExcelJXLSUtil.renderTemplate(in, context);



    }


}
