package org.springframework.beans.factory;

import org.springframework.beans.factory.annotations.Autowired;
import org.springframework.beans.factory.annotations.Resource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.beans.factory.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class BeanFactory {

    // todo заменить отладочные System.out.println на логирование

    private final Map<String, Object> singletons = new HashMap<>();
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public Object getBean(final String beanName) {
        return singletons.get(beanName);
    }

    public void instantiate(final String basePackage) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("Выполнение шага instantiate");
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final String path = basePackage.replace('.', '/');
        final Enumeration<URL> resources = classLoader.getResources(path);

        for (Iterator it = resources.asIterator(); it.hasNext();) {
            final URL resource = (URL) it.next();

            // todo этот код не сканирует вложенные пакеты
            final File file = new File(resource.toURI());
            for (File classFile: file.listFiles()) {
                final String fileName = classFile.getName();
                if (fileName.endsWith(".class")) {
                    final String className = fileName.substring(0, fileName.lastIndexOf("."));
                    final Class classObject = Class.forName(basePackage + "." + className);
                    if (classObject.isAnnotationPresent(Component.class) || classObject.isAnnotationPresent(Service.class)) {
                        if (classObject.isAnnotationPresent(Component.class)) {
                            System.out.println("Component: " + classObject);
                        } else if (classObject.isAnnotationPresent(Service.class)) {
                            System.out.println("Service: " + classObject);
                        }
                        final Object instance = classObject.newInstance(); // todo заменить deprecated метод
                        final String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                        singletons.put(beanName, instance);
                    }
                }
            }
        }
    }

    public void populateProperties() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Выполнение шага populateProperties");
        for (Object object : singletons.values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    for (Object dependency : singletons.values()) {
                        if (dependency.getClass().equals(field.getType())) {
                            injectDependency(object, field, dependency);
                        }
                    }
                } else if (field.isAnnotationPresent(Resource.class)) {
                    if (singletons.containsKey(field.getName())) {
                        final Object dependency = singletons.get(field.getName());
                        injectDependency(object, field, dependency);
                    }
                }
            }
        }
    }

    private void injectDependency(final Object bean, final Field field, final Object dependency) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Внедрение зависимости");
        final String setterName = "set" + field.getName().substring(0, 1).toUpperCase()
                + field.getName().substring(1);
        final Method setter = bean.getClass().getMethod(setterName, dependency.getClass());
        setter.invoke(bean, dependency);
    }

    public void injectBeanNames() {
        for (String name : singletons.keySet()) {
            final Object bean = singletons.get(name);
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(name);
            }
        }
    }

    public void injectBeanFactories() {
        for (Object bean : singletons.values()) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }
    }

    public void initializeBeans() {
        for (String beanName : singletons.keySet()) {
            final Object bean = singletons.get(beanName);
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            }
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            }
        }
    }
}
