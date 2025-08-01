package ksiprus.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextFactory {
    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("context.xml");

    public static  <T> T getBean(Class<T> requiredType){
        return context.getBean(requiredType);
    }

    public static  <T> T getBean(String name, Class<T> requiredType){
        return context.getBean(name, requiredType);
    }
}
