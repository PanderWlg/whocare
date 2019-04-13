package com.womow.toc.whocare.generator;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.util.List;
import java.util.Map;

/**
 * @author changqingshun
 */
public class AutoGeneratorConfigBuilder extends ConfigBuilder {
    /**
     * 表名、实体名关系映射
     */
    private Map<String, String> tableEntityNameMappings;


    public Map<String, String> getTableEntityNameMappings() {
        return tableEntityNameMappings;
    }

    public void setTableEntityNameMappings(Map<String, String> tableEntityNameMappings) {
        this.tableEntityNameMappings = tableEntityNameMappings;
    }

    /**
     * 在构造器中处理配置
     *
     * @param packageConfig    包配置
     * @param dataSourceConfig 数据源配置
     * @param strategyConfig   表配置
     * @param template         模板配置
     * @param globalConfig     全局配置
     */
    public AutoGeneratorConfigBuilder(PackageConfig packageConfig, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, TemplateConfig template, GlobalConfig globalConfig) {
        super(packageConfig, dataSourceConfig, strategyConfig, template, globalConfig);

    }

    /**
     * 覆盖
     *
     * @param tableList
     */
    void overriedTable(List<TableInfo> tableList) {
        String[] tablePrefix = getStrategyConfig().getTablePrefix();
        if (null == tableEntityNameMappings) {
            return;
        }
        for (TableInfo tableInfo : tableList) {
            if (!tableEntityNameMappings.containsKey(tableInfo.getName())) {
                continue;
            }
            String entityName = tableEntityNameMappings.get(tableInfo.getName());
            if (StringUtils.isNotEmpty(this.getGlobalConfig().getEntityName())) {
                tableInfo.setConvert(true);
                tableInfo.setEntityName
                        (String.format(this.getGlobalConfig().getEntityName(), entityName));
            } else {
                tableInfo.setEntityName(getStrategyConfig(), entityName);
            }
            if (StringUtils.isNotEmpty(this.getGlobalConfig().getMapperName())) {
                tableInfo.setMapperName(String.format(this.getGlobalConfig().getMapperName(), entityName));
            } else {
                tableInfo.setMapperName(entityName + ConstVal.MAPPER);
            }
            if (StringUtils.isNotEmpty(this.getGlobalConfig().getXmlName())) {
                tableInfo.setXmlName(String.format(this.getGlobalConfig().getXmlName(), entityName));
            } else {
                tableInfo.setXmlName(entityName + ConstVal.MAPPER);
            }
            if (StringUtils.isNotEmpty(this.getGlobalConfig().getServiceName())) {
                tableInfo.setServiceName(String.format(this.getGlobalConfig().getServiceName(), entityName));
            } else {
                tableInfo.setServiceName("I" + entityName + ConstVal.SERVICE);
            }
            if (StringUtils.isNotEmpty(this.getGlobalConfig().getServiceImplName())) {
                tableInfo.setServiceImplName(String.format(this.getGlobalConfig().getServiceImplName(), entityName));
            } else {
                tableInfo.setServiceImplName(entityName + ConstVal.SERVICE_IMPL);
            }
            if (StringUtils.isNotEmpty(this.getGlobalConfig().getControllerName())) {
                tableInfo.setControllerName(String.format(this.getGlobalConfig().getControllerName(), entityName));
            } else {
                tableInfo.setControllerName(entityName + ConstVal.CONTROLLER);
            }
        }
    }

    @Override
    public ConfigBuilder setTableInfoList(List<TableInfo> tableInfoList) {
        super.setTableInfoList(tableInfoList);
        overriedTable(tableInfoList);
        return this;
    }
}
