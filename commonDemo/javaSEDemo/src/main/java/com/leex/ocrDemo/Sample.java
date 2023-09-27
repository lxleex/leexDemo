package com.leex.ocrDemo;

import java.util.*;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "27189943";
    public static final String API_KEY = "dHBiKsCrlfESdAfVdxv68IpZ";
    public static final String SECRET_KEY = "s80ALNtaQhhHvWF19zQdhTesGsy2V7IU";

    public static void main(String[] args) {
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

        // 调用接口
        String path = "javaSEDemo/src/main/java/com/leex/screenShotDemo/100dff9e98704109bbd0191a0a38f16d.png";
        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
        JSONObject res = client.basicGeneral(path, options);
        
        System.out.println(res.toString(2));

    }
}