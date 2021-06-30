import com.leex.UserRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author : 86167
 * @Description : AopTest 2021/3/29 15:31 86167
 */
public class AopTest {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
        userRepository.add();
    }

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext1.xml");
        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
        userRepository.add();
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext2.xml");
        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
        userRepository.add();
    }
}
