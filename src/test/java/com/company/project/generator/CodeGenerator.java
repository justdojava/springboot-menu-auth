package com.company.project.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

    /**
     * JDBC数据源连接配置
     */
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/menu_auth_db";

    /**
     * JDBC数据源账号配置
     */
    private static String jdbcUserName = "root";

    /**
     * JDBC数据源密码配置
     */
    private static String jdbcPassword = "Hello@123456";

    /**
     * JDBC数据源驱动配置
     */
    private static String jdbcDriverName = "com.mysql.cj.jdbc.Driver";

    /**
     * 项目位置
     */
    private static String projectPath = System.getProperty("user.dir");//项目在硬盘上的基础路径

    /**
     * 生成类文件存放位置
     */
    private static String classOutPath = projectPath + "/src/main/java";

    /**
     * 生成xxxMapper.xml文件存放位置
     */
    private static String mapperXmlPath = projectPath + "/src/main/resources/mapper/";

    /**
     * 模块结构（模块路径）
     */
    private static String modulePath = "com.company.project";

    /**
     * 包文件夹
     */
    private static String moduleName = null;

    /**
     * 作者
     */
    private static String author = "pzblog";

    /**
     * 指定表
     */
    private static String[] tableNames = {
            "tb_user","tb_user_role","tb_role","tb_role_menu","tb_menu"
    };

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(classOutPath);
        gc.setAuthor(author);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setDateType(DateType.ONLY_DATE);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig().setUrl(jdbcUrl)
                .setDriverName(jdbcDriverName)
                .setUsername(jdbcUserName)
                .setPassword(jdbcPassword)
                .setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(modulePath);
        pc.setModuleName(moduleName);//包名
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("web");
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
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return mapperXmlPath + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
//        templateConfig.setController("templates/controller.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableNames);
        strategy.setTablePrefix("td_", "ts_", "tb_", "sn_", "te_", "tc_", "tm_", "tl_");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(false);
        strategy.entityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());//freemarker引擎
        mpg.execute();
    }
}
