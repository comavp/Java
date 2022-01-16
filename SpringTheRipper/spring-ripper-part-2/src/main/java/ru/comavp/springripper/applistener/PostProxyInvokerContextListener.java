package ru.comavp.springripper.applistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.comavp.springripper.quoters.PostProxy;

import java.lang.reflect.Method;

public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        final ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        final String[] names = context.getBeanDefinitionNames();
        for (String name: names) {
            final BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            final String originalClassName = beanDefinition.getBeanClassName();
            try {
                final Class<?> originalClass = Class.forName(originalClassName);
                final Method[] methods = originalClass.getMethods();
                for (Method method: methods) {
                    if (method.isAnnotationPresent(PostProxy.class)) {
                        final Object bean = context.getBean(name);
                        final Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        currentMethod.invoke(bean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
