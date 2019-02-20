package com.womow.toc.whocare.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author changqingshun
 */
@Controller
@RequestMapping("/views")
public class CommonViewController {
    /**
     * 模块主页
     *
     * @param module
     * @param entity
     * @return
     */
    @RequestMapping("/{module}/{entity}/index")
    String list(@PathVariable String module, @PathVariable String entity) {
        return module + "/" + entity + "-index";
    }

    /**
     * 表单
     *
     * @param module
     * @param entity
     * @return
     */
    @RequestMapping("/{module}/{entity}/form")
    String form(@PathVariable String module, @PathVariable String entity) {
        return module + "/" + entity + "-form";
    }
}
