package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TenantBeansConfig {

    @Scope(scopeName = "tenantScope")
    @Bean
    public TenantBean foo() {
        return new TenantBean("foo");
    }

    @Scope(scopeName = "tenantScope")
    @Bean
    public TenantBean bar() {
        return new TenantBean("bar");
    }
}
