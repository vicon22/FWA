package edu.school21.cinema.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource(value = "/application.properties")
//@PropertySource("classpath:../application.properties")
////@PropertySource("classpath:../webapp/WEB-INF/application.properties")
@ComponentScan(basePackages = {"edu.school21.cinema"})
public class ServletsApplicationConfig {

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.user}")
    private String DB_USER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.driver.name}")
    private String DB_DRIVER_NAME;

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

