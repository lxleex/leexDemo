package FacadeTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @Author : leex
 * @Description : FacadeTemplate 2021/7/13 21:32 leex
 */
public class FacadeTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacadeTemplate.class);

    private FacadeTemplate facadeTemplate = null;

    public FacadeTemplate(){
        facadeTemplate = this;
    }

    public static <T> ResultDTO<T> execute(Logger logger, Callable<T> call){

        try{
            init();

            T resultDate = call.call();

            return new ResultDTO<T>(true, resultDate);
        } catch (IllegalArgumentException e){
            return new ResultDTO<T>(false, "IllegalArgumentException", e.getMessage());
        } catch (NullPointerException e){
            return new ResultDTO<T>(false, "NullPointerException", "空指针异常");
        } catch (Throwable throwable){
            return new ResultDTO<T>(false, "UNKNOWN_ERROR", "未知错误");
        }finally {
            after();
        }

    }


    private static void init(){
        LOGGER.info("开始执行模板类");
    }

    private static void after(){
        LOGGER.info("执行模板类结束");
    }


}
