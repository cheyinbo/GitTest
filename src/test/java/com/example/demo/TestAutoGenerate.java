package com.example.demo;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
public class TestAutoGenerate {


    @Test
    public void autoGenerate(){
        //Step1 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //Step2 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //填写代码生成的目录
        String projectPath = "D:\\IdeaProjects\\demo";
        //配置代码最终输出的目录
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("cheyinbo");
        globalConfig.setOpen(false);

        autoGenerator.setGlobalConfig(globalConfig);

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/testMybatisPlus?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");

        PackageConfig packageConfig = new PackageConfig();

    }
}
