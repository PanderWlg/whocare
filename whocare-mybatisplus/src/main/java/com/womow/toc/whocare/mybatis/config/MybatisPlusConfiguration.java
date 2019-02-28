package com.womow.toc.whocare.mybatis.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changqingshun
 */
@Configuration
public class MybatisPlusConfiguration {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public ConfigurationCustomizer getConfigurationCustomizer() {
        return (config) -> {
        };

    }
}
