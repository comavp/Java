package org.springframework.context;

import com.kciray.CustomPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.event.ContextClosedEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URISyntaxException;

public class ApplicationContext {

    private BeanFactory beanFactory = new BeanFactory();

    public ApplicationContext(final String basePackage) throws ReflectiveOperationException, IOException, URISyntaxException {
        System.out.println("******Context is under construction******");

        beanFactory.addPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.addPostProcessor(new CustomPostProcessor());
        beanFactory.instantiate(basePackage);
        beanFactory.populateProperties();
        beanFactory.injectBeanNames();
        beanFactory.injectBeanFactories();
        beanFactory.initializeBeans();
    }

    public void close() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        beanFactory.close();
        for (Object bean : beanFactory.getSingletons().values()) {
            for (Type type : bean.getClass().getGenericInterfaces()) {
                if (type instanceof ParameterizedType) {
                    final ParameterizedType parameterizedType = (ParameterizedType) type;
                    final Type firstParameter = parameterizedType.getActualTypeArguments()[0];
                    if (firstParameter.equals(ContextClosedEvent.class)) {
                        final Method method = bean.getClass().getMethod("onApplicationEvent", ContextClosedEvent.class);
                        method.invoke(bean, new ContextClosedEvent());
                    }
                }
            }
        }
    }

    public Object getBean(final String beanName) {
        return beanFactory.getBean(beanName);
    }
}
