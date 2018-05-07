package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.StatusService;

@Configuration
@ComponentScan(basePackageClasses = StatusService.class)
public class RootConfig {

}