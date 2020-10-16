package com.zbs.mybatisplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

/**
 * 代码生成
 *
 * @author : SPL
 * @since : 2020-04-20 18:16
 **/
public class GenerateCode {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mybatis_plus_demo?useUnicode=true&characterEncoding=utf8";
    private static final String username = "root";
    private static final String password = "root";

//    private static String projectPath = System.getProperty("user.dir");
    private static final String projectPath = "D:\\work\\goodDemo\\mybatisPlusDemo\\src\\main\\java";
    //基本包名
    private static String basePackage = "com.zbs.mybatisplus";
    //基础controller
    //private static String BASE_CONTROLLER = "com.zbs.mybatisplus.controller.BaseController";

    //要生成的表名,和对应前缀
    private static String[] tables = {"user"};
    private static String[] tables_prefix = {""};

    private static String AUTHOR = "zhangbs";//作者
    //要生成的表名,和对应前缀
    //重复生成，定义需要覆盖的文件
    private static List<FileType> FILE_OVERWRITE_LIST = Lists.newArrayList(
            FileType.ENTITY,
            FileType.OTHER
    );

    public static void main(String[] args) {
        run();
    }
    public static void run() {

        System.out.println("====================== 执行代码生成 ======================");

        AutoGenerator mpg = new AutoGenerator();

        //1、 数据源配置
        dbConfig(mpg);
        //2、 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath);
        //true：生成完成后打开文件夹
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setAuthor(AUTHOR);
        gc.setFileOverride(false);
        //自定义文件命名
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        //3、 包配置
        packageConfig(mpg);
        //4 配置模板
        templateConfig(mpg);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                /* to do nothing */
            }
        };
        //5 文件输出配置
        fileOutConfig(mpg, cfg);

        //6、文件覆盖配置
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            //如果是Entity则直接返回true表示写文件
            if (FILE_OVERWRITE_LIST.contains(fileType)) {
                return true;
            }
            //否则先判断文件是否存在, 存在不覆盖
            File file = new File(filePath);
            boolean exist = file.exists();
            if (!exist) {
                file.getParentFile().mkdirs();
            }
            //文件不存在或者全局配置的fileOverride为true才写文件
            return !exist || configBuilder.getGlobalConfig().isFileOverride();
        });

        //7、生成策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tables); // 需要生成的表
        strategy.setTablePrefix(tables_prefix);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("del_flag");//逻辑删标识
        strategy.setRestControllerStyle(true);//RestController
        //strategy.setSuperControllerClass(BASE_CONTROLLER);
        strategy.setEntityBuilderModel(true);//build模式
        strategy.isSkipView();
        mpg.setStrategy(strategy);

        mpg.execute();
    }

    private static void fileOutConfig(AutoGenerator mpg, InjectionConfig cfg) {
        // 自定义输出配置
        mpg.setCfg(cfg);
    }

    private static void packageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);  //所属模块
        pc.setParent(basePackage);
        pc.setController("controller");
        pc.setEntity("dao.entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao.mapper");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);
    }

    private static void dbConfig(AutoGenerator mpg) {
        DataSourceConfig db = new DataSourceConfig();
        db.setDriverName(driver);
        db.setUsername(username);
        db.setPassword(password);
        db.setUrl(url);
        mpg.setDataSource(db);
    }

    /**
     * 配置模板
     *
     * @param mpg
     * @return TemplateConfig
     */
    private static void templateConfig(AutoGenerator mpg) {
        TemplateConfig templateConfig = new TemplateConfig();
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController("/templates/controller.java");
        templateConfig.setEntity("/templates/entity.java");
        templateConfig.setService("/templates/service.java");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
        templateConfig.setMapper("/templates/mapper.java");
        templateConfig.setXml("/templates/mapper.xml.vm");
        mpg.setTemplate(templateConfig);
    }

}
