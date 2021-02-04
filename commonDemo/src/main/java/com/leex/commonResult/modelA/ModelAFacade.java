package com.leex.commonResult.modelA;

/**
 * @Author : 86167
 * @Description : ModelAFacade 2021/2/3 23:23 86167
 */
public class ModelAFacade {


    public CommonResult<String> facadeA(String str){
        return new CommonResult<String>("ModelAFacade的facadeA方法, 参数" + str);
    }

}
