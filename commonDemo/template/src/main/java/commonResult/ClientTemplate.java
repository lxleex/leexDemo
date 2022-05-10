package commonResult;

import com.Util.GsonUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

/**
 * @Author : 86167
 * @Description : ClientTemplate-2021/2/3 21:58-86167
 */
public class ClientTemplate {

    /**
     * 接口调用模板
     *
     * @param    call            回调函数
     * @param    platformName    平台名称
     * @param    facadeName        接口名称
     * @param    parameter        请求参数
     * @return  T               调用返回值
     **/
    public static <T> T execute (Callable<T> call, String  platformName, String facadeName, Object parameter){

        T resultDate = null;

        try {
            resultDate = call.call();
        }catch (Exception e){
            System.out.println(String.format("调用[%s]系统的[%s]方法错误，请求参数[%s]",
                    platformName, facadeName, GsonUtil.GsonString(parameter)));
            new Throwable(String.format("调用[%s]系统的[%s]方法错误，请求参数[%s]",
                    platformName, facadeName, GsonUtil.GsonString(parameter)));
        }

        checkResult(resultDate, platformName, facadeName, parameter);

        return resultDate;
    }

    /**
     * 返回值校验
     *
     * @param   resultDate:   返回值
     * @param   platformName: 平台名称
     * @param   facadeName:   接口名称
     * @param   parameter:    参数
     * @return  void
     **/
    private static <T> void checkResult(T resultDate, String platformName, String facadeName, Object parameter) {

        Class<?> resultDateClass = resultDate.getClass();
        try {
            try {
                Object success = resultDateClass.getMethod("getResult").invoke(resultDate);
                System.out.println("执行结果为" + GsonUtil.GsonString(success));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


}
