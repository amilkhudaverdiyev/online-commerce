package com.onlinefoodcommercems.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI cardOperationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Check Cfile API")
                        .description("Cfile check file")
                        .version("v1.3.0"));
    }
}
