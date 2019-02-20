package com.womow.toc.whocare.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author chenxiaoyang 2015-05-28
 * Exception log
 */
@Aspect
@Component
public class ExceptionAopBean {
    private static final Logger aopLog = org.slf4j.LoggerFactory.getLogger("errorlog");

    @Pointcut("execution(* com.womow..*Service.*(..))")
    public void aspectMethod() {
    }
    public   void  service(){}
    @AfterThrowing(throwing = "e", value = "aspectMethod()")
    public void throwException(JoinPoint jp, Throwable e) {
        StackTraceElement ste = e.getStackTrace()[0];
        aopLog.error(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")" + ":" + e.getMessage());
    }

}
