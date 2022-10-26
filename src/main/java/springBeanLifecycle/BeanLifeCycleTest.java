package springBeanLifecycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserBean user = (UserBean) applicationContext.getBean("userBean");
        PrototypeBean bean = (PrototypeBean) applicationContext.getBean("prototypeBean");
        ((AbstractApplicationContext) applicationContext).close();
        //容器关闭以后 多例bean的销毁方法没有执行，说明此时的多例bean已经脱离的ioc容器的管理
    }
}

