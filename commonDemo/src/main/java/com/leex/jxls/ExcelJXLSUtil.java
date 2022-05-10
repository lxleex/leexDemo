package com.leex.jxls;

import FacadeTemplate.Test;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;

import java.io.*;
import java.util.Map;

/**
 * @Author : leex
 * @Description : ExcelJXLSUtil 2021/11/3 23:16 leex
 */
public class ExcelJXLSUtil {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ExcelJXLSUtil.class);

    public static OutputStream renderTemplate(InputStream template, Map<String, Object> context) {
        try(InputStream in = template){
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Transformer transformer = TransformerFactory.createTransformer(in, out);
            if(transformer == null) {
                throw new RuntimeException("解析异常");
            }
            //transformer.getTransformationConfig().setExpressionEvaluator(SPEL_);
            transformer.setFullFormulaRecalculationOnOpening(true);

            Context jxlsContext = new Context(context);

            JxlsHelper.getInstance().setProcessFormulas(false);
            JxlsHelper.getInstance().processTemplate(jxlsContext, transformer);

            return out;
        } catch (IOException e) {

            throw new RuntimeException("渲染Excel模板异常");
        }
    }

}
