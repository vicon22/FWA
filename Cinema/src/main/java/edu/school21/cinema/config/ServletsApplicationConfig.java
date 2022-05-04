package edu.school21.cinema.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = "/WEB-INF/application.properties")
public class ServletsApplicationConfig {
}
