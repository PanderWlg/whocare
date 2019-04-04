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
        @ConditionalOnMissingBean
        @Bean
        @Override
        protected SubjectDAO subjectDAO() {
            return super.subjectDAO();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected SessionStorageEvaluator sessionStorageEvaluator() {
            return super.sessionStorageEvaluator();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected SessionFactory sessionFactory() {
            return super.sessionFactory();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected SessionDAO sessionDAO() {
            return super.sessionDAO();
        }

        @ConditionalOnMissingBean(name = "sessionCookieTemplate")
        @Bean(name = "sessionCookieTemplate")
        @Override
        protected Cookie sessionCookieTemplate() {
            return super.sessionCookieTemplate();
        }

        @ConditionalOnMissingBean(name = "rememberMeCookieTemplate")
        @Bean(name = "rememberMeCookieTemplate")
        @Override
        protected Cookie rememberMeCookieTemplate() {
            return super.rememberMeCookieTemplate();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected RememberMeManager rememberMeManager() {
            return super.rememberMeManager();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected SubjectFactory subjectFactory() {
            return super.subjectFactory();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected Authorizer authorizer() {
            return super.authorizer();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected AuthenticationStrategy authenticationStrategy() {
            return super.authenticationStrategy();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected Authenticator authenticator() {
            return super.authenticator();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected SessionManager sessionManager() {
            return super.sessionManager();
        }

        @ConditionalOnMissingBean
        @Bean
        @Override
        protected SessionsSecurityManager securityManager(List<Realm> realms) {
            return super.securityManager(realms);
        }

        @Bean
        protected ShiroFilterChainDefinition shiroFilterChainDefinition(ShiroProperties shiroProperties) {
            if (null != shiroProperties.getInterceptUrls() && !shiroProperties.getInterceptUrls().isEmpty()) {
                DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
                shiroProperties.getInterceptUrls().forEach(one -> shiroFilterChainDefinition.addPathDefinition(one.getPattern(), one.getAccess()));
                return shiroFilterChainDefinition;
            }
            return super.shiroFilterChainDefinition();


        }

    }

    /**
     * 非Web应用
     */
    @Configuration
    @Import({ShiroBeanConfiguration.class})
    @ConditionalOnNotWebApplication
    public static class ShiroNoWebAutoConfiguration extends AbstractShiroConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @Override
        protected SessionsSecurityManager securityManager(List<Realm> realms) {
            return super.securityManager(realms);
        }

        @Bean
        @ConditionalOnMissingBean(name ="sessionManager" )
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
        @ConditionalOnMissingBean(name = "authorizer")
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
        @ConditionalOnMissingBean(name = "authenticator")
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
