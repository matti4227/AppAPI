package com.application.app;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername("pznjkznqhavwpm");
        config.setPassword("78ec9e9a5f16248351c0372dc0414c3d87abb204d1f1b1d3cf9813a59341adbc");
        return new HikariDataSource(config);
    }
}