
import com.leex2.ProxyObject;
import com.leex2.ProxyObjectImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author : 86167
 * @Description : ProxyTest 2021/3/29 11:33 86167
 */
public class ProxyTest2 {

    @Test
    public void test(){

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("执行了");

                method.invoke(proxy, args);

                return "Hello proxy";
            }
        };

        ProxyObject proxy = (ProxyObject) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                ProxyObjectImpl.class.getInterfaces(),
                invocationHandler);


        proxy.process("啦啦啦");

    }

}
