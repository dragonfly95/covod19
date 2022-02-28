package com.project.covid19.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/covid19?serverTimezone=Asia/Seoul&useSSL=false"
        );
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
}