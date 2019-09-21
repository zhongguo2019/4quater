
package com.boot.configurations;

import lombok.extern.slf4j.Slf4j;

import org.beetl.core.resource.ClasspathResourceLoader;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import org.beetl.ext.spring.BeetlSpringViewResolver;

import org.beetl.sql.core.ClasspathLoader;

import org.beetl.sql.core.Interceptor;

import org.beetl.sql.core.UnderlinedNameConversion;

import org.beetl.sql.core.db.MySqlStyle;

import org.beetl.sql.ext.DebugInterceptor;

import org.beetl.sql.ext.spring4.BeetlSqlDataSource;

import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;

import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;

import org.springframework.core.env.Environment;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import ch.qos.logback.classic.Logger;

import javax.sql.DataSource;

 

 

/**

 * @description:

 * @author: Martin

 * @create: 2018-08-02 11:34

 * @version: 1.0

 **/

@Configuration

@Slf4j

public class BeetlConf {
    @Bean(name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);
        beetlGroupUtilConfiguration.init();
        return beetlGroupUtilConfiguration; 

    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(
            @Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
//        beetlSpringViewResolver.setPrefix("/main/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;

    }

 

    //=============  以下是beetsql配置  =============

    @Bean(name = "beetlSqlScannerConfigurer")

    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {

        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();

        //扫面dao所在的包位置

        conf.setBasePackage("com.boot.web");
        

        //扫描的类是已Dao结尾

        conf.setDaoSuffix("Mapper");

        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        

        return conf;

    }

 

    @Bean(name = "sqlManagerFactoryBean")

    @Primary

    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource datasource) {

        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();

        BeetlSqlDataSource source = new BeetlSqlDataSource();

        source.setMasterSource(datasource);

 

        factory.setCs(source);

        factory.setDbStyle(new MySqlStyle());

        factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});

        factory.setNc(new UnderlinedNameConversion());//开启驼峰

        factory.setSqlLoader(new ClasspathLoader("/sql"));//sql文件路径

        return factory;

    }

 

    /**

     * 配置数据库

     */

    @Bean(name = "datasource")

    public DataSource getDataSource(Environment env) {

        String url = env.getProperty("spring.datasource.url");

        String userName = env.getProperty("spring.datasource.username");

        String password = env.getProperty("spring.datasource.password");
       
        return DataSourceBuilder.create().url(url).username(userName).password(password).build();

    }



    /**

     * 开启事务

     */

    @Bean(name = "transactionManager")

    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datasource") DataSource datasource) {

        DataSourceTransactionManager dsm = new DataSourceTransactionManager();

        dsm.setDataSource(datasource);

        return dsm;

    }

}
