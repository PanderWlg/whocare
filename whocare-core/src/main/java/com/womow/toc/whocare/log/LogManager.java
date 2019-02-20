package com.womow.toc.whocare.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志组件
 *
 * @author cxy        2014-11-11
 */
public class LogManager {

    public static final Logger log = LoggerFactory.getLogger("common");

    private static final Logger DEBUG = LoggerFactory.getLogger("debuglog");

    private static final Logger ERRORLOG = LoggerFactory.getLogger("errorlog");

    private static final Logger OPTLOG = LoggerFactory.getLogger("opt");

    private static final Logger LOGINLOG = LoggerFactory.getLogger("login");

    private static final Logger KANBANLOG = LoggerFactory.getLogger("kanban");

    /**
     * 记录debug日志到 debug.log
     * 用于开发时的系统调试，正式上线运行，建议关闭该模式
     */
    public static void logDebug(String logTitle, String logContent) {
        if (DEBUG.isDebugEnabled()) {
            DEBUG.debug("[" + logTitle + "]:" + logContent);
        }
    }

    /**
     * 明确知道哪里错了，自己去写日志
     * 记录error日志到 error.log
     */
    public static void logError(String logTitle, String logContent) {
        ERRORLOG.error("[" + logTitle + "]:" + logContent);
    }

    /**
     * 操作日志，业务的操作记录
     */
    public static void logOpt(String logTitle, String logContent) {
        OPTLOG.info("[" + logTitle + "]:" + logContent);
    }

    /**
     * 登录日志
     */
    public static void logLogin(String logContent) {
        LOGINLOG.info(logContent);
    }

    /**
     * 看板日志
     */
    public static void logKanban(String logContent) {
        KANBANLOG.info(logContent);
    }

    /**
     * 自动带行号和异常信息的
     *
     * @param e
     * @param e
     */
    public static void logError(Exception e) {
        StackTraceElement ste = e.getStackTrace()[0];
        ERRORLOG.error("[" + ste.getFileName() + ":" + ste.getLineNumber() + "]:" + e.getMessage());
    }

}