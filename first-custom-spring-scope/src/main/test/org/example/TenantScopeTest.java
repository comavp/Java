package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class TenantScopeTest {

    @Test
    public final void testCustomScope() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        try {
            context.register(TenantScopeConfig.class);
            context.register(TenantBeansConfig.class);
            context.refresh();

            final TenantBean fooBean = (TenantBean) context.getBean("foo", TenantBean.class);
            fooBean.sayHello();
            final TenantBean barBean = (TenantBean) context.getBean("bar", TenantBean.class);
            barBean.sayHello();
            final Map<String, TenantBean> beansMap = context.getBeansOfType(TenantBean.class);

            assertTrue(!fooBean.equals(barBean));
            assertTrue(beansMap.size() == 2);
            assertTrue(beansMap.containsValue(fooBean));
            assertTrue(beansMap.containsValue(barBean));

            final BeanDefinition fooDefinition = context.getBeanDefinition("foo");
            final BeanDefinition barDefinition = context.getBeanDefinition("bar");

            assertTrue(fooDefinition.getScope().equals("tenantScope"));
            assertTrue(barDefinition.getScope().equals("tenantScope"));
        } finally {
            context.close();
        }
    }
}
