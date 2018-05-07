package controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.StatusService;

@Configuration
public class IntegrationConfig {

    @Bean
    public OvenController ovenController(StatusService statusService){
        return new OvenController(statusService);
    }
}