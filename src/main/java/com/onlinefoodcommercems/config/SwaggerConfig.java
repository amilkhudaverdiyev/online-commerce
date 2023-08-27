package com.onlinefoodcommercems.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
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
@Configuration
public class SwaggerConfig {

}
