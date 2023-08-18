package com.onlinefoodcommercems.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name = "Amil",
                email = "amil.xudaverdiyev30@gmail.com",
                url = "http://onlinefoodcommerce.com"
        ),
        description = "OpenApi documentation for Spring Security",
        title = "OpenApi specification - Amil",
        version = "v1.3.0",
        termsOfService = "Terms of service"
),
        servers = {
                @Server(description = "Local ENV",
                        url = "http:/localhost:2020")

        },
        security = {
        @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI cardOperationOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Check Cfile API")
//                        .description("Cfile check file")
//                        .version("v1.3.0"));
//    }
}
