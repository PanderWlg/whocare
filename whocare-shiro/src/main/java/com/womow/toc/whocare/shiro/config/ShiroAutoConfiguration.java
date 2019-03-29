package com.womow.toc.whocare.shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.config.AbstractShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.config.ShiroConfiguration;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * shiro 自动配置
 *
 * @author changqingshun
 * @date 2019-03-28
 */
@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
@ConditionalOnProperty(prefix = "shiro", name = "enabled", havingValue = "true")
public class ShiroAutoConfiguration {
    /**
     * Web应用
     */
    @Configuration
    @Import({ShiroBeanConfiguration.class})
    @ConditionalOnWebApplication
    public static class ShiroWebAutoConfiguration extends ShiroWebConfiguration {
        @Bean
        protected ShiroFilterChainDefinition shiroFilterChainDefinition(ShiroProperties shiroProperties) {
            DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
            shiroProperties.getInterceptUrls().forEach(one -> shiroFilterChainDefinition.addPathDefinition(one.getPattern(), one.getAccess()));
            return shiroFilterChainDefinition;

        }

    }

    /**
     * 非Web应用
     */
    @Configuration
    @Import({ShiroConfiguration.class, ShiroAnnotationProcessorConfiguration.class})
    @ConditionalOnNotWebApplication
    public static class ShiroNoWetAutoConfiguration {

    }

    /**
     * ShiroAnnotationProcessor
     */
    @Configuration
    public static class ShiroAnnotationProcessorAutoConfiguration extends AbstractShiroAnnotationProcessorConfiguration {
        @Bean
        protected AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
            return super.authorizationAttributeSourceAdvisor(securityManager);
        }
    }
}
