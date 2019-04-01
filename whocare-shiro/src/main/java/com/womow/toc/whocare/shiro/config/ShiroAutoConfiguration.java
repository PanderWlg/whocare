package com.womow.toc.whocare.shiro.config;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.config.*;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.*;
import org.apache.shiro.web.servlet.Cookie;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

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
    @Import({ShiroBeanConfiguration.class, ShiroWebFilterConfiguration.class})
    @ConditionalOnWebApplication
    public static class ShiroWebAutoConfiguration extends AbstractShiroWebConfiguration {

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SubjectDAO subjectDAO() {
            return super.subjectDAO();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionStorageEvaluator sessionStorageEvaluator() {
            return super.sessionStorageEvaluator();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionFactory sessionFactory() {
            return super.sessionFactory();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionDAO sessionDAO() {
            return super.sessionDAO();
        }

        @Bean(name = "sessionCookieTemplate")
        @ConditionalOnMissingBean
        @Override
        protected Cookie sessionCookieTemplate() {
            return super.sessionCookieTemplate();
        }

        @Bean(name = "rememberMeCookieTemplate")
        @ConditionalOnMissingBean
        @Override
        protected Cookie rememberMeCookieTemplate() {
            return super.rememberMeCookieTemplate();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected RememberMeManager rememberMeManager() {
            return super.rememberMeManager();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SubjectFactory subjectFactory() {
            return super.subjectFactory();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected Authorizer authorizer() {
            return super.authorizer();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected AuthenticationStrategy authenticationStrategy() {
            return super.authenticationStrategy();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected Authenticator authenticator() {
            return super.authenticator();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionManager sessionManager() {
            return super.sessionManager();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionsSecurityManager securityManager(List<Realm> realms) {
            return super.securityManager(realms);
        }

        @Bean
        @ConditionalOnProperty(prefix = "shiro", name = "interceptUrls")
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
    @ConditionalOnNotWebApplication
    public static class ShiroNoWetAutoConfiguration extends AbstractShiroConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionsSecurityManager securityManager(List<Realm> realms) {
            return super.securityManager(realms);
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionManager sessionManager() {
            return super.sessionManager();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SubjectDAO subjectDAO() {
            return super.subjectDAO();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionStorageEvaluator sessionStorageEvaluator() {
            return super.sessionStorageEvaluator();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SubjectFactory subjectFactory() {
            return super.subjectFactory();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionFactory sessionFactory() {
            return super.sessionFactory();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionDAO sessionDAO() {
            return super.sessionDAO();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected Authorizer authorizer() {
            return super.authorizer();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected AuthenticationStrategy authenticationStrategy() {
            return super.authenticationStrategy();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected Authenticator authenticator() {
            return super.authenticator();
        }

        @Bean
        @ConditionalOnMissingBean
        @Override
        protected RememberMeManager rememberMeManager() {
            return super.rememberMeManager();
        }
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
