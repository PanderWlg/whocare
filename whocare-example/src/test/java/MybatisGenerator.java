import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.womow.toc.whocare.mybatis.service.MybatisBaseServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MybatisGenerator {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");

        gc.setOutputDir(projectPath + "/whocare-example/src/main/java");
        gc.setAuthor("changqingshun");
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setServiceName(null);
        gc.setServiceImplName("%sService");
        gc.setEntityName("%s");
        //gc.setActiveRecord(true);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://49.4.1.42:3306/pmis-dev?useUnicode=true&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("pmis-dev");
        dsc.setPassword("pmisDev1028");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("clbx");
        pc.setParent("com.womow.toc.whocare");
        pc.setEntity("model");
        pc.setService("");
        pc.setController("controller");
        pc.setServiceImpl("service");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/whocare-example/src/main/resources/mybatis/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        String formTemplate="/templates/entity-form.jsp.ftl";
        focList.add(new FileOutConfig(formTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/whocare-example/src/main/webapp/WEB-INF/views/" + pc.getModuleName()
                        + "/" + StringUtils.firstCharToLower(tableInfo.getEntityName()) + "-form.jsp";
            }
        });
        String entitiIndexTemplate="/templates/entity-index.jsp.ftl";
        focList.add(new FileOutConfig(entitiIndexTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/whocare-example/src/main/webapp/WEB-INF/views/" + pc.getModuleName()
                        + "/" + StringUtils.firstCharToLower(tableInfo.getEntityName()) + "-index.jsp";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // templateConfig.setEntity();
        // templateConfig.setService();
        // templateConfig.setController();
        //不生成service
        templateConfig.setService(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.no_change)
                .setEntityTableFieldAnnotationEnable(true)
                //.setSuperMapperClass(MybatisBaseMapper.class.getName())
                .setSuperServiceImplClass(MybatisBaseServiceImpl.class.getName())
                .setEntityBuilderModel(true)
                .setEntityLombokModel(false)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setInclude("t_pmis_chuxing","t_pmis_bx_cl")
                .setControllerMappingHyphenStyle(true)
                .setTableFillList(Arrays.asList(new TableFill("create_time_", FieldFill.INSERT),new TableFill("status_",FieldFill.INSERT)))
                .setTablePrefix("t_pmis_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


}
