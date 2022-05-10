import com.leex.service.MyService;
import com.leex.utils.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author : 86167
 * @Description : HelloSpringTest 2021/3/15 23:19 86167
 */
public class HelloSpringTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        MyService myService = (MyService) context.getBean("myService");


        //MyService myService = ApplicationContextUtil.getBean(MyService.class);

        myService.process();
    }

}
