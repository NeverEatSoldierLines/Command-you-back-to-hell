package cn.edu.jnu.config;

import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {


    private final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(){
//        return new HikariDataSource();
        LOGGER.info("hikari数据库连接池创建中.......");
        return DataSourceBuilder.create().build();
    }

}


