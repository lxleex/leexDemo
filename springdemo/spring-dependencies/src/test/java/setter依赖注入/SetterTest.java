package setter依赖注入;

import com.leex.setter依赖注入.Address;
import com.leex.setter依赖注入.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author : 86167
 * @Description : SetterTest 2021/3/21 16:00 86167
 */
public class SetterTest {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-setter.xml");

        Student student = (Student) context.getBean("student");

        student.show();
    }

}
