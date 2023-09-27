package com.leex.ocrDemo;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 图片文字提取
 *
 * https://ai.baidu.com/ai-doc/OCR/Nkibizxlf
 *
 * @Author : Administrator
 * @Description : OcrClient 2022/8/27 20:27 Administrator
 */
public class OcrClient {
    //设置APPID/AK/SK
    public static final String APP_ID = "27189943";
    public static final String API_KEY = "dHBiKsCrlfESdAfVdxv68IpZ";
    public static final String SECRET_KEY = "s80ALNtaQhhHvWF19zQdhTesGsy2V7IU";

    public String basicGeneral(byte[] image) {

        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
        JSONObject res = client.basicGeneral(image, options);

        JSONArray words_result = res.getJSONArray("words_result");
        JSONObject jsonObject = words_result.getJSONObject(0);

        return jsonObject.getString("words");
    }

}
