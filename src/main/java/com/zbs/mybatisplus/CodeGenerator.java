package com.zbs.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/3/21
 *
 * @author Saligia
 */
public class CodeGenerator {

    /**
     * 代码生成位置
     */
    public static final String PARENT_NAME = "citic.cph.bank.bankcommon";

    /**
     * 路径
     */
    public static final String PACKAGE_PATH = "citic/cph/bank/bankcommon";

    /**
     * modular 名字
     */
    public static final String MODULAR_NAME = "";

    /**
     * 基本路径
     */
    public static final String SRC_MAIN_JAVA = "src/main/java/";

    /**
     * 作者
     */
    public static final String AUTHOR = "";

    /**
     * 是否是 rest 接口
     */
    private static final boolean REST_CONTROLLER_STYLE = true;

    public static final String JDBC_MYSQL_URL = "jdbc:mysql://rm-2jd0l8t2999kn09a3uo.mysql.rds.aliyuncs.com:3306/e-receive-test?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";

    public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String JDBC_USERNAME = "cph_dev";

    public static final String JDBC_PASSWORD = "Dev&2101";

    @Test
    public void generator() {

        String projectPath = System.getProperty("user.dir");

        //1. 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/" + SRC_MAIN_JAVA)
                .setAuthor(AUTHOR)
                .setOpen(false)
                .setSwagger2(true)
                .setActiveRecord(true)
                .setBaseResultMap(true)
                .setFileOverride(true)
                .setEnableCache(true)
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(JDBC_MYSQL_URL);
        dataSourceConfig.setDriverName(JDBC_DRIVER_NAME);
        dataSourceConfig.setUsername(JDBC_USERNAME);
        dataSourceConfig.setPassword(JDBC_PASSWORD);


        //3.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(false) // 全局大写命名
                .setColumnNaming(NamingStrategy.underline_to_camel) //表名 字段名 是否使用下滑线命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude("bank_icbc_bill_repay") //生成的表
                .setControllerMappingHyphenStyle(true)
                .setRestControllerStyle(REST_CONTROLLER_STYLE)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setEntityBuilderModel(true)
                .setEntityLombokModel(true);
                //【实体】是否生成字段常量（默认 false）
//                .setTablePrefix("message_"); // 表前缀

        //4.包名策略
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PARENT_NAME)//父包名
                .setController("controller")
                .setEntity("db.entity")
                .setService("service")
                .setMapper("db.mapper")
                .setXml("mapper");


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

//        // 如果模板引擎是 velocity
//        String templatePathBo = "/templates/bo.java.ftl";
//
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePathBo) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + SRC_MAIN_JAVA + PACKAGE_PATH + "beans/bo/" + tableInfo.getEntityName() + "Bo" + StringPool.DOT_JAVA;
//            }
//        });

        cfg.setFileOutConfigList(focList);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);

        //5.整合配置
        AutoGenerator ag = new AutoGenerator().setGlobalConfig(globalConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(templateConfig)
                .setCfg(cfg)
                .setPackageInfo(packageConfig);
        ag.execute();

    }
}
