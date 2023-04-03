package com.xmr.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Administrator
 * @Date: 2022/6/4
 * @LastEditTime: 2022/6/4 19:45
 * @LastEditors: Administrator
 * @Description: 使用mybatis-plus-generator生成代码的方法
 */
public class CodeGenerator {
    public static void main(String[] args) {

    }

    /**
     * 快速生成
     * 适用版本：mybatis-plus-generator 3.5.1 及其以上版本
     */
    private static void fastAutoGenerator() {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1/test?characterEncoding=utf8&serverTimezone=UTC", "root", "9958")
                .globalConfig(builder -> {
                    builder.author("xmr") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://mybatisplus"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xmr.model") // 设置父包名
                            .moduleName("myabtis") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:/mybatisplus/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user", "stu") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀,会把表前缀给删除掉,例如t_user,生成的类是User
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    /**
     * 交互式生成
     * 适用版本：mybatis-plus-generator 3.5.1 及其以上版本
     */
    private static void channelGenerator() {

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1/test?characterEncoding=utf8&serverTimezone=UTC", "root", "9958")
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }




}
