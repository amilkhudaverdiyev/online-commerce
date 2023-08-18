////package com.onlinefoodcommercems.config;//package com.onlinefoodcommercems.config;
////
////import io.swagger.v3.oas.annotations.OpenAPIDefinition;
////import io.swagger.v3.oas.annotations.info.License;
////import io.swagger.v3.oas.annotations.servers.Server;
////import io.swagger.v3.oas.models.OpenAPI;
////import io.swagger.v3.oas.annotations.info.Info;
////import io.swagger.v3.oas.annotations.info.Contact;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////@OpenAPIDefinition(info = @Info(
////        contact = @Contact(
////                name = "Amil",
////                email = "amil.xudaverdiyev30@gmail.com",
////                url = "http://onlinefoodcommerce.com"
////        ),
////        description = "OpenApi documentation for Spring Security",
////        title = "OpenApi specification - Amil",
////        version = "1.0",
////        license = @License(
////                name = "License name",
////                url = "https://some-url.com"
////        ),
////        termsOfService = "Terms of service"
////),
////servers= {
////        @Server(description = "Local ENV",
////        url = "http:/localhost:2020")
////
////}
////
////)
//
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//
//@SecurityScheme(
//        name = "bearerAuth",
//        description = "JWT auth description",
//        scheme = "bearer",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
//)
////@Configuration
////public class SwaggerConfiguration {
////    @Bean
////    public OpenAPI cardOperationOpenAPI() {
////        return new OpenAPI()
////                .info(new Info()
////                        .title("Check Cfile API")
////                        .description("Cfile check file")
////                        .version("v1.3.0"));
////    }
////}
