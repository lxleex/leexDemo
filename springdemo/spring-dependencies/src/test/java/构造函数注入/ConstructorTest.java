package 构造函数注入;

import com.leex.构造函数注入.MyClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author : 86167
 * @Description : ConstructorTest 2021/3/21 15:19 86167
 */
public class ConstructorTest {

    @Test
    public void testDefault(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-constructor.xml");
        MyClass myClass = context.getBean("myClassDefault", MyClass.class);
        System.out.println(myClass.toString());

    }

    @Test
    public void testIndex(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-constructor.xml");
        MyClass myClass = context.getBean("myClassIndex", MyClass.class);
        System.out.println(myClass.toString());
    }

    @Test
    public void testName(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-constructor.xml");
        MyClass myClass = context.getBean("myClassName", MyClass.class);
        System.out.println(myClass.toString());
    }

    @Test
    public void testType(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-constructor.xml");
        MyClass myClass = context.getBean("myClassType", MyClass.class);
        System.out.println(myClass.toString());
    }

}
