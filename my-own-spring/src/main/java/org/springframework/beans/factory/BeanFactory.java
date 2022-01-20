package org.springframework.beans.factory;

import org.springframework.beans.factory.stereotype.Component;
import org.springframework.beans.factory.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class BeanFactory {

    private final Map<String, Object> singletons = new HashMap<>();

    public Object getBean(final String beanName) {
        return singletons.get(beanName);
    }

    public void instantiate(final String basePackage) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
}
