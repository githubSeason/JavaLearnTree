package com.lxseason.bootlaunch.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 初始化数据源和数据操作的对象
 * 在单数据源情况下，spring中默认有dataSource、和jdbcTemplate两个Bean，来帮助我们操作数据库
 * 多数据源情况下，就需要自定义多套dataSource、和jdbcTemplate的Bean来操作不同数据库
 *      如，primaryDataSource、primaryJdbcTemplate和secondaryDataSource、secondaryJdbcTemplate
 * 1、在config包下创建DataSourceConfig，实现把自定义数据源配置注入spring上下文
 * 2、在dao层新增ArticleJDBCMultDSDao
 * 3、在service层修改ArticleRestJDBCServiceImpl（ArticleJDBCMultDSDao替换ArticleJDBCDAO）
 *      加载并使用primaryJdbcTemplate、secondaryJdbcTemplate对象来操作不同数据库
 *      （注意：ArticleJDBCDAO中的@Resource 需要暂时注释掉，因为多数据源情况下仍注入默认jdbcTemplate会报错）
 */
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")//通过识别配置文件中的前缀来区分并加载数据库的配置
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
