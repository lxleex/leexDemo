package FacadeTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author : leex
 * @Description : Test 2021/7/13 21:57 leex
 */
public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        ResultDTO<String> execute = FacadeTemplate.execute(LOGGER, () -> {

            return "123465";

        });

        System.out.println(execute);
    }


}
