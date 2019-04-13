package com.womow.toc.whocare.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenxiaoyang 2015-05-28
 * Exception log
 */
@Aspect
@Component
public class ExceptionAopBean {
    private static final Logger aopLog = LoggerFactory.getLogger("errorlog");

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void aspectMethod() {
    }

    @AfterThrowing(throwing = "e", value = "aspectMethod()")
    public void throwException(JoinPoint jp, Throwable e) {
        if (aopLog.isErrorEnabled()) {
            aopLog.error(e.getMessage(), e);
        }

    }

}
