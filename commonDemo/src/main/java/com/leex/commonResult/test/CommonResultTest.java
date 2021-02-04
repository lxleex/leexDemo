package com.leex.commonResult.test;

import com.leex.commonResult.ClientTemplate;
import com.leex.commonResult.modelA.ModelAFacade;
import com.leex.commonResult.modelB.ModelBFacade;

/**
 * @Author : 86167
 * @Description : CommonResultTest 2021/2/3 23:21 86167
 */
public class CommonResultTest {

    private static ModelAFacade maf = new ModelAFacade();
    private static ModelBFacade mbf = new ModelBFacade();

    public static void main(String[] args) {
        facadeA();
        facadeB();
    }


    private static void facadeA() {

        final String param = "A";

        ClientTemplate.execute(() -> maf.facadeA(param), "ModelBFacade", "facadeB", param);

    }


    private static void facadeB() {

        final String param = "B";

        ClientTemplate.execute(() -> mbf.facadeB(param), "ModelAFacade", "facadeA", param);

    }



}
