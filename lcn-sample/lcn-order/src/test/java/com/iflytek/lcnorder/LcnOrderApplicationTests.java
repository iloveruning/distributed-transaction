package com.iflytek.lcnorder;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LcnOrderApplicationTests {

    @Test
    public void contextLoads() {
        String packageName = "com.iflytek.lcnorder";
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://172.16.154.26:3306/test_distributed-transaction?useUnicode=true&characterEncoding=utf-8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("imsp_vcloud")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude("order");//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("llchen12")
                .setOutputDir("D:\\ideaworkspace\\distributed-transaction\\lcn-sample\\lcn-order\\src\\main\\java")
                .setFileOverride(true);
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

}
