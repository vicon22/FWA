package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@PropertySource(value = "/WEB-INF/application.properties")
@ComponentScan(basePackages = {"edu.school21.cinema"})
public class ServletsApplicationConfig {

//    @Value("${db.url}")
    private String DB_URL = "jdbc:postgresql://localhost/postgres";

//    @Value("${db.user}")
    private String DB_USER = "postgres";

//    @Value("${db.password}")
    private String DB_PASSWORD = "1234";

//    @Value("${db.driver.name}")
    private String DB_DRIVER_NAME = "org.postgresql.Driver";

    @Bean
    public DriverManagerDataSource getHikariDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

