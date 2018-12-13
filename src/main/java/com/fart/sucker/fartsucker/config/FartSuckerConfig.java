package com.fart.sucker.fartsucker.config;

import org.springframework.cloud.aws.messaging.config.annotation.EnableSns;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableSns
@EnableAsync
@Configuration
public class FartSuckerConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
