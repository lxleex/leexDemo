package 懒加载bean;

import com.leex.懒加载bean.Mybean1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author : 86167
 * @Description : Test 2021/3/21 23:05 86167
 */
public class Test {

    @org.junit.Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-lazy-init.xml");

        System.out.println("已经启动啦");

        Mybean1 student = (Mybean1) context.getBean("mybean1");

    }
}
