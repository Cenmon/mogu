package com.cenmo.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;

public class GenCode {

    @Test
    public void genCode() {
        String moduleName = "tb";

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Cenmo");
        gc.setOpen(false); //生成后是否打开资源管理器,打开生成文件夹
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去掉Service接口的首字母I
        gc.setIdType(IdType.ID_WORKER_STR); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型：仅有日期，仅有时间等等
        gc.setSwagger2(false);//开启Swagger2模式,打开后，每个pojo的字段均有注解
        mpg.setGlobalConfig(gc);

        // 3、数据源配置+ moduleName
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/taotao" + "?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.cenmo.mogu");
//        pc.setModuleName(moduleName); //模块名,即上述parent路径后再加模块名，如com.cenmo.mogu.模块名
        pc.setController("controller");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(moduleName + "_\\w*");//设置要映射的表名,此处即为对moduleName_*的所有表进行逆向工程
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表名映射到实体的命名策略，即下划线到驼峰命名
//        pc.getModuleName()
        strategy.setTablePrefix(moduleName + "_");//设置表前缀不生成，即原表为edu_Teacher，映射成Teacher，前缀edu_失效

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库字段名映射到实体的命名策略
        strategy.setEntityLombokModel(true); //开启lombok模型 其中注解@Accessors(chain = true)指setter链式操作，即setter方法返回该pojo对象

        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀，即字段名为is_deleted映射成deleted，防止某些框架将isDeleted当做getter方法

        //自动填充
        TableFill gmtCreate = new TableFill("created", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("updated", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        strategy.setVersionFieldName("version");//乐观锁列

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
