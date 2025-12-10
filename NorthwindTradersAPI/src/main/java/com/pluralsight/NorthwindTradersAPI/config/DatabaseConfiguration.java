package com.pluralsight.NorthwindTradersAPI.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/northwind");
        ds.setUsername("root");
        ds.setPassword("yearup");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return ds;
    }
}


