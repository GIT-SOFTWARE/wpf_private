package com.biostime.bp.authorization.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.biostime.bp.authorization.config.datasource.properties.DataSourceProperties;
import com.biostime.bp.authorization.config.datasource.properties.DriudDataSourceProperties;
import com.biostime.bp.authorization.config.datasource.properties.DruidPluginProperties;

/**
 * @author <a href="mailto:yongfeng.huang@hh.global">11517</a>
 * @date 2018/9/11 15:29
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "com.biostime.bp.authorization.repository", sqlSessionTemplateRef = "antiFakeAuthSessionTemplate",sqlSessionFactoryRef="antiFakeAuthSqlSessionFactory")
public class MyBatisMapperConfig {

    
    @Autowired
    private DriudDataSourceProperties driudDataSourceProperties;
    
    @Autowired
    private DataSourceProperties  dataSourceProperties;
    
    @Autowired
    private DruidPluginProperties  druidPluginProperties;

    
      /**
     * mybatis  扫描  mapper  以及 配置文件配置
     * */
    @Value("${mybatis.type-aliases-package}")
    private String  mybatisTypeAliasesPackage;
    
    @Value("${mybatis.mapper-locations}")
    private String[] mybaitsMapperLocations;
    
    @Bean(name = "antiFakeAuthDataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
            datasource.setUrl(dataSourceProperties.getUrl());
            datasource.setUsername(dataSourceProperties.getUsername());
            datasource.setPassword(dataSourceProperties.getPassword());
            datasource.setDriverClassName(dataSourceProperties.getDriverClassName());
            datasource.setInitialSize(driudDataSourceProperties.getInitialSize());
            datasource.setMinIdle(driudDataSourceProperties.getMinIdle());
            datasource.setMaxActive(driudDataSourceProperties.getMaxActive());
            datasource.setMaxWait(driudDataSourceProperties.getMaxWait());
            datasource.setValidationQuery(driudDataSourceProperties.getValidationQuery());
            datasource.setTestWhileIdle(driudDataSourceProperties.getTestWhileIdle());
            datasource.setTestOnBorrow(driudDataSourceProperties.getTestOnBorrow());
            datasource.setTestOnReturn(driudDataSourceProperties.getTestOnReturn());
            datasource.setPoolPreparedStatements(driudDataSourceProperties.getPoolPreparedStatements());
            datasource.setUseGlobalDataSourceStat(driudDataSourceProperties.getUseGlobalDataSourceStat());
            datasource.setMinEvictableIdleTimeMillis(driudDataSourceProperties.getMinEvictableIdleTimeMillis());
            datasource.setTimeBetweenEvictionRunsMillis(driudDataSourceProperties.getTimeBetweenEvictionRunsMillis());
            datasource.setMaxOpenPreparedStatements(driudDataSourceProperties.getMaxOpenPreparedStatements());
            datasource.setMaxPoolPreparedStatementPerConnectionSize(driudDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
            datasource.setFilters(driudDataSourceProperties.getFilters());
            List<Filter> filters=new ArrayList<Filter>();
            filters.add(slf4jLogFilter());
            datasource.setProxyFilters(filters);
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        return datasource;
    }


    @Bean(name = "antiFakeAuthSqlSessionFactory")
    @Primary
    public SqlSessionFactory surveySqlSessionFactory(@Qualifier("antiFakeAuthDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        for (String mapperLocation : mybaitsMapperLocations) {
            Resource[] mappers = resourceResolver.getResources(mapperLocation);
            resources.addAll(Arrays.asList(mappers));
        }
        bean.setMapperLocations(resources.toArray(new Resource[resources.size()]));
        bean.setTypeAliasesPackage(mybatisTypeAliasesPackage);
        bean.setVfs(SpringBootVFS.class);
        return bean.getObject();
    }

    @Bean(name = "antiFakeAuthTransactionManager")
    @Primary
    public DataSourceTransactionManager surveyTransactionManager(
            @Qualifier("antiFakeAuthDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "antiFakeAuthSessionTemplate")
    @Primary
    public SqlSessionTemplate surveySqlSessionTemplate(
            @Qualifier("antiFakeAuthSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    
    
    
    @Bean(name = "slf4jLogFilter")
    public Slf4jLogFilter slf4jLogFilter() {
        Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
        try {
            slf4jLogFilter.setStatementCreateAfterLogEnabled(druidPluginProperties.isAfterStatementCreateLogEnable());
            slf4jLogFilter.setStatementParameterSetLogEnabled(druidPluginProperties.isStatementParameterSetLogEnable());
            slf4jLogFilter.setStatementExecuteAfterLogEnabled(druidPluginProperties.isAfterStatementExecuteLogEnable());
//          打印可执行sql
            slf4jLogFilter.setStatementExecutableSqlLogEnable(druidPluginProperties.isStatementExecutableSqlLogEnable());
            slf4jLogFilter.setStatementParameterClearLogEnable(druidPluginProperties.isStatementParameterClearLogEnable());
            slf4jLogFilter.setStatementCloseAfterLogEnabled(druidPluginProperties.isAfterStatementCloseLogEnable());
//          打印预处理sql语句，false为不打印
            slf4jLogFilter.setStatementPrepareAfterLogEnabled(druidPluginProperties.isAfterStatementPrepareLogEnable());
            slf4jLogFilter.setStatementPrepareCallAfterLogEnabled(druidPluginProperties.isAfterStatementPrepareCallLogEnable());
//            slf4jLogFilter.setStatementLogEnabled(true);
//            slf4jLogFilter.setDataSourceLogEnabled(true);
        } catch (Exception e) {
            log.error("slf4jLogFilter", e);
        }
        return slf4jLogFilter;
    }
    

}
