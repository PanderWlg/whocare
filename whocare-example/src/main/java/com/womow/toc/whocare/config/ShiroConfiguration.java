package com.womow.toc.whocare.config;

import org.apache.shiro.authc.credential.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
    @Bean
    PasswordService passwordService() {
        return new DefaultPasswordService();
    }

    @Bean
    PasswordMatcher passwordMatcher(PasswordService passwordService) {
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        passwordMatcher.setPasswordService(passwordService);
        return passwordMatcher;
    }

    @Bean
    Realm simpleAccountRealm(PasswordMatcher passwordMatcher) {
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.setCredentialsMatcher(passwordMatcher);
        realm.addAccount("17612286689", passwordMatcher.getPasswordService().encryptPassword("123456"));
        realm.addAccount("18002167657", "123456");
        return realm;
    }
}
