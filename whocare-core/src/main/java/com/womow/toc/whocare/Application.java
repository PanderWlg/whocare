package com.womow.toc.whocare;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author changqingshun
 */
@SpringBootApplication(scanBasePackages = "com.womow.toc.whocare")
@EnableTransactionManagement(proxyTargetClass = true)
@ImportResource({"classpath*:spring/spring-*.xml"})
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class)
                .web(WebApplicationType.SERVLET);
    }
}
