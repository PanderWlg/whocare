package com.womow.toc.whocare.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {
    public static class InterceptUrl {
        /**
         * 请求
         */
        private String pattern;
        /**
         * 访问权限
         */
        private String access;

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public String getAccess() {
            return access;
        }

        public void setAccess(String access) {
            this.access = access;
        }
    }

    /**
     * 请求地址
     */
    private List<InterceptUrl> interceptUrls;
    /**
     * 是否开启
     */
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<InterceptUrl> getInterceptUrls() {
        return interceptUrls;
    }

    public void setInterceptUrls(List<InterceptUrl> interceptUrls) {
        this.interceptUrls = interceptUrls;
    }


}
