import com.leex.ProxyInvocationHandler;
import com.leex.ProxyObject;
import com.leex.ProxyObjectImpl;
import org.junit.Test;

/**
 * @Author : 86167
 * @Description : ProxyTest 2021/3/29 11:33 86167
 */
public class ProxyTest {

    @Test
    public void test(){

        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();

        ProxyObject proxyObject = new ProxyObjectImpl();

        proxyInvocationHandler.setTarget(proxyObject);

        ProxyObject proxy = (ProxyObject) proxyInvocationHandler.getProxy();

        String str = proxy.process("啦啦啦");
        System.out.println(str);

    }

}
