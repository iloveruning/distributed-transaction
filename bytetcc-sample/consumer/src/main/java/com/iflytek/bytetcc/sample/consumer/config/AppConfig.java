package com.iflytek.bytetcc.sample.consumer.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.bytesoft.bytejta.supports.jdbc.LocalXADataSource;
import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * @author llchen12
 * @date 2018/5/8
 */
@Import(SpringCloudConfiguration.class)
@Configuration
public class AppConfig {

    @Bean("mybatisDataSource")
    public DataSource dataSource(){
        LocalXADataSource dataSource = new LocalXADataSource();
        dataSource.setDataSource(druidDataSource());
        return dataSource;
    }

    public DataSource druidDataSource(){

        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://172.16.154.26:3306/test_dist?useUnicode=true&characterEncoding=utf-8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("imsp_vcloud");
        druidDataSource.setMaxActive(10);
        druidDataSource.setInitialSize(2);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setValidationQuery("select 'x' ");
        druidDataSource.setValidationQueryTimeout(1);
        druidDataSource.setTimeBetweenEvictionRunsMillis(30000);
        return druidDataSource;
    }

}
