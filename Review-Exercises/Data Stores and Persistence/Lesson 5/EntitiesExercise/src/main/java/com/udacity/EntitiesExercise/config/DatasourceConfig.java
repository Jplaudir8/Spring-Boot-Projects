package com.udacity.EntitiesExercise.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "com.joan.datasource") // db url is loaded from .properties file
    public DataSource getDatasource(DataSourceProperties properties) {
        DataSourceBuilder dsb = DataSourceBuilder.create();
        //dsb.username("sa");
        //dsb.password(securePasswordService());
        dsb.url("jdbc:mysql://localhost:3306/plantdb");
        return dsb.build();
    }

    private String securePasswordService() {
        return "sa1234";
    }

}
