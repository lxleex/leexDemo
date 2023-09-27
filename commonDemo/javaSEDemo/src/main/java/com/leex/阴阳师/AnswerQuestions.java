package com.leex.阴阳师;

import com.leex.ocrDemo.OcrClient;
import com.leex.screenShotDemo.ScreenShotUtil;

import java.sql.Array;
import java.util.*;

/**
 * @Author : Administrator
 * @Description : AnswerQuestions 2022/8/27 19:59 Administrator
 */
public class AnswerQuestions {

    private static List<String> list = new ArrayList();

    private static OcrClient ocrClient = new OcrClient();

    static {
        list.add("、");
        list.add("的");
        list.add("或");
    }

    public static void main(String[] args) {

        // 获取图片
        byte[] bytes = ScreenShotUtil.screenShot(1135, 110, 521, 129);
        // 获取第一行
        String question = ocrClient.basicGeneral(bytes);

        // 字符串加工
        question = question.substring(question.indexOf("，") + 1);
        question = question.replace("？", "");

        List<String> keywords = new ArrayList<>();
        for (String s : list) {
            if(question.contains(s)){
                keywords.addAll(Arrays.asList(question.split(s)));
            }
        }

        System.out.println(question);
        Map<String, String> questionBank = QuestionBank.getQuestionBank();


        for (String keyword : keywords) {
            Map<String, String> questionBankTemp = new HashMap<>();
            for (Map.Entry<String, String> entry : questionBank.entrySet()) {
                if(entry.getKey().contains(keyword)){
                    questionBankTemp.put(entry.getKey(), entry.getValue());
                }
            }
            questionBank = questionBankTemp;
        }

        for (Map.Entry<String, String> stringStringEntry : questionBank.entrySet()) {
            System.out.println(stringStringEntry.getKey() + "  -----  " + stringStringEntry.getValue());
        }

    }


}
