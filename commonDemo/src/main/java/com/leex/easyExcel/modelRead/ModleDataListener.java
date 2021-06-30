package com.leex.easyExcel.modelRead;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.leex.easyExcel.modelRead.model.ExcelMode;
import com.leex.easyExcel.noModleRead.NoModleDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;
import java.util.List;

/**
 * @Author : leex
 * @Description : ModleDataListener 2021/6/27 20:58 leex
 */
public class ModleDataListener extends AnalysisEventListener<ExcelMode> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModleDataListener.class);

    List<ExcelMode> list = Lists.newArrayList();

    @Override
    public void invoke(ExcelMode data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        LOGGER.info(String.format("数据解析完成，一共有[%s]条数据", list.size()));
    }
}
