package com.womow.toc.whocare.generator;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Map;

/**
 * @author changqingshun
 */
public class FreemarkerTemplateEngineV2 extends FreemarkerTemplateEngine {
    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        File file = new File(outputFile);
        if (!file.exists()) {
            File pf = file.getParentFile();
            if (!pf.exists()) {
                pf.mkdirs();
            }
        }
        super.writer(objectMap, templatePath, outputFile);
    }
}
