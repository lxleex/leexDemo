package commonResult.modelB;

/**
 * @Author : 86167
 * @Description : ModelAFacade 2021/2/3 23:23 86167
 */
public class ModelBFacade {


    public CommonResult<String> facadeB(String str){
        return new CommonResult<String>("ModelBFacade的facadeB方法, 参数" + str);
    }

}
