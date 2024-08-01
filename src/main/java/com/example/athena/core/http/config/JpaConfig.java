package com.example.athena.core.http.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    @Bean
    public JpaProperties jpaProperties() {
        JpaProperties jpaProperties = new JpaProperties();
        jpaProperties.getProperties().put("hibernate.jdbc.time_zone", "UTC-03:00");
        return jpaProperties;
    }
}
