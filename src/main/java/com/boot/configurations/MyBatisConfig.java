package com.boot.configurations;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class MyBatisConfig {
	
	@Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setTypeAliasesPackage("tk.mybatis.springboot.model");//每一张表对应的实体类

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));//每张表对应的xml文件
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public DataSource getDataSource(Environment env) {

        String url = env.getProperty("spring.datasource.url");

        String userName = env.getProperty("spring.datasource.username");

        String password = env.getProperty("spring.datasource.password");
       
        return DataSourceBuilder.create().url(url).username(userName).password(password).build();

    }
}
